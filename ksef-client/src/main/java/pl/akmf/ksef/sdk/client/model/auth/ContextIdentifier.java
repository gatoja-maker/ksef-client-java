package pl.akmf.ksef.sdk.client.model.auth;

public class ContextIdentifier {
    private ContextIdentifierType type;
    private String value;

    public ContextIdentifier() {
    }

    public ContextIdentifier(ContextIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ContextIdentifierType getType() {
        return type;
    }

    public void setType(ContextIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

