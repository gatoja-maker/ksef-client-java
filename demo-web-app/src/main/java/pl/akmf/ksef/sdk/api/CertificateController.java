package pl.akmf.ksef.sdk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.api.builders.certificate.SendCertificateEnrollmentRequestBuilder;
import pl.akmf.ksef.sdk.api.services.DefaultCryptographyService;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentStatusResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentsInfoResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateLimitsResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateListRequest;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateListResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateMetadataListRequest;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateMetadataListResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateRevokeRequest;
import pl.akmf.ksef.sdk.client.model.certificate.CsrResult;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class CertificateController {
    private final DefaultKsefClient ksefClient;

    @GetMapping(value = "/limits")
    public CertificateLimitsResponse getCertificateLimit() throws ApiException {
        return ksefClient.getCertificateLimits();
    }

    @GetMapping(value = "/enrollment-info")
    public CertificateEnrollmentsInfoResponse getCertificateEnrollmentInfo() throws ApiException {
        return ksefClient.getCertificateEnrollmentInfo();
    }

    @PostMapping(value = "/send-enrollment")
    public CertificateEnrollmentResponse certificateEnrollment(@RequestBody CertificateEnrollmentsInfoResponse certificateEnrollmentsInfoResponse) throws Exception {
        DefaultCryptographyService defaultCryptographyService = new DefaultCryptographyService(ksefClient);

        //wygenerowanie CSR na podstawie otrzymanych wcześniej informacji
        CsrResult csr = defaultCryptographyService.generateCsr(certificateEnrollmentsInfoResponse);

        //stworzenie requesta
        var request = new SendCertificateEnrollmentRequestBuilder()
                .withCertificateName("certificateName")
                .withCsr(csr.csr())
                .withValidFrom(OffsetDateTime.now())
                .build();

        //rozpoczęcie procesu generowania certyfikatu
        return ksefClient.sendCertificateEnrollment(request);
    }

    @GetMapping(value = "/enrollment-status/{referenceNumber}")
    public CertificateEnrollmentStatusResponse getCertificateEnrollmentStatus(@PathVariable String referenceNumber) throws ApiException {
        return ksefClient.getCertificateEnrollmentStatus(referenceNumber);
    }

    @PostMapping(value = "/certListMetadata")
    public CertificateMetadataListResponse getMetadataCertificateList(@RequestBody CertificateMetadataListRequest certificateMetadataListRequest) throws ApiException {
        return ksefClient.getCertificateMetadataList(certificateMetadataListRequest, 10, 0);
    }

    @PostMapping(value = "/retrieve")
    public CertificateListResponse getCertificateList(@RequestBody CertificateListRequest request) throws ApiException {
        return ksefClient.getCertificateList(request);
    }

    @PostMapping(value = "/revoke/{serialNumber}")
    public void revokeCertificate(@PathVariable String serialNumber, @RequestBody CertificateRevokeRequest request) throws ApiException {
        ksefClient.revokeCertificate(request, serialNumber);
    }
}
