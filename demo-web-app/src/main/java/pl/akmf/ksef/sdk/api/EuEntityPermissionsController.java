package pl.akmf.ksef.sdk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.PermissionsOperationResponse;
import pl.akmf.ksef.sdk.client.model.permission.euentity.GrantEUEntityPermissionsRequest;

@RestController
@RequiredArgsConstructor
public class EuEntityPermissionsController {
    private final DefaultKsefClient ksefClient;

    @PostMapping(value = "/grant-eu-entity-permissions")
    public PermissionsOperationResponse grantPermissionsEntity(@RequestBody GrantEUEntityPermissionsRequest request) throws ApiException {
        return ksefClient.grantsPermissionEUEntity(request);
    }

    @PostMapping(value = "/revoke-eu-entity-permissions")
    public PermissionsOperationResponse revokePermissionsEntity(@RequestBody String permissionId) throws ApiException {
        return ksefClient.revokeCommonPermission(permissionId);
    }
}