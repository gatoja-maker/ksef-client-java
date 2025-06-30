package pl.akmf.ksef.sdk.client.model.permission.entity;

public class SubjectIdentifier {
    private SubjectIdentifierType type;
    private String value;

    public SubjectIdentifier() {
    }

    public SubjectIdentifier(SubjectIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public SubjectIdentifierType getType() {
        return type;
    }

    public void setType(SubjectIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
