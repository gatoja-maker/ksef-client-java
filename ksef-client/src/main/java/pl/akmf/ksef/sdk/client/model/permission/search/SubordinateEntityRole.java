package pl.akmf.ksef.sdk.client.model.permission.search;

import java.time.OffsetDateTime;

public class SubordinateEntityRole {
    private String subordinateEntityIdentifier;
    private String subordinateEntityIdentifierType;
    private String role;
    private String description;
    private OffsetDateTime startDate;

    public SubordinateEntityRole() {
    }

    public SubordinateEntityRole(String subordinateEntityIdentifier, String subordinateEntityIdentifierType, String role, String description, OffsetDateTime startDate) {
        this.subordinateEntityIdentifier = subordinateEntityIdentifier;
        this.subordinateEntityIdentifierType = subordinateEntityIdentifierType;
        this.role = role;
        this.description = description;
        this.startDate = startDate;
    }

    public String getSubordinateEntityIdentifier() {
        return subordinateEntityIdentifier;
    }

    public void setSubordinateEntityIdentifier(String subordinateEntityIdentifier) {
        this.subordinateEntityIdentifier = subordinateEntityIdentifier;
    }

    public String getSubordinateEntityIdentifierType() {
        return subordinateEntityIdentifierType;
    }

    public void setSubordinateEntityIdentifierType(String subordinateEntityIdentifierType) {
        this.subordinateEntityIdentifierType = subordinateEntityIdentifierType;
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

