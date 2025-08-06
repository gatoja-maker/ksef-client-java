package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.euentity.EuEntityPermissionsQueryRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.permission.euentity.GrantEUEntityPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.PermissionStatusInfo;
import pl.akmf.ksef.sdk.client.model.permission.euentity.ContextIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.euentity.ContextIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.euentity.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.euentity.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.search.EuEntityPermission;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class EuEntityPermissionIntegrationTest extends BaseIntegrationTest {

    @Test
    void euEntityPermissionE2EIntegrationTest() throws JAXBException, IOException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantEuEntityPermission(subjectNip, contextNip);

        await().atMost(5, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> isOperationFinish(grantReferenceNumber));

        List<String> permission = checkPermission(subjectNip, 4);

        permission.forEach(e -> {
            var revokeReferenceNumber = revokePermission(e);

            await().atMost(30, SECONDS)
                    .pollInterval(2, SECONDS)
                    .until(() -> isOperationFinish(revokeReferenceNumber));
        });

        checkPermission(subjectNip, 0);
    }

    private List<String> checkPermission(String subjectContext, int expectedNumber) throws ApiException {
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

    private String revokePermission(String operationId) {
        try {
            return defaultKsefClient.revokeCommonPermission(operationId).getOperationReferenceNumber();
        } catch (ApiException e) {
            Assertions.fail(e.getMessage());
        }
        return null;
    }

    private String grantEuEntityPermission(String subjectNip, String euContext) throws ApiException {
        var request = new GrantEUEntityPermissionsRequestBuilder()
                .withSubject(new SubjectIdentifier(SubjectIdentifierType.FINGERPRINT, subjectNip))
                .withContext(new ContextIdentifier(ContextIdentifierType.NIPVATUE, euContext))
                .withDescription("e2e test")
                .build();

        var response = defaultKsefClient.grantsPermissionEUEntity(request);

        Assertions.assertNotNull(response);

        return response.getOperationReferenceNumber();
    }

    private Boolean isOperationFinish(String referenceNumber) throws ApiException {
        PermissionStatusInfo operations = defaultKsefClient.permissionOperationStatus(referenceNumber);
        return operations != null && operations.getStatus().getCode() == 200;
    }
}
