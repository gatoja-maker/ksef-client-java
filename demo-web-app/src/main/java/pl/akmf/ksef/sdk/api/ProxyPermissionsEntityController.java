package pl.akmf.ksef.sdk.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.client.model.permission.PermissionsOperationResponse;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.proxy.GrantProxyEntityPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.proxy.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.proxy.ProxyEntityPermissionType;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProxyPermissionsEntityController {

    private final DefaultKsefClient ksefClient;

    @PostMapping("/grant-proxy-permissions-for-entity")
    public PermissionsOperationResponse grantPermissionsEntity(@RequestBody SubjectIdentifier subjectIdentifier) throws ApiException {
        GrantProxyEntityPermissionsRequest request = new GrantProxyEntityPermissionsRequest();
        request.setSubjectIdentifier(subjectIdentifier);
        request.setPermission(ProxyEntityPermissionType.TAXREPRESENTATIVE);
        request.setDescription("Access for quarterly review");

        PermissionsOperationResponse permissionsOperationResponse = ksefClient.grantsPermissionsProxyEntity(request);
        log.info(permissionsOperationResponse.toString());
        return permissionsOperationResponse;
    }

    @PostMapping("/revoke-proxy-permissions-for-entity")
    public PermissionsOperationResponse RevokePermissionsEntity(@RequestBody String permissionId) throws ApiException {
        return ksefClient.revokeAuthorizationsPermission(permissionId);
    }
}
