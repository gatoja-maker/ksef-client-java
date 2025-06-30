package pl.akmf.ksef.sdk.client.model.certificate;

import java.time.OffsetDateTime;

public class SendCertificateEnrollmentRequest {
    private String certificateName;
    private byte[] csr;
    private OffsetDateTime validFrom;

    public SendCertificateEnrollmentRequest() {
    }

    public SendCertificateEnrollmentRequest(String certificateName, byte[] csr, OffsetDateTime validFrom) {
        this.certificateName = certificateName;
        this.csr = csr;
        this.validFrom = validFrom;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public byte[] getCsr() {
        return csr;
    }

    public void setCsr(byte[] csr) {
        this.csr = csr;
    }

    public OffsetDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
    }
}

