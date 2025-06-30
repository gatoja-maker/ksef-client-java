package pl.akmf.ksef.sdk.client.interfaces;

import org.bouncycastle.asn1.x500.X500Name;
import pl.akmf.ksef.sdk.client.model.certificate.SelfSignedCertificate;
import pl.akmf.ksef.sdk.system.SystemKSeFSDKException;

public interface CertificateGenerator {

    SelfSignedCertificate generateSelfSignedCertificate(X500Name x500Name) throws SystemKSeFSDKException;
}
