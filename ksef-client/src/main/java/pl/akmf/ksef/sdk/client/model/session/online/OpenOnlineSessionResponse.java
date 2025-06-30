package pl.akmf.ksef.sdk.client.model.session.online;

public class OpenOnlineSessionResponse {
    private String referenceNumber;

    public OpenOnlineSessionResponse() {
    }

    public OpenOnlineSessionResponse(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}

