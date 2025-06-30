package pl.akmf.ksef.sdk.api.builders.permission.person;

import pl.akmf.ksef.sdk.client.model.permission.person.PersonPermissionType;
import pl.akmf.ksef.sdk.client.model.permission.search.PermissionState;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermissionsAuthorIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermissionsAuthorizedIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermissionsQueryRequest;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermissionsTargetIdentifier;

import java.util.List;

public class PersonPermissionsQueryRequestBuilder {
    private PersonPermissionsAuthorIdentifier authorIdentifier;
    private PersonPermissionsAuthorizedIdentifier authorizedIdentifier;
    private PersonPermissionsTargetIdentifier targetIdentifier;
    private List<PersonPermissionType> permissionTypes;
    private PermissionState permissionState;

    public PersonPermissionsQueryRequestBuilder withAuthorIdentifier(PersonPermissionsAuthorIdentifier authorIdentifier) {
        this.authorIdentifier = authorIdentifier;
        return this;
    }

    public PersonPermissionsQueryRequestBuilder withAuthorizedIdentifier(PersonPermissionsAuthorizedIdentifier authorizedIdentifier) {
        this.authorizedIdentifier = authorizedIdentifier;
        return this;
    }

    public PersonPermissionsQueryRequestBuilder withTargetIdentifier(PersonPermissionsTargetIdentifier targetIdentifier) {
        this.targetIdentifier = targetIdentifier;
        return this;
    }

    public PersonPermissionsQueryRequestBuilder withPermissionTypes(List<PersonPermissionType> permissionTypes) {
        this.permissionTypes = permissionTypes;
        return this;
    }

    public PersonPermissionsQueryRequestBuilder withPermissionState(PermissionState permissionState) {
        this.permissionState = permissionState;
        return this;
    }

    public PersonPermissionsQueryRequest build() {
        return new PersonPermissionsQueryRequest(authorIdentifier, authorizedIdentifier, targetIdentifier, permissionTypes, permissionState);
    }
}