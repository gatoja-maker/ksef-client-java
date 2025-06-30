package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.entity.GrantEntityPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.entity.SubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.entity.SubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.entity.EntityPermission;
import pl.akmf.ksef.sdk.client.model.permission.entity.EntityPermissionType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.util.List;

class EntityPermissionIntegrationTest extends BaseIntegrationTest {

    //TODO NIE ZWRACA Ról
    @Test
    void entityPermissionE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantPermission(subjectNip);

        Thread.sleep(5000);

        checkPermissionStatus(grantReferenceNumber);

        searchGrantedRole(2);
    }

    private void searchGrantedRole(int expectedRole) throws ApiException {
        var response = defaultKsefClient.searchEntityInvoiceRoles(0, 10);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedRole, response.getRoles().size());
    }

    private void checkPermissionStatus(String referenceNumber) throws ApiException {
        var status = defaultKsefClient.operations(referenceNumber);

        Assertions.assertNotNull(status);
        Assertions.assertEquals(200, status.getStatus().getCode());
    }

    private String grantPermission(String targetNip) throws ApiException {
        var request = new GrantEntityPermissionsRequestBuilder()
                .withPermissions(List.of(
                        new EntityPermission(EntityPermissionType.INVOICEREAD, true),
                        new EntityPermission(EntityPermissionType.INVOICEREAD, false)))
                .withDescription("EWE test grant")
                .withSubjectIdentifier(new SubjectIdentifier(SubjectIdentifierType.NIP, targetNip))
                .build();

        var response = defaultKsefClient.grantsPermissionEntity(request);
        Assertions.assertNotNull(response);

        return response.getOperationReferenceNumber();
    }
}
