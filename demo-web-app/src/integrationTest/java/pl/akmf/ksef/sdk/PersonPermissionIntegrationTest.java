package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.permission.person.GrantPersonPermissionsRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.permission.person.PersonPermissionsQueryRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.permission.PermissionStatusInfo;
import pl.akmf.ksef.sdk.client.model.permission.person.PersonPermissionType;
import pl.akmf.ksef.sdk.client.model.permission.person.PersonPermissionsSubjectIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.person.PersonPermissionsSubjectIdentifierType;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermission;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermissionsAuthorizedIdentifier;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermissionsAuthorizedIdentifierType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class PersonPermissionIntegrationTest extends BaseIntegrationTest {

    @Test
    void personPermissionE2EIntegrationTest() throws JAXBException, IOException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        String subjectNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var grantReferenceNumber = grantPermission(subjectNip);

        await().atMost(5, SECONDS)
                .pollInterval(1, SECONDS)
                .until(() -> isOperationFinish(grantReferenceNumber));

        var permission = searchPermission(subjectNip, 1);

        permission.forEach(e -> {
            var revokeReferenceNumber = revokePermission(e);

            await().atMost(30, SECONDS)
                    .pollInterval(2, SECONDS)
                    .until(() -> isOperationFinish(revokeReferenceNumber));
        });
        searchPermission(subjectNip, 0);
    }

    private List<String> searchPermission(String subjectNip, int expected) throws ApiException {
        var request = new PersonPermissionsQueryRequestBuilder()
                .withAuthorizedIdentifier(new PersonPermissionsAuthorizedIdentifier(PersonPermissionsAuthorizedIdentifierType.NIP, subjectNip))
                .build();

        var response = defaultKsefClient.searchGrantedPersonPermissions(request, 0, 10);
        Assertions.assertEquals(expected, response.getPermissions().size());

        if (response.getPermissions().isEmpty()) {
            return Collections.emptyList();
        }

        return response.getPermissions()
                .stream()
                .map(PersonPermission::getId)
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

    private Boolean isOperationFinish(String referenceNumber) throws ApiException {
        PermissionStatusInfo operations = defaultKsefClient.permissionOperationStatus(referenceNumber);
        return operations != null && operations.getStatus().getCode() == 200;
    }
}


