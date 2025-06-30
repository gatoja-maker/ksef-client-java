package pl.akmf.ksef.sdk.client.model.permission.search;

import java.time.OffsetDateTime;

public class PersonPermission {
    private String id;
    private String authorizedIdentifier;
    private String authorizedIdentifierType;
    private String targetIdentifier;
    private String targetIdentifierType;
    private String authorIdentifier;
    private String authorIdentifierType;
    private String permissionScope;
    private String description;
    private String permissionState;
    private OffsetDateTime startDate;
    private boolean canDelegate;

  public PersonPermission() {
    }

    public PersonPermission(String id, String authorizedIdentifier, String authorizedIdentifierType, String targetIdentifier, String targetIdentifierType, String authorIdentifier, String authorIdentifierType, String permissionScope, String description, String permissionState, OffsetDateTime startDate) {
        this.id = id;
        this.authorizedIdentifier = authorizedIdentifier;
        this.authorizedIdentifierType = authorizedIdentifierType;
        this.targetIdentifier = targetIdentifier;
        this.targetIdentifierType = targetIdentifierType;
        this.authorIdentifier = authorIdentifier;
        this.authorIdentifierType = authorIdentifierType;
        this.permissionScope = permissionScope;
        this.description = description;
        this.permissionState = permissionState;
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

    public String getTargetIdentifier() {
        return targetIdentifier;
    }

    public void setTargetIdentifier(String targetIdentifier) {
        this.targetIdentifier = targetIdentifier;
    }

    public String getTargetIdentifierType() {
        return targetIdentifierType;
    }

    public void setTargetIdentifierType(String targetIdentifierType) {
        this.targetIdentifierType = targetIdentifierType;
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

    public String getPermissionState() {
        return permissionState;
    }

    public void setPermissionState(String permissionState) {
        this.permissionState = permissionState;
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

