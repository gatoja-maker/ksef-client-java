package pl.akmf.ksef.sdk.client.model.permission.indirect;

public class TargetIdentifier {
    private TargetIdentifierType type;
    private String value;

    public TargetIdentifier(TargetIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TargetIdentifierType getType() {
        return type;
    }

    public void setType(TargetIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
