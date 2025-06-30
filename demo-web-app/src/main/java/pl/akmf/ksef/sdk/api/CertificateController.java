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

    /**
     * Zwraca limity związane z certyfikatami wewnętrznymi
     *
     * @return CertificateLimitsResponse
     * @throws pl.akmf.ksef.sdk.ApiException if fails to make API call
     */
    @GetMapping(value = "/limits")
    public CertificateLimitsResponse getCertificateLimit() throws ApiException {
        return ksefClient.getCertificateLimits();
    }

    /**
     * Zwraca informacje służące do generowania certyfiktu wewnętrznego na postawie tokenu JWT
     *
     * @return CertificateEnrollmentResponse
     * @throws pl.akmf.ksef.sdk.ApiException if fails to make API call
     */
    @GetMapping(value = "/enrollment-info")
    public CertificateEnrollmentsInfoResponse getCertificateEnrollmentInfo() throws ApiException {
        return ksefClient.getCertificateEnrollmentInfo();
    }

    /**
     * Rozpoczęcie procesu generowania certyfikatu wewnętrznego
     *
     * @return CertificateEnrollmentResponse
     * @throws pl.akmf.ksef.sdk.ApiException if fails to make API call
     */
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

    /**
     * Sprawdzenie statusu generowania certyfikatu wewnetrznego
     *
     * @param referenceNumber numer referencyjny procesu
     * @return CertificateEnrollmentStatusResponse
     * @throws pl.akmf.ksef.sdk.ApiException if fails to make API call
     */
    @GetMapping(value = "/enrollment-status/{referenceNumber}")
    public CertificateEnrollmentStatusResponse getCertificateEnrollmentStatus(@PathVariable String referenceNumber) throws ApiException {
        return ksefClient.getCertificateEnrollmentStatus(referenceNumber);
    }

    /**
     * Pobieranie listy metadanych certyfikatów
     *
     * @return CertificateEnrollmentStatusResponse
     * @throws pl.akmf.ksef.sdk.ApiException if fails to make API call
     */
    @PostMapping(value = "/certListMetadata")
    public CertificateMetadataListResponse getMetadataCertificateList(@RequestBody CertificateMetadataListRequest certificateMetadataListRequest) throws ApiException {
        return ksefClient.getCertificateMetadataList(certificateMetadataListRequest, 10, 0);
    }

    /**
     * Pobieranie listy certyfikatów
     *
     * @return CertificateEnrollmentStatusResponse
     * @throws pl.akmf.ksef.sdk.ApiException if fails to make API call
     */
    @PostMapping(value = "/retrieve")
    public CertificateListResponse getCertificateList(@RequestBody CertificateListRequest request) throws ApiException {
        return ksefClient.getCertificateList(request);
    }

    /**
     * Unieważnienie certyfikatu wewnętrznego
     *
     * @throws pl.akmf.ksef.sdk.ApiException if fails to make API call
     */
    @PostMapping(value = "/revoke/{serialNumber}")
    public void revokeCertificate(@PathVariable String serialNumber, @RequestBody CertificateRevokeRequest request) throws ApiException {
        ksefClient.revokeCertificate(request, serialNumber);
    }
}
