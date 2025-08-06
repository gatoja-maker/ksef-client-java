package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.Pair;
import pl.akmf.ksef.sdk.client.model.session.AuthenticationListResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static pl.akmf.ksef.sdk.api.Url.SESSION_ACTIVE_SESSIONS;
import static pl.akmf.ksef.sdk.api.Url.SESSION_REVOKE_CURRENT_SESSION;
import static pl.akmf.ksef.sdk.api.Url.SESSION_REVOKE_SESSION;

public class ActiveSessionApi extends BaseApi {
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;

    public ActiveSessionApi() {
        this(new ApiClient());
    }

    public ActiveSessionApi(ApiClient apiClient) {
        super(apiClient.getHttpClient(), apiClient.getResponseInterceptor());
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
    }

    /**
     * Unieważnienie aktualnej sesji uwierzytelnienia
     * Unieważnia sesję powiązaną z tokenem użytym do wywołania tej operacji.  Unieważnienie sesji sprawia, że powiązany z nią refresh token przestaje działać i nie można już za jego pomocą uzyskać kolejnych access tokenów. **Aktywne access tokeny działają do czasu minięcia ich termin ważności.**  Sposób uwierzytelnienia: &#x60;RefreshToken&#x60; lub &#x60;ContextToken&#x60;.
     *
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<Void> apiV2AuthSessionsCurrentDelete() throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthSessionsCurrentDeleteRequestBuilder();
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_REVOKE_CURRENT_SESSION.getOperationId());

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

    private HttpRequest.Builder apiV2AuthSessionsCurrentDeleteRequestBuilder() {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_REVOKE_CURRENT_SESSION.getUrl();

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
     * Pobranie listy aktywnych sesji
     * Zwraca listę aktywnych sesji uwierzytelnienia.
     *
     * @param pageSize          Rozmiar strony wyników. (optional, default to 10)
     * @param continuationToken
     * @return ApiResponse&lt;AuthenticationListResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<AuthenticationListResponse> apiV2AuthSessionsGet(Integer pageSize, String continuationToken) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthSessionsGetRequestBuilder(pageSize, continuationToken);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_ACTIVE_SESSIONS.getOperationId());
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

    private HttpRequest.Builder apiV2AuthSessionsGetRequestBuilder(Integer pageSize, String continuationToken) {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_ACTIVE_SESSIONS.getUrl();

        StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
        List<Pair> localVarQueryParams = new ArrayList<>(ApiClient.parameterToPairs("pageSize", pageSize));

        if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
            StringJoiner queryJoiner = new StringJoiner("&");
            localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
            if (localVarQueryStringJoiner.length() != 0) {
                queryJoiner.add(localVarQueryStringJoiner.toString());
            }
            localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner));
        } else {
            localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
        }

        if (continuationToken != null) {
            localVarRequestBuilder.header("x-continuation-token", continuationToken);
        }

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
     * Unieważnienie sesji uwierzytelnienia
     * Unieważnia sesję o podanym numerze referencyjnym.  Unieważnienie sesji sprawia, że powiązany z nią refresh token przestaje działać i nie można już za jego pomocą uzyskać kolejnych access tokenów. **Aktywne access tokeny działają do czasu minięcia ich termin ważności.**
     *
     * @param referenceNumber Numer referencyjny sesji uwierzytelnienia. (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<Void> apiV2AuthSessionsReferenceNumberDelete(String referenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2AuthSessionsReferenceNumberDeleteRequestBuilder(referenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_REVOKE_SESSION.getOperationId());
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

    private HttpRequest.Builder apiV2AuthSessionsReferenceNumberDeleteRequestBuilder(String referenceNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2AuthSessionsReferenceNumberDelete");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_REVOKE_SESSION
                .getUrl().replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber));

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
}
