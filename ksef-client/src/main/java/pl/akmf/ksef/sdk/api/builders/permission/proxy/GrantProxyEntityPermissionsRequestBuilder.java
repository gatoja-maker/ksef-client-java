package pl.akmf.ksef.sdk.api.builders.permission.proxy;

import pl.akmf.ksef.sdk.client.model.permission.proxy.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.proxy.GrantProxyEntityPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.proxy.ProxyEntityPermissionType;

public class GrantProxyEntityPermissionsRequestBuilder {
    private SubjectIdentifier subjectIdentifier;
    private ProxyEntityPermissionType permission;
    private String description;

    public GrantProxyEntityPermissionsRequestBuilder withSubjectIdentifier(SubjectIdentifier subjectIdentifier) {
        this.subjectIdentifier = subjectIdentifier;
        return this;
    }

    public GrantProxyEntityPermissionsRequestBuilder withPermission(ProxyEntityPermissionType permission) {
        this.permission = permission;
        return this;
    }

    public GrantProxyEntityPermissionsRequestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public GrantProxyEntityPermissionsRequest build(){
        return new GrantProxyEntityPermissionsRequest(subjectIdentifier, permission, description);
    }
}
