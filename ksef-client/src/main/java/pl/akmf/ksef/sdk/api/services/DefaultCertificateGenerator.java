package pl.akmf.ksef.sdk.api.services;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import pl.akmf.ksef.sdk.client.interfaces.CertificateGenerator;
import pl.akmf.ksef.sdk.client.model.certificate.SelfSignedCertificate;
import pl.akmf.ksef.sdk.system.SystemKSeFSDKException;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.security.spec.RSAKeyGenParameterSpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DefaultCertificateGenerator implements CertificateGenerator {
    public static final String RSA = "RSA";
    public static final String SHA_256_WITH_RSA = "SHA256WithRSA";
    public static final String BC = "BC";

    public SelfSignedCertificate generateSelfSignedCertificate(X500Name x500Name) throws SystemKSeFSDKException {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            // Generate RSA key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4));
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Certificate validity period
            Instant now = Instant.now();
            Date notBefore = Date.from(now);
            Date notAfter = Date.from(now.plus(365, ChronoUnit.DAYS));

            // Create certificate info
            BigInteger certSerialNumber = new BigInteger(Long.toString(System.currentTimeMillis())); // <-- Using the current timestamp as the certificate serial number

            JcaX509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
                    x500Name,
                    certSerialNumber,
                    notBefore,
                    notAfter,
                    x500Name,
                    keyPair.getPublic()
            );

            // Create content signer
            ContentSigner contentSigner = new JcaContentSignerBuilder(SHA_256_WITH_RSA)
                    .setProvider(BC)
                    .build(keyPair.getPrivate());

            // Build the certificate
            X509CertificateHolder certHolder = certBuilder.build(contentSigner);

            // Convert to X509Certificate
            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter()
                    .setProvider(BC);
            X509Certificate certificate = certConverter.getCertificate(certHolder);

            return new SelfSignedCertificate(certificate, keyPair);
        } catch (Exception e) {
            throw new SystemKSeFSDKException(e.getMessage(), e);
        }
    }
}
