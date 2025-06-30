package pl.akmf.ksef.sdk.api.builders.tokens;

import pl.akmf.ksef.sdk.client.model.auth.GenerateTokenRequest;
import pl.akmf.ksef.sdk.client.model.auth.TokenPermissionType;

import java.util.List;

public class GenerateTokenRequestBuilder {
    private List<TokenPermissionType> permissions;
    private String description;

    public GenerateTokenRequestBuilder withPermissions(List<TokenPermissionType> permissions) {
        this.permissions = permissions;
        return this;
    }

    public GenerateTokenRequestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public GenerateTokenRequest build() {
        return new GenerateTokenRequest(permissions, description);
    }
}