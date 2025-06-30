package pl.akmf.ksef.sdk.api.builders.certificate;

import pl.akmf.ksef.sdk.client.model.certificate.SendCertificateEnrollmentRequest;

import java.time.OffsetDateTime;

public class SendCertificateEnrollmentRequestBuilder {
    private String certificateName;
    private byte[] csr;
    private OffsetDateTime validFrom;

    public SendCertificateEnrollmentRequestBuilder withCertificateName(String certificateName) {
        this.certificateName = certificateName;
        return this;
    }

    public SendCertificateEnrollmentRequestBuilder withCsr(byte[] csr) {
        this.csr = csr;
        return this;
    }

    public SendCertificateEnrollmentRequestBuilder withValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public SendCertificateEnrollmentRequest build() {
        SendCertificateEnrollmentRequest request = new SendCertificateEnrollmentRequest();
        request.setCertificateName(certificateName);
        request.setCsr(csr);
        request.setValidFrom(validFrom);

        return request;
    }
}
