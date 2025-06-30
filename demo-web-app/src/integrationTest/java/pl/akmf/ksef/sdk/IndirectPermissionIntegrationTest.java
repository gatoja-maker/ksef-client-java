package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.indirect.GrantIndirectEntityPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.permission.subunit.SubunitPermissionsQueryRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.indirect.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.indirect.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.util.List;

import static pl.akmf.ksef.sdk.client.model.permission.indirect.IndirectPermissionType.INVOICEWRITE;

class IndirectPermissionIntegrationTest extends BaseIntegrationTest {

    @Test
    void indirectPermissionE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantIndirectPermission(subjectNip);

        Thread.sleep(5000);

        checkPermissionStatus(grantReferenceNumber);
        authWithCustomNip(subjectNip, ContextIdentifierTypeEnum.NIP, subjectNip);

        checkGrantedPermission(4);
    }

    private void checkGrantedPermission(int expected) throws ApiException {
        var request = new SubunitPermissionsQueryRequestBuilder()
                .build();

        var response = defaultKsefClient.searchSubunitAdminPermissions(request, 0, 10);
        Assertions.assertEquals(expected, response.getPermissions().size());
    }

    private String grantIndirectPermission(String subjectNip) throws ApiException {
        var request = new GrantIndirectEntityPermissionsRequestBuilder()
                .withSubjectIdentifier(new SubjectIdentifier(SubjectIdentifierType.NIP, subjectNip))
                .withTargetIdentifier(null)
                .withPermissions(List.of(INVOICEWRITE))
                .withDescription("e2e test")
                .build();

        var response = defaultKsefClient.grantsPermissionIndirectEntity(request);
        Assertions.assertNotNull(response);
        return response.getOperationReferenceNumber();
    }

    private void checkPermissionStatus(String referenceNumber) throws ApiException {
        var status = defaultKsefClient.operations(referenceNumber);

        Assertions.assertNotNull(status);
        Assertions.assertEquals(200, status.getStatus().getCode());
    }
}
