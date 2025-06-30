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

public class OperationApi {
    private final HttpClient memberVarHttpClient;
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;
    private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;


    public OperationApi() {
        this(new ApiClient());
    }

    public OperationApi(ApiClient apiClient) {
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
     * Pobranie statusu operacji
     *
     * @param referenceNumber Numer referencyjny operacji (required)
     * @return ApiResponse&lt;PermissionsOperationStatusResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<PermissionStatusInfo> apiV2PermissionsOperationsReferenceNumberGet(String referenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2PermissionsOperationsReferenceNumberGetRequestBuilder(referenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2PermissionsOperationsReferenceNumberGet", localVarResponse);
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

    private HttpRequest.Builder apiV2PermissionsOperationsReferenceNumberGetRequestBuilder(String referenceNumber) throws ApiException {
        // verify the required parameter 'referenceNumber' is set
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2PermissionsOperationsReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/permissions/operations/{referenceNumber}"
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber.toString()));

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
     * @param permissionId Id uprawnienia. (required)
     * @return PermissionsOperationResponse
     * @throws ApiException if fails to make API call
     */
    public PermissionsOperationResponse apiV2PermissionsAuthorizationsGrantsPermissionIdDelete(String permissionId) throws ApiException {
        ApiResponse<PermissionsOperationResponse> localVarResponse = apiV2PermissionsAuthorizationsGrantsPermissionIdDeleteWithHttpInfo(permissionId);
        return localVarResponse.getData();
    }

    /**
     * Odebranie uprawnień o charakterze upoważnień
     * Rozpoczyna asynchroniczną operacje odbierania uprawnienia o podanym identyfikatorze. Ta metoda służy do odbierania uprawnień o charakterze upoważnień.
     * @param permissionId Id uprawnienia. (required)
     * @return ApiResponse&lt;PermissionsOperationResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<PermissionsOperationResponse> apiV2PermissionsAuthorizationsGrantsPermissionIdDeleteWithHttpInfo(String permissionId) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2PermissionsAuthorizationsGrantsPermissionIdDeleteRequestBuilder(permissionId);
        try {
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            try {
                if (localVarResponse.statusCode()/ 100 != 2) {
                    throw getApiException("apiV2PermissionsAuthorizationsGrantsPermissionIdDelete", localVarResponse);
                }
                return new ApiResponse<PermissionsOperationResponse>(
                        localVarResponse.statusCode(),
                        localVarResponse.headers().map(),
                        localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<PermissionsOperationResponse>() {}) // closes the InputStream
                );
            } finally {
            }
        } catch (IOException e) {
            throw new ApiException(e);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2PermissionsAuthorizationsGrantsPermissionIdDeleteRequestBuilder(String permissionId) throws ApiException {
        // verify the required parameter 'permissionId' is set
        if (permissionId == null) {
            throw new ApiException(400, "Missing the required parameter 'permissionId' when calling apiV2PermissionsAuthorizationsGrantsPermissionIdDelete");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/permissions/authorizations/grants/{permissionId}"
                .replace("{permissionId}", ApiClient.urlEncode(permissionId.toString()));

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
     * @param permissionId Id uprawnienia. (required)
     * @return PermissionsOperationResponse
     * @throws ApiException if fails to make API call
     */
    public PermissionsOperationResponse apiV2PermissionsCommonGrantsPermissionIdDelete(String permissionId) throws ApiException {
        ApiResponse<PermissionsOperationResponse> localVarResponse = apiV2PermissionsCommonGrantsPermissionIdDeleteWithHttpInfo(permissionId);
        return localVarResponse.getData();
    }

    /**
     * Odebranie uprawnień
     * Rozpoczyna asynchroniczną operacje odbierania uprawnienia o podanym identyfikatorze.  Ta metoda służy do odbierania uprawnień takich jak: - nadanych osobom fizycznym do pracy w KSeF - nadanych podmiotom do obsługi faktur - nadanych w sposób pośredni - administratora podmiotu podrzędnego - administratora podmiotu unijnego - reprezentanta podmiotu unijnego
     * @param permissionId Id uprawnienia. (required)
     * @return ApiResponse&lt;PermissionsOperationResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<PermissionsOperationResponse> apiV2PermissionsCommonGrantsPermissionIdDeleteWithHttpInfo(String permissionId) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2PermissionsCommonGrantsPermissionIdDeleteRequestBuilder(permissionId);
        try {
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            try {
                if (localVarResponse.statusCode()/ 100 != 2) {
                    throw getApiException("apiV2PermissionsCommonGrantsPermissionIdDelete", localVarResponse);
                }
                return new ApiResponse<PermissionsOperationResponse>(
                        localVarResponse.statusCode(),
                        localVarResponse.headers().map(),
                        localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<PermissionsOperationResponse>() {}) // closes the InputStream
                );
            } finally {
            }
        } catch (IOException e) {
            throw new ApiException(e);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2PermissionsCommonGrantsPermissionIdDeleteRequestBuilder(String permissionId) throws ApiException {
        // verify the required parameter 'permissionId' is set
        if (permissionId == null) {
            throw new ApiException(400, "Missing the required parameter 'permissionId' when calling apiV2PermissionsCommonGrantsPermissionIdDelete");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/permissions/common/grants/{permissionId}"
                .replace("{permissionId}", ApiClient.urlEncode(permissionId.toString()));

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
