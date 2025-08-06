
package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.Pair;
import pl.akmf.ksef.sdk.client.model.auth.AuthKsefTokenRequest;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationChallengeResponse;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationInitResponse;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationOperationStatusResponse;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationTokenRefreshResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static pl.akmf.ksef.sdk.api.Url.AUTH_CHALLENGE;
import static pl.akmf.ksef.sdk.api.Url.AUTH_KSEF_TOKEN;
import static pl.akmf.ksef.sdk.api.Url.AUTH_TOKEN_REEDEM;
import static pl.akmf.ksef.sdk.api.Url.AUTH_TOKEN_SIGNATURE;
import static pl.akmf.ksef.sdk.api.Url.AUTH_TOKEN_STATUS;
import static pl.akmf.ksef.sdk.api.Url.JWT_TOKEN_REFRESH;
import static pl.akmf.ksef.sdk.api.Url.JWT_TOKEN_REVOKE;
import static pl.akmf.ksef.sdk.client.model.ApiException.getApiException;

public class AuthenticationApi extends BaseApi {
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;


    public AuthenticationApi(ApiClient apiClient) {
        super(apiClient.getHttpClient(),apiClient.getResponseInterceptor());
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
    }

    /**
     * Inicjalizacja mechanizmu uwierzytelnienia i autoryzacji
     *
     * @return ApiResponse&lt;AuthenticationChallengeResponse&gt;
     * @throws ApiException if fails to make API call
     */

    public ApiResponse<AuthenticationChallengeResponse> apiV2AuthChallengePostWithHttpInfo() throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthChallengePostRequestBuilder();
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, AUTH_CHALLENGE.getOperationId());

            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    })
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthChallengePostRequestBuilder() {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = AUTH_CHALLENGE.getUrl();

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
        localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.noBody());
        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/json");

        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }

    /**
     * Unieważnienie tokena autoryzacyjnego
     * Unieważnia aktualny (przekazany w nagłówku wywołania tej metody) token.
     * Po unieważnieniu tokena nie będzie można za jego pomocą wykonywać żadnych operacji.
     *
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<Void> apiV2AuthTokenDeleteWithHttpInfo() throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthTokenDeleteRequestBuilder();
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, JWT_TOKEN_REVOKE.getOperationId());

            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    null
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthTokenDeleteRequestBuilder() {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = JWT_TOKEN_REVOKE.getUrl();

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/json");
        localVarRequestBuilder.method("DELETE", HttpRequest.BodyPublishers.noBody());
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }

    /**
     * [Mock] Odświeżenie tokena autoryzacyjnego
     *
     * @return ApiResponse&lt;AuthenticationTokenRefreshResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<AuthenticationTokenRefreshResponse> apiV2AuthTokenRefreshPostWithHttpInfo() throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthTokenRefreshPostRequestBuilder();
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, JWT_TOKEN_REFRESH.getOperationId());

            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    })
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthTokenRefreshPostRequestBuilder() {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = JWT_TOKEN_REFRESH.getUrl();

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/json");

        localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.noBody());
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }

    /**
     * Rozpoczęcie procesu uwierzytelniania
     * Rozpoczyna proces uwierzytelniania na podstawie podpisanego XMLa.
     *
     * @param body (required)
     * @return ApiResponse&lt;AuthenticationInitResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<AuthenticationInitResponse> apiV2AuthTokenSignaturePostWithHttpInfo(String body, boolean verifyCertificateChain) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthTokenSignaturePostRequestBuilder(body, verifyCertificateChain);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, AUTH_TOKEN_SIGNATURE.getOperationId());

            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    })
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthTokenSignaturePostRequestBuilder(String body, boolean verifyCertificateChain) throws ApiException {
        if (body == null) {
            throw new ApiException(400, "Missing the required parameter 'body' when calling apiV2AuthTokenSignaturePost");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = AUTH_TOKEN_SIGNATURE.getUrl();

        List<Pair> localVarQueryParams = new ArrayList<>();
        StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
        localVarQueryParams.addAll(ApiClient.parameterToPairs("verifyCertificateChain", verifyCertificateChain));

        if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
            StringJoiner queryJoiner = new StringJoiner("&");
            localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
            if (localVarQueryStringJoiner.length() != 0) {
                queryJoiner.add(localVarQueryStringJoiner.toString());
            }
            localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
        } else {
            localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
        }

        localVarRequestBuilder.header("Content-Type", "application/xml");
        localVarRequestBuilder.header("Accept", "application/json");

        localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.ofString(body));
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }

    /**
     * Rozpoczęcie procesu uwierzytelniania tokenem
     * Rozpoczyna proces uwierzytelniania na podstawie tokenu
     *
     * @param body (required)
     * @return ApiResponse&lt;AuthenticationInitResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<AuthenticationInitResponse> apiV2AuthTokeKSeFPostWithHttpInfo(AuthKsefTokenRequest body) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthTokenKSeFPostRequestBuilder(body);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, AUTH_KSEF_TOKEN.getOperationId());
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    })
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthTokenKSeFPostRequestBuilder(AuthKsefTokenRequest authKsefTokenRequest) throws ApiException {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = AUTH_KSEF_TOKEN.getUrl();

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/json");

        try {
            byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(authKsefTokenRequest);
            localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
        } catch (IOException e) {
            throw new ApiException(e);
        }
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }


    /**
     * Pobranie statusu operacji uwierzytelniania
     * Zwraca status trwającej operacji uwierzytelniania
     *
     * @param referenceNumber numer referencyjny związany z procesem uwierzytelnienia
     * @return ApiResponse&lt;AuthenticationOperationStatusResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<pl.akmf.ksef.sdk.client.model.session.AuthenticationOperationStatusResponse> apiV2AuthTokenTokenReferenceNumberGetWithHttpInfo(String referenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthTokenTokenReferenceNumberGetRequestBuilder(referenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, AUTH_TOKEN_STATUS.getOperationId());

            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    })
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthTokenTokenReferenceNumberGetRequestBuilder(String referenceNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'tokenReferenceNumber' when calling apiV2AuthTokenTokenReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = AUTH_TOKEN_STATUS.getUrl()
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/json");

        localVarRequestBuilder.method("GET", HttpRequest.BodyPublishers.noBody());
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }


    /**
     * Pobranie tokenów uwierzytelnienia
     * Zwraca token dostępowy oraz token odswieżający
     *
     * @return ApiResponse&lt;AuthenticationOperationStatusResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<AuthenticationOperationStatusResponse> apiV2AuthRedeemTokenPost() throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthapiV2AuthRedeemTokenPostRequestBuilder();
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, AUTH_TOKEN_REEDEM.getOperationId());

            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    })
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthapiV2AuthRedeemTokenPostRequestBuilder() {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = AUTH_TOKEN_REEDEM.getUrl();

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/json");

        localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.noBody());
        if (memberVarReadTimeout != null) {
            localVarRequestBuilder.timeout(memberVarReadTimeout);
        }
        if (memberVarInterceptor != null) {
            memberVarInterceptor.accept(localVarRequestBuilder);
        }
        return localVarRequestBuilder;
    }
}
