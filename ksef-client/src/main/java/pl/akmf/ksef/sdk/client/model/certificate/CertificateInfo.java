package pl.akmf.ksef.sdk.client.model.certificate;

import java.time.OffsetDateTime;

public class CertificateInfo {
    private String certificateSerialNumber;
    private String name;
    private String commonName;
    private CertificateListItemStatus status;
    private String thumbprint;
    private String subjectIdentifier;
    private String subjectIdentifierType;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;
    private OffsetDateTime lastUseDate;

    public CertificateInfo() {

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

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public CertificateListItemStatus getStatus() {
        return status;
    }

    public void setStatus(CertificateListItemStatus status) {
        this.status = status;
    }

    public String getThumbprint() {
        return thumbprint;
    }

    public void setThumbprint(String thumbprint) {
        this.thumbprint = thumbprint;
    }

    public String getSubjectIdentifier() {
        return subjectIdentifier;
    }

    public void setSubjectIdentifier(String subjectIdentifier) {
        this.subjectIdentifier = subjectIdentifier;
    }

    public String getSubjectIdentifierType() {
        return subjectIdentifierType;
    }

    public void setSubjectIdentifierType(String subjectIdentifierType) {
        this.subjectIdentifierType = subjectIdentifierType;
    }

    public OffsetDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(OffsetDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public OffsetDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(OffsetDateTime validTo) {
        this.validTo = validTo;
    }

    public OffsetDateTime getLastUseDate() {
        return lastUseDate;
    }

    public void setLastUseDate(OffsetDateTime lastUseDate) {
        this.lastUseDate = lastUseDate;
    }
}