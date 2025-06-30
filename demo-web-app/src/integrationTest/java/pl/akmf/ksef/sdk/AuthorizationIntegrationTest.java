package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.auth.AuthKsefTokenRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.tokens.GenerateTokenRequestBuilder;
import pl.akmf.ksef.sdk.api.services.DefaultCryptographyService;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.auth.ContextIdentifier;
import pl.akmf.ksef.sdk.client.model.auth.ContextIdentifierType;
import pl.akmf.ksef.sdk.client.model.auth.GenerateTokenRequest;
import pl.akmf.ksef.sdk.client.model.auth.GenerateTokenResponse;
import pl.akmf.ksef.sdk.client.model.auth.TokenPermissionType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

class AuthorizationIntegrationTest extends BaseIntegrationTest {
    private static final String CONTEXT_NIP = "1112223344";

    @Test
    void refreshTokenE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        // given
        var token = authWithCustomNip(CONTEXT_NIP, ContextIdentifierTypeEnum.NIP, CONTEXT_NIP);

        //when
        var refreshTokenResult = defaultKsefClient.refreshAccessToken(token.refreshToken());

        //then
        Assertions.assertNotNull(refreshTokenResult);
        Assertions.assertNotEquals(token.authToken(), refreshTokenResult.getAccessToken().getToken());
    }

    @Test
    void initAuthByTokenE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        authWithCustomNip(CONTEXT_NIP, ContextIdentifierTypeEnum.NIP, CONTEXT_NIP);

        var ksefToken = getKSeFToken();

        var challenge = defaultKsefClient.getAuthChallenge();

        var tokenWithTimestamp =
                ksefToken.getToken() + "|" + challenge.getTimestamp().toInstant().toEpochMilli();
        var encryptedToken =
                new DefaultCryptographyService(defaultKsefClient).encryptKsefTokenWithRSAUsingPublicKey(tokenWithTimestamp.getBytes(StandardCharsets.UTF_8));

        var authTokenRequest = new AuthKsefTokenRequestBuilder()
                .withChallenge(challenge.getChallenge())
                .withContextIdentifier(new ContextIdentifier(ContextIdentifierType.NIP, CONTEXT_NIP))
                .withEncryptedToken(Base64.getEncoder().encodeToString(encryptedToken))
                .build();

        var response = defaultKsefClient.authorizeByKSeFToken(authTokenRequest);

        Thread.sleep(5000);

        var authStatus = defaultKsefClient.getAuthStatus(response.getReferenceNumber());

        Assertions.assertEquals(200, authStatus.getStatus().getCode());

        var tokenResponse = defaultKsefClient.redeemToken();
        Assertions.assertNotNull(tokenResponse);
    }

    private GenerateTokenResponse getKSeFToken() throws ApiException {
        GenerateTokenRequest request = new GenerateTokenRequestBuilder()
                .withDescription("test description")
                .withPermissions(List.of(TokenPermissionType.INVOICEREAD, TokenPermissionType.INVOICEWRITE, TokenPermissionType.CREDENTIALSREAD))
                .build();
        return defaultKsefClient.generateKsefToken(request);
    }
}
