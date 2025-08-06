package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.subunit.GrantSubUnitPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.permission.subunit.SubunitPermissionsQueryRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.search.SubunitPermission;
import pl.akmf.ksef.sdk.client.model.permission.search.SubunitPermissionsSubunitIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.search.SubunitPermissionsSubunitIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.subunit.ContextIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.subunit.ContextIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.subunit.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.subunit.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class SubUnitPermissionIntegrationTest extends BaseIntegrationTest {

    @Test
    void subUnitPermissionE2EIntegrationTest() throws JAXBException, IOException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        String subUnitNip = contextNip + "-11111";
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantPermission(subjectNip, subUnitNip);

        await()
                .atMost(Duration.ofSeconds(30))
                .pollInterval(Duration.ofSeconds(5))
                .until(() -> isPermissionStatusReady(grantReferenceNumber));

        var permission = searchGrantedRole(subUnitNip, 1);

        permission.forEach(e -> {
            var revokeReferenceNumber = revokePermission(e);

            await().atMost(30, SECONDS)
                    .pollInterval(5, SECONDS)
                    .until(() -> isPermissionStatusReady(revokeReferenceNumber));
        });

        searchGrantedRole(subUnitNip, 0);
    }

    private Boolean isPermissionStatusReady(String grantReferenceNumber) {
        try {
            var status = defaultKsefClient.permissionOperationStatus(grantReferenceNumber);
            return status != null && status.getStatus().getCode() == 200;
        } catch (ApiException e) {
            return false;
        }
    }

    private List<String> searchGrantedRole(String subUnitNip, int expectedSize) throws ApiException {
        var request = new SubunitPermissionsQueryRequestBuilder()
                .withSubunitIdentifier(new SubunitPermissionsSubunitIdentifier(SubunitPermissionsSubunitIdentifierType.INTERNALID, subUnitNip))
                .build();

        var response = defaultKsefClient.searchSubunitAdminPermissions(request, 0, 10);
        Assertions.assertEquals(expectedSize, response.getPermissions().size());

        return response.getPermissions()
                .stream()
                .map(SubunitPermission::getId)
                .toList();
    }

    private String grantPermission(String subjectNip, String contextNip) throws ApiException {
        var request = new GrantSubUnitPermissionsRequestBuilder()
                .withSubjectIdentifier(new SubjectIdentifier(SubjectIdentifierType.NIP, subjectNip))
                .withContextIdentifier(new ContextIdentifier(ContextIdentifierType.INTERNALID, contextNip))
                .withDescription("e2e test")
                .build();

        var response = defaultKsefClient.grantsPermissionSubUnit(request);
        Assertions.assertNotNull(response);
        return response.getOperationReferenceNumber();
    }

    private String revokePermission(String operationId) {
        try {
            return defaultKsefClient.revokeCommonPermission(operationId).getOperationReferenceNumber();
        } catch (ApiException e) {
            Assertions.fail(e.getMessage());
        }
        return null;
    }
}

