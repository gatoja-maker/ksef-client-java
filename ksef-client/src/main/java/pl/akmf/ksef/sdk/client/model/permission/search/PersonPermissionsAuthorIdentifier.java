package pl.akmf.ksef.sdk.client.model.permission.search;

public class PersonPermissionsAuthorIdentifier {
    private PersonPermissionsAuthorIdentifierType type;
    private String value;

    public PersonPermissionsAuthorIdentifier() {
    }

    public PersonPermissionsAuthorIdentifier(PersonPermissionsAuthorIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public PersonPermissionsAuthorIdentifierType getType() {
        return type;
    }

    public void setType(PersonPermissionsAuthorIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

