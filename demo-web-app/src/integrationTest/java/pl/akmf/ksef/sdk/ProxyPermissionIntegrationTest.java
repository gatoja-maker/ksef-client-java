package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.proxy.GrantProxyEntityPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.proxy.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.proxy.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.proxy.ProxyEntityPermissionType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;

public class ProxyPermissionIntegrationTest extends BaseIntegrationTest {

    @Test
    void proxyPermissionE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantPermission(subjectNip);

        Thread.sleep(5000);

        checkPermissionStatus(grantReferenceNumber);
        authWithCustomNip(subjectNip, ContextIdentifierTypeEnum.NIP, subjectNip);

        //TODO ALWAYS RETURN EMPTY
//        searchGrantedRole(1);
    }

    private void checkPermissionStatus(String referenceNumber) throws ApiException {
        var status = defaultKsefClient.operations(referenceNumber);

        Assertions.assertNotNull(status);
        Assertions.assertEquals(200, status.getStatus().getCode());
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

    private void searchGrantedRole(int expectedRole) throws ApiException {
        var response = defaultKsefClient.searchEntityInvoiceRoles(0, 10);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedRole, response.getRoles().size());
    }
}
