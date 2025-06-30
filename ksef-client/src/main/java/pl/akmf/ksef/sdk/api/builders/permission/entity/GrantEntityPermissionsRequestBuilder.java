package pl.akmf.ksef.sdk.api.builders.permission.entity;

import pl.akmf.ksef.sdk.client.model.permission.entity.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.entity.EntityPermission;
import pl.akmf.ksef.sdk.client.model.permission.entity.GrantEntityPermissionsRequest;

import java.util.List;

public class GrantEntityPermissionsRequestBuilder {
    private SubjectIdentifier subjectIdentifier;
    private List<EntityPermission> permissions;
    private String description;

    public GrantEntityPermissionsRequestBuilder withSubjectIdentifier(SubjectIdentifier subjectIdentifier) {
        this.subjectIdentifier = subjectIdentifier;
        return this;
    }

    public GrantEntityPermissionsRequestBuilder withPermissions(List<EntityPermission> permissions) {
        this.permissions = permissions;
        return this;
    }

    public GrantEntityPermissionsRequestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public GrantEntityPermissionsRequest build() {
        GrantEntityPermissionsRequest request = new GrantEntityPermissionsRequest();
        request.setSubjectIdentifier(subjectIdentifier);
        request.setPermissions(permissions);
        request.setDescription(description);
        return request;
    }
}
