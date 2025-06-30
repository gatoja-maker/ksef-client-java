package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.subunit.GrantSubUnitPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.permission.subunit.SubunitPermissionsQueryRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.search.SubunitPermissionsSubunitIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.search.SubunitPermissionsSubunitIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.subunit.ContextIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.subunit.ContextIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.subunit.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.subunit.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;

class SubUnitPermissionIntegrationTest extends BaseIntegrationTest {

    @Test
    void subUnitPermissionE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();

        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantPermission(subjectNip, contextNip + "-11111");

        Thread.sleep(5000);

        checkPermissionStatus(grantReferenceNumber);
        authWithCustomNip(subjectNip, ContextIdentifierTypeEnum.NIP, subjectNip);

        //TODO ALWAYS RETURN EMPTY
        searchGrantedRole(contextNip);
    }

    private void searchGrantedRole(String contextNip) throws ApiException {
        var request = new SubunitPermissionsQueryRequestBuilder()
                .withSubunitIdentifier(new SubunitPermissionsSubunitIdentifier(SubunitPermissionsSubunitIdentifierType.NIP, contextNip + "-11111"))
                .build();

        var response = defaultKsefClient.searchSubunitAdminPermissions(request, 0, 10);
        Assertions.assertEquals(1, response.getPermissions().size());
    }

    private void checkPermissionStatus(String referenceNumber) throws ApiException {
        var status = defaultKsefClient.operations(referenceNumber);

        Assertions.assertNotNull(status);
        Assertions.assertEquals(200, status.getStatus().getCode());
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
}

