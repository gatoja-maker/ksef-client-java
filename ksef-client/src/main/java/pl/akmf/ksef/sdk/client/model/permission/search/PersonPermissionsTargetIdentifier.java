package pl.akmf.ksef.sdk.client.model.permission.search;

public class PersonPermissionsTargetIdentifier {
    private PersonPermissionsTargetIdentifierType type;
    private String value;

    public PersonPermissionsTargetIdentifier() {
    }

    public PersonPermissionsTargetIdentifier(PersonPermissionsTargetIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public PersonPermissionsTargetIdentifierType getType() {
        return type;
    }

    public void setType(PersonPermissionsTargetIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

