package pl.akmf.ksef.sdk.api;

import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.api.builders.auth.AuthTokenRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.auth.AuthTokenRequestSerializer;
import pl.akmf.ksef.sdk.api.builders.certificate.CertificateBuilders;
import pl.akmf.ksef.sdk.api.services.DefaultCertificateGenerator;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.api.services.DefaultSignatureService;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationOperationStatusResponse;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationTokenRefreshResponse;
import pl.akmf.ksef.sdk.client.model.certificate.SelfSignedCertificate;
import pl.akmf.ksef.sdk.client.model.xml.AuthTokenRequest;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.client.model.xml.SubjectIdentifierTypeEnum;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final DefaultKsefClient ksefClient;

    /**
     * Cały process autoryzacji krok po kroku
     * Zwraca token JWT oraz refreshToken
     * Inicjalizacja przyk�dowego identyfikatora - w tym przypadku NIP.
     *
     * @param context nip kontekstu w którym następuje próba uwierzytelnienia
     * @return AuthenticationOperationStatusResponse
     * @throws ApiException if fails to make API call
     */
    @PostMapping(value = "/auth-step-by-step/{context}")
    public AuthenticationOperationStatusResponse authStepByStep(@PathVariable String context) throws ApiException, JAXBException, IOException, InterruptedException {
        //wykonanie auth challenge
        var challenge = ksefClient.getAuthChallenge();

        //xml niezbędny do uwierzytelnienia
        AuthTokenRequest authTokenRequest = new AuthTokenRequestBuilder()
                .withChallenge(challenge.getChallenge())
                .withContext(ContextIdentifierTypeEnum.NIP, context)
                .withSubjectType(SubjectIdentifierTypeEnum.CERTIFICATE_SUBJECT)
                .build();

        var xml = AuthTokenRequestSerializer.authTokenRequestSerializer(authTokenRequest);

        //wygenerowanie certyfikatu oraz klucza prywatnego
        var x500 = new CertificateBuilders()
                .buildForOrganization("Kowalski sp. z o.o", "VATPL-" + context, "Kowalski");

        SelfSignedCertificate cert = new DefaultCertificateGenerator().generateSelfSignedCertificate(x500);

        //podpisanie xml wygenerowanym certyfikatem oraz kluczem prywatnym
        var signedXml = new DefaultSignatureService().sign(xml.getBytes(), cert.certificate(), cert.getPrivateKey());

        // Przesłanie podpisanego XML do systemu KSeF
        var submitAuthTokenResponse = ksefClient.submitAuthTokenRequest(signedXml);

        //Czekanie na zakończenie procesu
        Thread.sleep(2000);

        //Sprawdzenie statusu operacki
        var checkAuthStatus = ksefClient.getAuthStatus(submitAuthTokenResponse.getReferenceNumber());

        //pobranie tokenów
        return ksefClient.redeemToken();
    }

    /**
     * Proces odświeżania tokenu jwt
     * Zwraca nowy token JWT oraz refreshToken
     *
     * @param refreshToken token służący do odświeżania
     * @return AuthenticationTokenRefreshResponse
     * @throws ApiException if fails to make API call
     */
    @GetMapping(value = "/refreshToken/{refreshToken}")
    public AuthenticationTokenRefreshResponse refreshToken(@PathVariable String refreshToken) throws Exception {
        return ksefClient.refreshAccessToken(refreshToken);
    }

    /**
     * Proces unieważniania tokenu jwt
     *
     * @return void
     * @throws ApiException if fails to make API call
     */
    @GetMapping(value = "/revoke")
    public void revokeToken() throws Exception {
        ksefClient.revokeAccessToken();
    }
}