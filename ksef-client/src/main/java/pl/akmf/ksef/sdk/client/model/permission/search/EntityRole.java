package pl.akmf.ksef.sdk.client.model.permission.search;

import java.time.OffsetDateTime;


public class EntityRole {
    private String parentEntityIdentifier;
    private String parentEntityIdentifierType;
    private String role;
    private String description;
    private OffsetDateTime startDate;

    public EntityRole() {
    }

    public EntityRole(String parentEntityIdentifier, String parentEntityIdentifierType, String role, String description, OffsetDateTime startDate) {
        this.parentEntityIdentifier = parentEntityIdentifier;
        this.parentEntityIdentifierType = parentEntityIdentifierType;
        this.role = role;
        this.description = description;
        this.startDate = startDate;
    }

    public String getParentEntityIdentifier() {
        return parentEntityIdentifier;
    }

    public void setParentEntityIdentifier(String parentEntityIdentifier) {
        this.parentEntityIdentifier = parentEntityIdentifier;
    }

    public String getParentEntityIdentifierType() {
        return parentEntityIdentifierType;
    }

    public void setParentEntityIdentifierType(String parentEntityIdentifierType) {
        this.parentEntityIdentifierType = parentEntityIdentifierType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}

