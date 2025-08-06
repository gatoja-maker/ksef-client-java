package pl.akmf.ksef.sdk.client.model.certificate.publickey;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


public class PublicKeyCertificate {
    private String certificatePem;
    private OffsetDateTime validFrom;
    private OffsetDateTime validTo;
    private List<PublicKeyCertificateUsage> usage = new ArrayList<>();

    public PublicKeyCertificate() {
    }

    public String getCertificatePem() {
        return certificatePem;
    }

    public void setCertificatePem(String certificatePem) {
        this.certificatePem = certificatePem;
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

    public List<PublicKeyCertificateUsage> getUsage() {
        return usage;
    }

    public void setUsage(List<PublicKeyCertificateUsage> usage) {
        this.usage = usage;
    }
}
