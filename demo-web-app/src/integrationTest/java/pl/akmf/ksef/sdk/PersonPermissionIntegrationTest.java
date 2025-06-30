package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.person.GrantPersonPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.permission.person.PersonPermissionsQueryRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.person.PersonPermissionType;
import pl.akmf.ksef.sdk.client.model.permission.person.PersonPermissionsSubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.person.PersonPermissionsSubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermissionsAuthorizedIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermissionsAuthorizedIdentifierType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.util.List;

class PersonPermissionIntegrationTest extends BaseIntegrationTest {

    @Test
    void personPermissionE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantPermission(subjectNip);

        Thread.sleep(5000);

        checkPermissionStatus(grantReferenceNumber);

        checkGrantedPermission(subjectNip, 1);
    }

    private void checkGrantedPermission(String subjectNip, int expected) throws ApiException {
        var request = new PersonPermissionsQueryRequestBuilder()
                .withAuthorizedIdentifier(new PersonPermissionsAuthorizedIdentifier(PersonPermissionsAuthorizedIdentifierType.NIP, subjectNip))
                .build();

        var response = defaultKsefClient.searchGrantedPersonPermissions(request, 0, 10);
        Assertions.assertEquals(expected, response.getPermissions().size());
    }

    private String grantPermission(String subjectNip) throws ApiException {
        var request = new GrantPersonPermissionsRequestBuilder()
                .withSubjectIdentifier(new PersonPermissionsSubjectIdentifier(PersonPermissionsSubjectIdentifierType.NIP, subjectNip))
                .withPermissions(List.of(PersonPermissionType.INVOICEWRITE))
                .withDescription("e2e test")
                .build();

        var response = defaultKsefClient.grantsPermissionPerson(request);
        Assertions.assertNotNull(response);
        return response.getOperationReferenceNumber();
    }

    private void checkPermissionStatus(String referenceNumber) throws ApiException {
        var status = defaultKsefClient.operations(referenceNumber);

        Assertions.assertNotNull(status);
        Assertions.assertEquals(200, status.getStatus().getCode());
    }
}


