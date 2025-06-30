package pl.akmf.ksef.sdk.client.model.permission.euentity;

public class SubjectIdentifier {
    private SubjectIdentifierType type;
    private String value;

    public SubjectIdentifier() {
    }

    public SubjectIdentifier(SubjectIdentifierType subjectIdentifierType, String value) {
        this.type = subjectIdentifierType;
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

