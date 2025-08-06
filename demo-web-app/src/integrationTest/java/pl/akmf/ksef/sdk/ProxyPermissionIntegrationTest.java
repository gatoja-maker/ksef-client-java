package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.proxy.GrantProxyEntityPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.proxy.ProxyEntityPermissionType;
import pl.akmf.ksef.sdk.client.model.permission.proxy.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.proxy.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.search.EntityAuthorizationGrant;
import pl.akmf.ksef.sdk.client.model.permission.search.EntityAuthorizationPermissionsQueryRequest;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class ProxyPermissionIntegrationTest extends BaseIntegrationTest {

    @Test
    void proxyPermissionE2EIntegrationTest() throws JAXBException, IOException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantPermission(subjectNip);

        await().atMost(15, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> isPermissionStatusReady(grantReferenceNumber));

        var permission = searchRole(1);

        permission.forEach(e -> {
            var revokeReferenceNumber = revokePermission(e);

            await().atMost(30, SECONDS)
                    .pollInterval(2, SECONDS)
                    .until(() -> isPermissionStatusReady(revokeReferenceNumber));
        });
        searchRole(0);
    }

    private Boolean isPermissionStatusReady(String grantReferenceNumber) throws ApiException {
        var status = defaultKsefClient.permissionOperationStatus(grantReferenceNumber);
        return status != null && status.getStatus().getCode() == 200;
    }

    private String revokePermission(String operationId) {
        try {
            return defaultKsefClient.revokeAuthorizationsPermission(operationId).getOperationReferenceNumber();
        } catch (ApiException e) {
            Assertions.fail(e.getMessage());
        }
        return null;
    }

    private String grantPermission(String subjectNip) throws ApiException {
        var request = new GrantProxyEntityPermissionsRequestBuilder()
                .withSubjectIdentifier(new SubjectIdentifier(SubjectIdentifierType.NIP, subjectNip))
                .withPermission(ProxyEntityPermissionType.RRINVOICING)
                .withDescription("e2e test")
                .build();

        var response = defaultKsefClient.grantsPermissionsProxyEntity(request);
        Assertions.assertNotNull(response);
        return response.getOperationReferenceNumber();
    }

    private List<String> searchRole(int expectedRole) throws ApiException {
        var response = defaultKsefClient.searchEntityAuthorizationGrants(new EntityAuthorizationPermissionsQueryRequest(), 0, 10);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedRole, response.getAuthorizationGrants().size());

        return response.getAuthorizationGrants()
                .stream()
                .map(EntityAuthorizationGrant::getId)
                .toList();
    }
}
