package pl.akmf.ksef.sdk.client.model.permission.person;

public class PersonPermissionsSubjectIdentifier {
    private PersonPermissionsSubjectIdentifierType type;
    private String value;

    public PersonPermissionsSubjectIdentifier() {
    }

    public PersonPermissionsSubjectIdentifier(PersonPermissionsSubjectIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public PersonPermissionsSubjectIdentifierType getType() {
        return type;
    }

    public void setType(PersonPermissionsSubjectIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

