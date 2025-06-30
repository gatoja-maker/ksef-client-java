package pl.akmf.ksef.sdk.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.PermissionsOperationResponse;
import pl.akmf.ksef.sdk.client.model.permission.indirect.GrantIndirectEntityPermissionsRequest;

@Slf4j
@RestController
@AllArgsConstructor
public class IndirectPermissionsEntityController {

    private final DefaultKsefClient ksefClient;

    @PostMapping("/grant-indirect-permissions-for-entity")
    public PermissionsOperationResponse GrantPermissionsEntity(@RequestBody GrantIndirectEntityPermissionsRequest request) throws ApiException {
        PermissionsOperationResponse permissionsOperationResponse = ksefClient.grantsPermissionIndirectEntity(request);
        log.info(permissionsOperationResponse.toString());
        return permissionsOperationResponse;
    }

    @PostMapping("/revoke-indirect-permissions-for-entity")
    public PermissionsOperationResponse RevokePermissionsEntity(@RequestBody String permissionId) throws ApiException {
        return ksefClient.revokeAuthorizationsPermission(permissionId);
    }

}
