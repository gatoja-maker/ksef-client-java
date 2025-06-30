package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.tokens.GenerateTokenRequestBuilder;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationToken;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationTokenStatus;
import pl.akmf.ksef.sdk.client.model.auth.GenerateTokenRequest;
import pl.akmf.ksef.sdk.client.model.auth.GenerateTokenResponse;
import pl.akmf.ksef.sdk.client.model.auth.QueryTokensResponse;
import pl.akmf.ksef.sdk.client.model.auth.TokenPermissionType;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.util.List;

class TokensIntegrationTest extends BaseIntegrationTest {

    @Test
    void checkGenerateTokenTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        //step 1: generate token
        GenerateTokenRequest request = new GenerateTokenRequestBuilder()
                .withDescription("test description")
                .withPermissions(List.of(TokenPermissionType.INVOICEREAD, TokenPermissionType.INVOICEWRITE, TokenPermissionType.CREDENTIALSREAD))
                .build();
        GenerateTokenResponse token = defaultKsefClient.generateKsefToken(request);
        GenerateTokenResponse token2 = defaultKsefClient.generateKsefToken(request);
        GenerateTokenResponse token3 = defaultKsefClient.generateKsefToken(request);

        Assertions.assertNotNull(token);
        Assertions.assertNotNull(token.getToken());
        Assertions.assertNotNull(token.getReferenceNumber());

        //step 2: get metadate token
        Thread.sleep(5000);
        AuthenticationToken ksefToken = defaultKsefClient.getKsefToken(token.getReferenceNumber());
        Assertions.assertNotNull(ksefToken);
        Assertions.assertEquals(AuthenticationTokenStatus.ACTIVE, ksefToken.getStatus());

        //step 3: filter active tokens
        List<AuthenticationTokenStatus> status = List.of(AuthenticationTokenStatus.ACTIVE);
        Integer pageSize = 10;
        QueryTokensResponse tokens = defaultKsefClient.queryKsefTokens(status, StringUtils.EMPTY, pageSize);
        List<AuthenticationToken> filteredTokens = tokens.getTokens();
        Assertions.assertNotNull(filteredTokens);
        Assertions.assertEquals(3, filteredTokens.size());

        //step 4: revoke tokens and check status
        defaultKsefClient.revokeKsefToken(token.getReferenceNumber());
        Thread.sleep(1000);
        AuthenticationToken ksefTokenAfterRevoke = defaultKsefClient.getKsefToken(token.getReferenceNumber());
        Assertions.assertNotNull(ksefTokenAfterRevoke);
        Assertions.assertEquals(AuthenticationTokenStatus.REVOKED, ksefTokenAfterRevoke.getStatus());

        //step 5: filter active tokens after revoked one token
        QueryTokensResponse tokens2 = defaultKsefClient.queryKsefTokens(status, StringUtils.EMPTY, pageSize);
        List<AuthenticationToken> filteredTokens2 = tokens2.getTokens();
        Assertions.assertNotNull(filteredTokens2);
        Assertions.assertEquals(2, filteredTokens2.size());
    }
}
