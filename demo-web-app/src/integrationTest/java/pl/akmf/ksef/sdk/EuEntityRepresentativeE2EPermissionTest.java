package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.euentity.EuEntityPermissionsQueryRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.permission.euentityrepresentative.GrantEUEntityRepresentativePermissionsRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.PermissionStatusInfo;
import pl.akmf.ksef.sdk.client.model.permission.euentity.EuEntityPermissionType;
import pl.akmf.ksef.sdk.client.model.permission.euentity.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.euentity.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.search.EuEntityPermission;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class EuEntityRepresentativeE2EPermissionTest extends BaseIntegrationTest {

    @Test
    void euEntityRepresentativeE2EIntegrationTest() throws JAXBException, IOException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP_VAT_UE, contextNip);

        var grantReferenceNumber = grantEuEntityRepresentativePermission(subjectNip);

        await().atMost(15, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> isOperationFinish(grantReferenceNumber));

        var permission = searchPermission(subjectNip, 1);

        permission.forEach(e -> {
            var revokeReferenceNumber = revokePermission(e);

            await().atMost(30, SECONDS)
                    .pollInterval(2, SECONDS)
                    .until(() -> isOperationFinish(revokeReferenceNumber));
        });

        searchPermission(subjectNip, 0);
    }

    private String revokePermission(String operationId) {
        try {
            return defaultKsefClient.revokeAuthorizationsPermission(operationId).getOperationReferenceNumber();
        } catch (ApiException e) {
            Assertions.fail(e.getMessage());
        }
        return null;
    }

    private List<String> searchPermission(String subjectContext, int expectedNumber) throws ApiException {
        var request = new EuEntityPermissionsQueryRequestBuilder()
                .withAuthorizedFingerprintIdentifier(subjectContext)
                .build();

        var response = defaultKsefClient.searchGrantedEuEntityPermissions(request, 0, 10);

        Assertions.assertEquals(expectedNumber, response.getPermissions().size());

        return response.getPermissions()
                .stream()
                .map(EuEntityPermission::getId)
                .toList();
    }

    private String grantEuEntityRepresentativePermission(String subjectNip) throws ApiException {
        var request = new GrantEUEntityRepresentativePermissionsRequestBuilder()
                .withSubjectIdentifier(new SubjectIdentifier(SubjectIdentifierType.FINGERPRINT, subjectNip))
                .withPermissions(List.of(EuEntityPermissionType.INVOICEWRITE))
                .withDescription("e2e test")
                .build();

        var response = defaultKsefClient.grantsPermissionEUEntityRepresentative(request);

        Assertions.assertNotNull(response);

        return response.getOperationReferenceNumber();
    }

    private Boolean isOperationFinish(String referenceNumber) throws ApiException {
        PermissionStatusInfo operations = defaultKsefClient.permissionOperationStatus(referenceNumber);
        return operations != null && operations.getStatus().getCode() == 200;
    }
}
