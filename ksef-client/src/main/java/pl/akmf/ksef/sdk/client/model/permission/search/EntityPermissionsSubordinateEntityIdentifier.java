package pl.akmf.ksef.sdk.client.model.permission.search;

public class EntityPermissionsSubordinateEntityIdentifier {
    private EntityPermissionsSubordinateEntityIdentifierType type;
    private String value;

    public EntityPermissionsSubordinateEntityIdentifier() {
    }

    public EntityPermissionsSubordinateEntityIdentifier(EntityPermissionsSubordinateEntityIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public EntityPermissionsSubordinateEntityIdentifierType getType() {
        return type;
    }

    public void setType(EntityPermissionsSubordinateEntityIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

