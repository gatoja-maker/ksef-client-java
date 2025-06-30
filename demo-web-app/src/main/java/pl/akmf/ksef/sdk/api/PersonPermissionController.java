package pl.akmf.ksef.sdk.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.client.model.permission.PermissionsOperationResponse;
import pl.akmf.ksef.sdk.api.builders.permission.person.GrantPersonPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.person.PersonPermissionType;
import pl.akmf.ksef.sdk.client.model.permission.person.GrantPersonPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.person.PersonPermissionsSubjectIdentifier;

import java.util.List;

@RestController
@RequestMapping("/person-permission")
@RequiredArgsConstructor
public class PersonPermissionController {

    private final DefaultKsefClient ksefClient;

    @PostMapping("/grant-permissions-for-person")
    public ResponseEntity<PermissionsOperationResponse> grantPermissionsPerson(
            @RequestBody PersonPermissionsSubjectIdentifier subjectIdentifier) throws ApiException {


     GrantPersonPermissionsRequest request = new GrantPersonPermissionsRequestBuilder()
                .withSubjectIdentifier(subjectIdentifier)
                .withPermissions(List.of(PersonPermissionType.INVOICEREAD, PersonPermissionType.INVOICEWRITE))
                .withDescription("Access for quarterly review")
                .build();

        PermissionsOperationResponse response = ksefClient.grantsPermissionPerson(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/revoke-permissions-for-person")
    public PermissionsOperationResponse revokePermissionsPerson(
            @RequestBody String permissionId) throws ApiException {
        return ksefClient.revokeCommonPermission(permissionId);
    }
}

