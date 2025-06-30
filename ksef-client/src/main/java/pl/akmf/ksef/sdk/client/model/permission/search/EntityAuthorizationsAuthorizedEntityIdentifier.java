package pl.akmf.ksef.sdk.client.model.permission.search;

public class EntityAuthorizationsAuthorizedEntityIdentifier {
    private EntityAuthorizationsAuthorizedEntityIdentifierType type;
    private String value;

    public EntityAuthorizationsAuthorizedEntityIdentifier() {
    }

    public EntityAuthorizationsAuthorizedEntityIdentifier(EntityAuthorizationsAuthorizedEntityIdentifierType type, String value) {
        this.type = type;
        this.value = value;
    }

    public EntityAuthorizationsAuthorizedEntityIdentifierType getType() {
        return type;
    }

    public void setType(EntityAuthorizationsAuthorizedEntityIdentifierType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

