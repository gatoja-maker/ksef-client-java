package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.euentity.EuEntityPermissionsQueryRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.permission.euentityrepresentative.GrantEUEntityRepresentativePermissionsRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.euentity.EuEntityPermissionType;
import pl.akmf.ksef.sdk.client.model.permission.euentity.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.euentity.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.util.List;

class EuEntityRepresentativeE2EPermissionTest extends BaseIntegrationTest {

    @Test
    void euEntityRepresentativeE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP_VAT_UE, contextNip);

        var grantReferenceNumber = grantEuEntityRepresentativePermission(subjectNip);

        Thread.sleep(5000);

        checkPermissionStatus(grantReferenceNumber);

        checkGrantedPermission(subjectNip, 1);
    }

    private void checkGrantedPermission(String subjectContext, int expectedNumber) throws ApiException {
        var request = new EuEntityPermissionsQueryRequestBuilder()
                .withAuthorizedFingerprintIdentifier(subjectContext)
                .build();

        var response = defaultKsefClient.searchGrantedEuEntityPermissions(request, 0, 10);

        Assertions.assertEquals(expectedNumber, response.getPermissions().size());
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

    private void checkPermissionStatus(String referenceNumber) throws ApiException {
        var status = defaultKsefClient.operations(referenceNumber);

        Assertions.assertNotNull(status);
        Assertions.assertEquals(200, status.getStatus().getCode());
    }
}
