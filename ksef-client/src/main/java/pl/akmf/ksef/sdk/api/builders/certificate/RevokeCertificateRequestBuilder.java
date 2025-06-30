package pl.akmf.ksef.sdk.api.builders.certificate;

import pl.akmf.ksef.sdk.client.model.certificate.CertificateRevocationReason;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateRevokeRequest;

public class RevokeCertificateRequestBuilder {
    private CertificateRevocationReason revocationReason;

    public RevokeCertificateRequestBuilder withRevocationReason(final CertificateRevocationReason revocationReason) {
        this.revocationReason = revocationReason;
        return this;
    }

    public CertificateRevokeRequest build() {
        var revokeCertificateRequest = new CertificateRevokeRequest();
        revokeCertificateRequest.setRevocationReason(revocationReason);
        return revokeCertificateRequest;
    }
}
