package pl.akmf.ksef.sdk.client.model.permission.search;

public class PersonPermissionsAuthorizedIdentifier {
    private PersonPermissionsAuthorizedIdentifierType type;
    private String value;

    public PersonPermissionsAuthorizedIdentifier() {
    }

    public PersonPermissionsAuthorizedIdentifier(PersonPermissionsAuthorizedIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public PersonPermissionsAuthorizedIdentifierType getType() {
        return type;
    }

    public void setType(PersonPermissionsAuthorizedIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

