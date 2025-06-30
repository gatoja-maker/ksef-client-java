package pl.akmf.ksef.sdk.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.akmf.ksef.sdk.TestClientApplication;
import pl.akmf.ksef.sdk.api.builders.auth.AuthTokenRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.auth.AuthTokenRequestSerializer;
import pl.akmf.ksef.sdk.api.builders.certificate.CertificateBuilders;
import pl.akmf.ksef.sdk.api.services.DefaultCertificateGenerator;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.api.services.DefaultSignatureService;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.certificate.SelfSignedCertificate;
import pl.akmf.ksef.sdk.client.model.xml.AuthTokenRequest;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.client.model.xml.SubjectIdentifierTypeEnum;

import java.io.IOException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(
        classes = {TestClientApplication.class, IntegrationConfig.class},
        webEnvironment = RANDOM_PORT
)
@ContextConfiguration(classes = IntegrationConfig.class)
@EnableAutoConfiguration
@ActiveProfiles("DEV")
public abstract class BaseIntegrationTest {

    @LocalServerPort
    protected int port;
    protected int sleepTime = 350;

    @Autowired
    protected WireMockServer wireMock;

    @Autowired
    protected DefaultKsefClient defaultKsefClient;

    @BeforeEach
    public void prepare() {
        wireMock.start();
    }

    @AfterEach
    void clear() {
        wireMock.stop();
    }

    protected AuthTokensPair authWithCustomNip(String context,ContextIdentifierTypeEnum contextType, String subject) throws ApiException, JAXBException, IOException, InterruptedException {
        var challenge = defaultKsefClient.getAuthChallenge();

        AuthTokenRequest authTokenRequest = new AuthTokenRequestBuilder()
                .withChallenge(challenge.getChallenge())
                .withContext(contextType, context)
                .withSubjectType(SubjectIdentifierTypeEnum.CERTIFICATE_SUBJECT)
                .build();

        var xml = AuthTokenRequestSerializer.authTokenRequestSerializer(authTokenRequest);

        var x500 = new CertificateBuilders()
                .buildForOrganization("Kowalski sp. z o.o", "VATPL-" + subject, "Kowalski");

        SelfSignedCertificate cert = new DefaultCertificateGenerator().generateSelfSignedCertificate(x500);

        var signedXml = new DefaultSignatureService().sign(xml.getBytes(), cert.certificate(), cert.getPrivateKey());

        var submitAuthTokenResponse = defaultKsefClient.submitAuthTokenRequest(signedXml);

        Thread.sleep(2000);

        var tokenResponse = defaultKsefClient.redeemToken();

        return new AuthTokensPair(tokenResponse.getAccessToken().getToken(), tokenResponse.getRefreshToken().getToken());
    }

    public record AuthTokensPair(String authToken, String refreshToken) {

    }
}
