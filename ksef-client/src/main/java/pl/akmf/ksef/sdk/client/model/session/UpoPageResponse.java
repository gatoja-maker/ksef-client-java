package pl.akmf.ksef.sdk.client.model.session;

import java.net.URI;

public class UpoPageResponse {
    private String referenceNumber;
    private URI downloadUrl;

    public UpoPageResponse() {
    }

    public UpoPageResponse(String referenceNumber, URI downloadUrl) {
        this.referenceNumber = referenceNumber;
        this.downloadUrl = downloadUrl;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public URI getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(URI downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}

