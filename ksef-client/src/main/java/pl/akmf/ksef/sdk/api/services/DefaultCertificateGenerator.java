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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DefaultCertificateGenerator implements CertificateGenerator {
    private static final String RSA = "RSA";
    private static final String EC = "EC";
    private static final String SHA_256_WITH_RSA = "SHA256WithRSA";
    private static final String SHA_256_WITH_ECDSA = "SHA256withECDSA";
    private static final String BC = "BC";

    @Override
    public SelfSignedCertificate generateSelfSignedCertificateRsa(X500Name x500Name) {

        return generateSelfSignedCertificate(RSA, 2048, SHA_256_WITH_RSA, x500Name);
    }

    @Override
    public SelfSignedCertificate generateSelfSignedCertificateEcdsa(X500Name x500Name) {

        return generateSelfSignedCertificate(EC, 256, SHA_256_WITH_ECDSA, x500Name);
    }

    private SelfSignedCertificate generateSelfSignedCertificate(String generatorAlgorithm, int generatorKeySize, String signatureAlgorithm, X500Name x500Name) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(generatorAlgorithm);
            keyPairGenerator.initialize(generatorKeySize);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            Instant now = Instant.now();
            Date notBefore = Date.from(now);
            Date notAfter = Date.from(now.plus(365, ChronoUnit.DAYS));

            BigInteger certSerialNumber = new BigInteger(Long.toString(System.currentTimeMillis()));

            JcaX509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
                    x500Name,
                    certSerialNumber,
                    notBefore,
                    notAfter,
                    x500Name,
                    keyPair.getPublic()
            );

            ContentSigner contentSigner = new JcaContentSignerBuilder(signatureAlgorithm)
                    .setProvider(BC)
                    .build(keyPair.getPrivate());

            X509CertificateHolder certHolder = certBuilder.build(contentSigner);

            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter()
                    .setProvider(BC);
            X509Certificate certificate = certConverter.getCertificate(certHolder);

            return new SelfSignedCertificate(certificate, keyPair);
        } catch (Exception e) {
            throw new SystemKSeFSDKException(e.getMessage(), e);
        }
    }
}
