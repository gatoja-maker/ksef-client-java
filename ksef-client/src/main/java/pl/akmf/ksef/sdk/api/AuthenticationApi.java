
package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.auth.AuthKsefTokenRequest;
import pl.akmf.ksef.sdk.client.model.auth.AuthStatus;
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
import java.util.function.Consumer;

public class AuthenticationApi {
    private final HttpClient memberVarHttpClient;
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;
    private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;


    public AuthenticationApi(ApiClient apiClient) {
        memberVarHttpClient = apiClient.getHttpClient();
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
        memberVarResponseInterceptor = apiClient.getResponseInterceptor();
    }

    protected ApiException getApiException(String operationId, HttpResponse<InputStream> response) throws IOException {
        String body = response.body() == null ? null : new String(response.body().readAllBytes());
        String message = formatExceptionMessage(operationId, response.statusCode(), body);
        return new ApiException(response.statusCode(), message, response.headers(), body);
    }

    private String formatExceptionMessage(String operationId, int statusCode, String body) {
        if (body == null || body.isEmpty()) {
            body = "[no body]";
        }
        return operationId + " call failed with: " + statusCode + " - " + body;
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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2AuthChallengePost", localVarResponse);
            }
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    }) // closes the InputStream
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

        String localVarPath = "/api/v2/auth/challenge";

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
     * [Mock] Unieważnienie tokena autoryzacyjnego
     * Unieważnia aktualny (przekazany w nagłówku wywołania tej metody) token.
     * Po unieważnieniu tokena nie będzie można za jego pomocą wykonywać żadnych operacji.
     *
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<Void> apiV2AuthTokenDeleteWithHttpInfo() throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthTokenDeleteRequestBuilder();
        try {
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            try {
                if (localVarResponse.statusCode() / 100 != 2) {
                    throw getApiException("apiV2AuthTokenDelete", localVarResponse);
                }
                return new ApiResponse<>(
                        localVarResponse.statusCode(),
                        localVarResponse.headers().map(),
                        null
                );
            } finally {
                // Drain the InputStream
                localVarResponse.body().close();
            }
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthTokenDeleteRequestBuilder() {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/auth/token";

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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2AuthTokenRefreshPost", localVarResponse);
            }
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    }) // closes the InputStream
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

        String localVarPath = "/api/v2/auth/token/refresh";

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
    public ApiResponse<AuthenticationInitResponse> apiV2AuthTokenSignaturePostWithHttpInfo(String body) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthTokenSignaturePostRequestBuilder(body);
        try {
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2AuthTokenSignaturePost", localVarResponse);
            }
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    }) // closes the InputStream
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthTokenSignaturePostRequestBuilder(String body) throws ApiException {
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException(400, "Missing the required parameter 'body' when calling apiV2AuthTokenSignaturePost");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/auth/xades-signature";

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2AuthTokenSignaturePost", localVarResponse);
            }
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    }) // closes the InputStream
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

        String localVarPath = "/api/v2/auth/ksef-token";

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
    public ApiResponse<AuthStatus> apiV2AuthTokenTokenReferenceNumberGetWithHttpInfo(String referenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthTokenTokenReferenceNumberGetRequestBuilder(referenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2AuthTokenTokenReferenceNumberGet", localVarResponse);
            }
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    }) // closes the InputStream
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2AuthTokenTokenReferenceNumberGetRequestBuilder(String referenceNumber) throws ApiException {
        // verify the required parameter 'tokenReferenceNumber' is set
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'tokenReferenceNumber' when calling apiV2AuthTokenTokenReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/auth/{referenceNumber}"
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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2AuthTokenTokenReferenceNumberGet", localVarResponse);
            }
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                    }) // closes the InputStream
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

        String localVarPath = "/api/v2/auth/token/redeem";

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
