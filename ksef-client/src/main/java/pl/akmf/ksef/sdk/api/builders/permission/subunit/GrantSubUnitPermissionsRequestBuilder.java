package pl.akmf.ksef.sdk.api.builders.permission.subunit;

import pl.akmf.ksef.sdk.client.model.permission.subunit.ContextIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.subunit.GrantSubUnitPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.subunit.SubjectIdentifier;

public class GrantSubUnitPermissionsRequestBuilder {
    private SubjectIdentifier subjectIdentifier;
    private ContextIdentifier contextIdentifier;
    private String description;

    public GrantSubUnitPermissionsRequestBuilder withSubjectIdentifier(SubjectIdentifier subjectIdentifier) {
        this.subjectIdentifier = subjectIdentifier;
        return this;
    }

    public GrantSubUnitPermissionsRequestBuilder withContextIdentifier(ContextIdentifier contextIdentifier) {
        this.contextIdentifier = contextIdentifier;
        return this;
    }

    public GrantSubUnitPermissionsRequestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public GrantSubUnitPermissionsRequest build() {
        GrantSubUnitPermissionsRequest request = new GrantSubUnitPermissionsRequest();
        request.setSubjectIdentifier(subjectIdentifier);
        request.setContextIdentifier(contextIdentifier);
        request.setDescription(description);
        return request;
    }
}
