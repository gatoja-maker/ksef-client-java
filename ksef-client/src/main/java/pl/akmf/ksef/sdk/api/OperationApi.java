package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.permission.PermissionStatusInfo;
import pl.akmf.ksef.sdk.client.model.permission.PermissionsOperationResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.function.Consumer;

import static pl.akmf.ksef.sdk.api.Url.PERMISSION_REVOKE_AUTHORIZATION;
import static pl.akmf.ksef.sdk.api.Url.PERMISSION_REVOKE_COMMON;
import static pl.akmf.ksef.sdk.api.Url.PERMISSION_STATUS;

public class OperationApi extends BaseApi {
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;

    public OperationApi(ApiClient apiClient) {
        super(apiClient.getHttpClient(), apiClient.getResponseInterceptor());
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
    }

    /**
     * Pobranie statusu operacji
     *
     * @param referenceNumber Numer referencyjny operacji (required)
     * @return ApiResponse&lt;PermissionsOperationStatusResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<PermissionStatusInfo> apiV2PermissionsOperationsReferenceNumberGet(String referenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2PermissionsOperationsReferenceNumberGetRequestBuilder(referenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, PERMISSION_STATUS.getOperationId());

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

    private HttpRequest.Builder apiV2PermissionsOperationsReferenceNumberGetRequestBuilder(String referenceNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2PermissionsOperationsReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = PERMISSION_STATUS.getUrl()
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
     * Odebranie uprawnień o charakterze upoważnień
     * Rozpoczyna asynchroniczną operacje odbierania uprawnienia o podanym identyfikatorze. Ta metoda służy do odbierania uprawnień o charakterze upoważnień.
     *
     * @param permissionId Id uprawnienia. (required)
     * @return ApiResponse&lt;PermissionsOperationResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<PermissionsOperationResponse> apiV2PermissionsAuthorizationsGrantsPermissionIdDelete(String permissionId) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2PermissionsAuthorizationsGrantsPermissionIdDeleteRequestBuilder(permissionId);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, PERMISSION_REVOKE_AUTHORIZATION.getOperationId());

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

    private HttpRequest.Builder apiV2PermissionsAuthorizationsGrantsPermissionIdDeleteRequestBuilder(String permissionId) throws ApiException {
        if (permissionId == null) {
            throw new ApiException(400, "Missing the required parameter 'permissionId' when calling apiV2PermissionsAuthorizationsGrantsPermissionIdDelete");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = PERMISSION_REVOKE_AUTHORIZATION.getUrl()
                .replace("{permissionId}", ApiClient.urlEncode(permissionId));

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
     * Odebranie uprawnień
     * Rozpoczyna asynchroniczną operacje odbierania uprawnienia o podanym identyfikatorze.  Ta metoda służy do odbierania uprawnień takich jak: - nadanych osobom fizycznym do pracy w KSeF - nadanych podmiotom do obsługi faktur - nadanych w sposób pośredni - administratora podmiotu podrzędnego - administratora podmiotu unijnego - reprezentanta podmiotu unijnego
     *
     * @param permissionId Id uprawnienia. (required)
     * @return ApiResponse&lt;PermissionsOperationResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<PermissionsOperationResponse> apiV2PermissionsCommonGrantsPermissionIdDelete(String permissionId) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2PermissionsCommonGrantsPermissionIdDeleteRequestBuilder(permissionId);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, PERMISSION_REVOKE_COMMON.getOperationId());

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

    private HttpRequest.Builder apiV2PermissionsCommonGrantsPermissionIdDeleteRequestBuilder(String permissionId) throws ApiException {
        if (permissionId == null) {
            throw new ApiException(400, "Missing the required parameter 'permissionId' when calling apiV2PermissionsCommonGrantsPermissionIdDelete");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = PERMISSION_REVOKE_COMMON.getUrl()
                .replace("{permissionId}", ApiClient.urlEncode(permissionId));

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
