package pl.akmf.ksef.sdk.client.model.permission.search;

public class SubunitPermissionsSubunitIdentifier {
    private SubunitPermissionsSubunitIdentifierType type;
    private String value;

    public SubunitPermissionsSubunitIdentifier() {
    }

    public SubunitPermissionsSubunitIdentifier(SubunitPermissionsSubunitIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public SubunitPermissionsSubunitIdentifierType getType() {
        return type;
    }

    public void setType(SubunitPermissionsSubunitIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

