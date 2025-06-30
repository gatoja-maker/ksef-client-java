package pl.akmf.ksef.sdk.client.model.auth;

import java.util.List;

public class GenerateTokenRequest {
    List<TokenPermissionType> permissions;
    String description;

    public GenerateTokenRequest(List<TokenPermissionType> permissions, String description) {
        this.permissions = permissions;
        this.description = description;
    }

    public GenerateTokenRequest() {
    }

    public List<TokenPermissionType> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TokenPermissionType> permissions) {
        this.permissions = permissions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
