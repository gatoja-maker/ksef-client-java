package pl.akmf.ksef.sdk.client.model.certificate;

import java.time.OffsetDateTime;

public class CertificateMetadataListRequest {
    private String certificateSerialNumber;
    private String name;
    private CertificateListItemStatus status;
    private OffsetDateTime expiresAfter;

    public CertificateMetadataListRequest() {
    }

    public CertificateMetadataListRequest(String certificateSerialNumber, String name, CertificateListItemStatus status, OffsetDateTime expiresAfter) {
        this.certificateSerialNumber = certificateSerialNumber;
        this.name = name;
        this.status = status;
        this.expiresAfter = expiresAfter;
    }

    public String getCertificateSerialNumber() {
        return certificateSerialNumber;
    }

    public void setCertificateSerialNumber(String certificateSerialNumber) {
        this.certificateSerialNumber = certificateSerialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CertificateListItemStatus getStatus() {
        return status;
    }

    public void setStatus(CertificateListItemStatus status) {
        this.status = status;
    }

    public OffsetDateTime getExpiresAfter() {
        return expiresAfter;
    }

    public void setExpiresAfter(OffsetDateTime expiresAfter) {
        this.expiresAfter = expiresAfter;
    }
}

