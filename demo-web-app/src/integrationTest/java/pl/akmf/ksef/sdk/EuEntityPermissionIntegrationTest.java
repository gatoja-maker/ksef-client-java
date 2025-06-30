package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.euentity.EuEntityPermissionsQueryRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.permission.euentity.GrantEUEntityPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.euentity.ContextIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.euentity.ContextIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.euentity.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.euentity.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;

class EuEntityPermissionIntegrationTest extends BaseIntegrationTest {

    @Test
    void euEntityPermissionE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantEuEntityPermission(subjectNip, contextNip);

        Thread.sleep(5000);

        checkPermissionStatus(grantReferenceNumber);

        checkGrantedPermission(subjectNip, 4);
    }

    private void checkGrantedPermission(String subjectContext, int expectedNumber) throws ApiException {
        var request = new EuEntityPermissionsQueryRequestBuilder()
                .withAuthorizedFingerprintIdentifier(subjectContext)
                .build();

        var response = defaultKsefClient.searchGrantedEuEntityPermissions(request, 0, 10);

        Assertions.assertEquals(expectedNumber, response.getPermissions().size());
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

    private void checkPermissionStatus(String referenceNumber) throws ApiException {
        var status = defaultKsefClient.operations(referenceNumber);

        Assertions.assertNotNull(status);
        Assertions.assertEquals(200, status.getStatus().getCode());
    }
}
