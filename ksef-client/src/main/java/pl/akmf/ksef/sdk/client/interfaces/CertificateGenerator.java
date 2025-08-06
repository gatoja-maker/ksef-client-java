package pl.akmf.ksef.sdk.client.interfaces;

import org.bouncycastle.asn1.x500.X500Name;
import pl.akmf.ksef.sdk.client.model.certificate.SelfSignedCertificate;

public interface CertificateGenerator {

    SelfSignedCertificate generateSelfSignedCertificateRsa(X500Name x500Name);

    SelfSignedCertificate generateSelfSignedCertificateEcdsa(X500Name x500Name);
}
