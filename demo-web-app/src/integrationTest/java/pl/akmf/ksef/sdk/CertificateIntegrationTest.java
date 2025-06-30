package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.certificate.RevokeCertificateRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.certificate.SendCertificateEnrollmentRequestBuilder;
import pl.akmf.ksef.sdk.api.services.DefaultCryptographyService;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentStatusResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentsInfoResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateListRequest;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateRevocationReason;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

class CertificateIntegrationTest extends BaseIntegrationTest {

    @Test
    void certificateE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        getCertificateLimitAsync();

        var enrollmentInfo = getEnrolmentInfo();

        var referenceNumber = sendEnrollment(enrollmentInfo);

        Thread.sleep(10000);
        var enrolmentStatus = getEnrolmentStatus(referenceNumber);

        getCertificateList(enrolmentStatus.getCertificateSerialNumber());

        revokeCertificate(enrolmentStatus.getCertificateSerialNumber());

        getMedataCertificateList(enrolmentStatus.getCertificateSerialNumber());
    }

    private void getMedataCertificateList(String certificateSerialNumber) throws ApiException {
        var response = defaultKsefClient.getCertificateMetadataList(null, 10, 0);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getCertificates().getFirst().getCertificateSerialNumber(), certificateSerialNumber);
    }

    private void revokeCertificate(String serialNumber) throws ApiException {
        var request = new RevokeCertificateRequestBuilder()
                .withRevocationReason(CertificateRevocationReason.KEYCOMPROMISE)
                .build();

        defaultKsefClient.revokeCertificate(request, serialNumber);
    }

    private void getCertificateList(String certificateSerialNumber) throws ApiException {
        var certificateResponse =
                defaultKsefClient.getCertificateList(new CertificateListRequest(List.of(certificateSerialNumber)));

        Assertions.assertNotNull(certificateResponse);
        Assertions.assertEquals(1, certificateResponse.getCertificates().size());
    }

    private CertificateEnrollmentStatusResponse getEnrolmentStatus(String referenceNumber) throws ApiException {
        var response = defaultKsefClient.getCertificateEnrollmentStatus(referenceNumber);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatus().getCode());
        return response;
    }

    private String sendEnrollment(CertificateEnrollmentsInfoResponse enrollmentInfo) throws ApiException {
        var csr = new DefaultCryptographyService(defaultKsefClient).generateCsr(enrollmentInfo);

        var request = new SendCertificateEnrollmentRequestBuilder()
                .withValidFrom(OffsetDateTime.now())
                .withCsr(csr.csr())
                .withCertificateName("certificate")
                .build();

        var response = defaultKsefClient.sendCertificateEnrollment(request);
        Assertions.assertNotNull(response);

        return response.getReferenceNumber();
    }

    private CertificateEnrollmentsInfoResponse getEnrolmentInfo() throws ApiException {
        var response = defaultKsefClient.getCertificateEnrollmentInfo();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getOrganizationIdentifier());

        return response;
    }

    private void getCertificateLimitAsync() throws ApiException {
        var response = defaultKsefClient.getCertificateLimits();

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.getCanRequest());
    }
}
