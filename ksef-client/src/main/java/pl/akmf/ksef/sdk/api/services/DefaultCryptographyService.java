package pl.akmf.ksef.sdk.api.services;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import pl.akmf.ksef.sdk.client.interfaces.CryptographyService;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentsInfoResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CsrResult;
import pl.akmf.ksef.sdk.client.model.session.EncryptionData;
import pl.akmf.ksef.sdk.client.model.session.EncryptionInfo;
import pl.akmf.ksef.sdk.client.model.session.FileMetadata;
import pl.akmf.ksef.sdk.system.SystemKSeFSDKException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

public class DefaultCryptographyService implements CryptographyService {
    public static final String AES_CBC_PKCS_5_PADDING = "AES/CBC/PKCS5Padding";
    public static final String AES = "AES";
    public static final String RSA = "RSA";
    public static final String SHA_256_WITH_RSA = "SHA256withRSA";
    public static final String SHA_256 = "SHA-256";
    public static final String MGF_1 = "MGF1";
    public static final String RSA_ECB_OAEPPADDING = "RSA/ECB/OAEPPadding";
    private final DefaultKsefClient ksefClient;

    public DefaultCryptographyService(DefaultKsefClient ksefClient) {
        this.ksefClient = ksefClient;
    }

    @Override
    public EncryptionData getEncryptionData() throws SystemKSeFSDKException, ApiException {
        byte[] publicKey = ksefClient.getPublicKey();

        try {
            byte[] key = generateRandom256BitsKey();

            byte[] iv = generateRandom16BytesIv();

            byte[] encryptedKey = encryptWithRSAUsingPublicKey(key, publicKey);
            String encodedEncryptedKey = Base64.getEncoder().encodeToString(encryptedKey);
            String initializationVector = Base64.getEncoder().encodeToString(iv);

            EncryptionInfo encryptionInfo = new EncryptionInfo();
            encryptionInfo.setEncryptedSymmetricKey(encodedEncryptedKey);
            encryptionInfo.setInitializationVector(initializationVector);

            return new EncryptionData(key, iv, encodedEncryptedKey, encryptionInfo);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemKSeFSDKException(e.getMessage(), e);
        }
    }

    @Override
    public byte[] encryptKsefTokenWithRSAUsingPublicKey(byte[] content) throws SystemKSeFSDKException, ApiException {
        byte[] publicKey = ksefClient.getPublicKey();

        return encryptWithRSAUsingPublicKey(content, publicKey);
    }

    @Override
    public byte[] encryptBytesWithAES256(byte[] content, byte[] key, byte[] iv) throws SystemKSeFSDKException {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS_5_PADDING);

            SecretKeySpec secretKey = new SecretKeySpec(key, AES);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            return cipher.doFinal(content);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new SystemKSeFSDKException(e.getMessage(), e);
        }
    }

    @Override
    public CsrResult generateCsr(CertificateEnrollmentsInfoResponse certificateInfo) throws SystemKSeFSDKException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);

            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            X500NameBuilder nameBuilder = getX500NameBuilder(certificateInfo);

            // Add given names
            List<String> givenNames = certificateInfo.getGivenNames();
            if (givenNames != null) {
                for (String givenName : givenNames) {
                    if (givenName != null && !givenName.isEmpty()) {
                        nameBuilder.addRDN(BCStyle.GIVENNAME, givenName);
                    }
                }
            }

            X500Name subject = nameBuilder.build();

            // Create CSR
            PKCS10CertificationRequestBuilder requestBuilder =
                    new JcaPKCS10CertificationRequestBuilder(subject, keyPair.getPublic());

            ContentSigner signer = new JcaContentSignerBuilder(SHA_256_WITH_RSA)
                    .build(keyPair.getPrivate());

            PKCS10CertificationRequest csr = requestBuilder.build(signer);

            return new CsrResult(csr.getEncoded(), keyPair.getPrivate().getEncoded());
        } catch (IOException | OperatorCreationException | NoSuchAlgorithmException e) {
            throw new SystemKSeFSDKException(e.getMessage(), e);
        }
    }

    @Override
    public FileMetadata getMetaData(byte[] file) throws SystemKSeFSDKException {
        try {
            MessageDigest sha256 = MessageDigest.getInstance(SHA_256);
            byte[] hash = sha256.digest(file);
            String base64Hash = Base64.getEncoder().encodeToString(hash);

            int fileSize = file.length;

            FileMetadata fileMetadata = new FileMetadata();
            fileMetadata.setFileSize((long) fileSize);
            fileMetadata.setHashSHA(base64Hash);

            return fileMetadata;
        } catch (NoSuchAlgorithmException e) {
            throw new SystemKSeFSDKException(e.getMessage(), e);
        }
    }

    private byte[] encryptWithRSAUsingPublicKey(byte[] content, byte[] publicKeyBytes) throws SystemKSeFSDKException {
        // Parse PEM content to get public key
        try {
            PublicKey publicKey = parsePublicKeyFromPem(publicKeyBytes);

            OAEPParameterSpec oaepParams = new OAEPParameterSpec(
                    SHA_256,       // Hash algorithm (SHA-1, SHA-256, etc.)
                    MGF_1,              // Mask generation function
                    MGF1ParameterSpec.SHA256,             // MGF1 parameter spec
                    PSource.PSpecified.DEFAULT
            );

            Cipher cipher = Cipher.getInstance(RSA_ECB_OAEPPADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParams);

            return cipher.doFinal(content);
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new SystemKSeFSDKException(e.getMessage(), e);
        }
    }

    private PublicKey parsePublicKeyFromPem(byte[] publicKey) throws SystemKSeFSDKException {
        try {
            String publicKeyPEM = new String(publicKey, StandardCharsets.UTF_8);

            try (StringReader stringReader = new StringReader(publicKeyPEM);
                 PemReader pemReader = new PemReader(stringReader)) {

                PemObject pemObject = pemReader.readPemObject();
                byte[] keyBytes = pemObject.getContent();

                KeyFactory keyFactory = KeyFactory.getInstance(RSA);
                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

                return keyFactory.generatePublic(keySpec);
            }
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new SystemKSeFSDKException(e.getMessage(), e);
        }
    }

    private static X500NameBuilder getX500NameBuilder(CertificateEnrollmentsInfoResponse certificateInfo) {
        // Build the subject name
        X500NameBuilder nameBuilder = new X500NameBuilder(BCStyle.INSTANCE);

        if (certificateInfo.getCommonName() != null && !certificateInfo.getCommonName().isEmpty()) {
            nameBuilder.addRDN(BCStyle.CN, certificateInfo.getCommonName());
        }
        if (certificateInfo.getSurname() != null && !certificateInfo.getSurname().isEmpty()) {
            nameBuilder.addRDN(BCStyle.SURNAME, certificateInfo.getSurname());
        }
        if (certificateInfo.getOrganizationName() != null && !certificateInfo.getOrganizationName().isEmpty()) {
            nameBuilder.addRDN(BCStyle.O, certificateInfo.getOrganizationName());
        }
        if (certificateInfo.getOrganizationIdentifier() != null && !certificateInfo.getOrganizationIdentifier().isEmpty()) {
            nameBuilder.addRDN(BCStyle.ORGANIZATION_IDENTIFIER, certificateInfo.getOrganizationIdentifier());
        }
        if (certificateInfo.getCountryName() != null && !certificateInfo.getCountryName().isEmpty()) {
            nameBuilder.addRDN(BCStyle.C, certificateInfo.getCountryName());
        }
        if (certificateInfo.getSerialNumber() != null && !certificateInfo.getSerialNumber().isEmpty()) {
            nameBuilder.addRDN(BCStyle.SERIALNUMBER, certificateInfo.getSerialNumber());
        }
        if (certificateInfo.getUniqueIdentifier() != null && !certificateInfo.getUniqueIdentifier().isEmpty()) {
            nameBuilder.addRDN(BCStyle.UNIQUE_IDENTIFIER, certificateInfo.getUniqueIdentifier());
        }
        return nameBuilder;
    }

    private byte[] generateRandom256BitsKey() throws NoSuchAlgorithmException {
        byte[] key = new byte[256 / 8];
        SecureRandom.getInstanceStrong().nextBytes(key);
        return key;
    }

    private byte[] generateRandom16BytesIv() throws NoSuchAlgorithmException {
        byte[] iv = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(iv);
        return iv;
    }
}