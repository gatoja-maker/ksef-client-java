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
import pl.akmf.ksef.sdk.client.model.auth.EncryptionMethod;
import pl.akmf.ksef.sdk.client.model.auth.GenerateTokenRequest;
import pl.akmf.ksef.sdk.client.model.auth.GenerateTokenResponse;
import pl.akmf.ksef.sdk.client.model.auth.TokenPermissionType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

class AuthorizationIntegrationTest extends BaseIntegrationTest {
    private static final String CONTEXT_NIP = "1112223344";

    @Test
    void refreshTokenE2EIntegrationTest() throws JAXBException, IOException, ApiException {
        // given
        var token = authWithCustomNip(CONTEXT_NIP, ContextIdentifierTypeEnum.NIP, CONTEXT_NIP);

        //when
        var refreshTokenResult = defaultKsefClient.refreshAccessToken(token.refreshToken());

        //then
        Assertions.assertNotNull(refreshTokenResult);
        Assertions.assertNotEquals(token.authToken(), refreshTokenResult.getAccessToken().getToken());
    }

    @Test
    void initAuthByTokenE2EIntegrationTestECDsa() throws JAXBException, IOException, ApiException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, CertificateException {
        initAuthByToken(EncryptionMethod.ECDsa);
    }

    @Test
    void initAuthByTokenE2EIntegrationTestRSA() throws JAXBException, IOException, ApiException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, CertificateException {
        initAuthByToken(EncryptionMethod.Rsa);
    }

    private void initAuthByToken(EncryptionMethod encryptionMethod) throws JAXBException, IOException, ApiException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, CertificateException {
        authWithCustomNip(CONTEXT_NIP, ContextIdentifierTypeEnum.NIP, CONTEXT_NIP);
        var ksefToken = getKSeFToken();

        var challenge = defaultKsefClient.getAuthChallenge();

        var tokenWithTimestamp = ksefToken.getToken() + "|" + challenge.getTimestamp().toEpochMilli();
        byte[] encryptedToken;
        switch (encryptionMethod) {
            case Rsa -> encryptedToken = new DefaultCryptographyService(defaultKsefClient)
                    .encryptKsefTokenWithRSAUsingPublicKey(tokenWithTimestamp.getBytes(StandardCharsets.UTF_8));
            case ECDsa -> encryptedToken = new DefaultCryptographyService(defaultKsefClient)
                    .encryptWithECDsaUsingPublicKey(tokenWithTimestamp.getBytes(StandardCharsets.UTF_8));
            default -> throw new IllegalArgumentException();
        }

        var authTokenRequest = new AuthKsefTokenRequestBuilder()
                .withChallenge(challenge.getChallenge())
                .withContextIdentifier(new ContextIdentifier(ContextIdentifierType.NIP, CONTEXT_NIP))
                .withEncryptedToken(Base64.getEncoder().encodeToString(encryptedToken))
                .build();

        var response = defaultKsefClient.authorizeByKSeFToken(authTokenRequest);

        await().atMost(30, SECONDS)
                .pollInterval(2, SECONDS)
                .until(() -> isAuthStatusReady(response.getReferenceNumber()));

        var authStatus = defaultKsefClient.getAuthStatus(response.getReferenceNumber());

        Assertions.assertEquals(200, authStatus.getStatus().getCode());

        var tokenResponse = defaultKsefClient.redeemToken();
        Assertions.assertNotNull(tokenResponse);
    }

    private Boolean isAuthStatusReady(String referenceNumber) throws ApiException {
        var authStatus = defaultKsefClient.getAuthStatus(referenceNumber);
        return authStatus != null && authStatus.getStatus().getCode() == 200;
    }

    private GenerateTokenResponse getKSeFToken() throws ApiException {
        GenerateTokenRequest request = new GenerateTokenRequestBuilder()
                .withDescription("test description")
                .withPermissions(List.of(TokenPermissionType.INVOICEREAD, TokenPermissionType.INVOICEWRITE, TokenPermissionType.CREDENTIALSREAD))
                .build();
        return defaultKsefClient.generateKsefToken(request);
    }
}
