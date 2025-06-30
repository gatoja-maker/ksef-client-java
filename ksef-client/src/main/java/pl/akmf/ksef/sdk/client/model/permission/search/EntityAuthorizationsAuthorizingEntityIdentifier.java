package pl.akmf.ksef.sdk.client.model.permission.search;

public class EntityAuthorizationsAuthorizingEntityIdentifier {
    private EntityAuthorizationsAuthorizingEntityIdentifierType type;
    private String value;

    public EntityAuthorizationsAuthorizingEntityIdentifier() {
    }

    public EntityAuthorizationsAuthorizingEntityIdentifier(EntityAuthorizationsAuthorizingEntityIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public EntityAuthorizationsAuthorizingEntityIdentifierType getType() {
        return type;
    }

    public void setType(EntityAuthorizationsAuthorizingEntityIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

