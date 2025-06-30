package pl.akmf.ksef.sdk.client.model.permission.search;

import java.time.OffsetDateTime;

public class SubunitPermission {
    private String id;
    private String authorizedIdentifier;
    private String authorizedIdentifierType;
    private String subunitIdentifier;
    private String subunitdentifierType;
    private String authorIdentifier;
    private String authorIdentifierType;
    private String permissionScope;
    private String description;
    private OffsetDateTime startDate;
    private boolean canDelegate;

    SubunitPermission() {
    }

    public SubunitPermission(String authorizedIdentifier, String authorizedIdentifierType, String subunitIdentifier, String subunitdentifierType, String authorIdentifier, String authorIdentifierType, String permissionScope, String description, OffsetDateTime startDate) {
        this.authorizedIdentifier = authorizedIdentifier;
        this.authorizedIdentifierType = authorizedIdentifierType;
        this.subunitIdentifier = subunitIdentifier;
        this.subunitdentifierType = subunitdentifierType;
        this.authorIdentifier = authorIdentifier;
        this.authorIdentifierType = authorIdentifierType;
        this.permissionScope = permissionScope;
        this.description = description;
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorizedIdentifier() {
        return authorizedIdentifier;
    }

    public void setAuthorizedIdentifier(String authorizedIdentifier) {
        this.authorizedIdentifier = authorizedIdentifier;
    }

    public String getAuthorizedIdentifierType() {
        return authorizedIdentifierType;
    }

    public void setAuthorizedIdentifierType(String authorizedIdentifierType) {
        this.authorizedIdentifierType = authorizedIdentifierType;
    }

    public String getSubunitIdentifier() {
        return subunitIdentifier;
    }

    public void setSubunitIdentifier(String subunitIdentifier) {
        this.subunitIdentifier = subunitIdentifier;
    }

    public String getSubunitdentifierType() {
        return subunitdentifierType;
    }

    public void setSubunitdentifierType(String subunitdentifierType) {
        this.subunitdentifierType = subunitdentifierType;
    }

    public String getAuthorIdentifier() {
        return authorIdentifier;
    }

    public void setAuthorIdentifier(String authorIdentifier) {
        this.authorIdentifier = authorIdentifier;
    }

    public String getAuthorIdentifierType() {
        return authorIdentifierType;
    }

    public void setAuthorIdentifierType(String authorIdentifierType) {
        this.authorIdentifierType = authorIdentifierType;
    }

    public String getPermissionScope() {
        return permissionScope;
    }

    public void setPermissionScope(String permissionScope) {
        this.permissionScope = permissionScope;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public boolean isCanDelegate() {
        return canDelegate;
    }

    public void setCanDelegate(boolean canDelegate) {
        this.canDelegate = canDelegate;
    }
}

