package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.session.batch.OpenBatchSessionRequest;
import pl.akmf.ksef.sdk.client.model.session.batch.OpenBatchSessionResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.function.Consumer;

import static pl.akmf.ksef.sdk.api.Url.BATCH_SESSION_CLOSE;
import static pl.akmf.ksef.sdk.api.Url.BATCH_SESSION_OPEN;
import static pl.akmf.ksef.sdk.client.model.ApiException.getApiException;

public class BatchInvoiceApi extends BaseApi {
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;

    public BatchInvoiceApi(ApiClient apiClient) {
        super(apiClient.getHttpClient(),apiClient.getResponseInterceptor());
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
    }

    /**
     * Zamknięcie sesji wsadowej
     * Informuje system o tym, że wszystkie pliki zostały przekazane i można rozpocząć ich przetwarzanie.
     *
     * @param referenceNumber Numer referencyjny sesji (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<Void> apiV2SessionsBatchReferenceNumberClosePostWithHttpInfo(String referenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsBatchReferenceNumberClosePostRequestBuilder(referenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, BATCH_SESSION_CLOSE.getOperationId());

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

    private HttpRequest.Builder apiV2SessionsBatchReferenceNumberClosePostRequestBuilder(String referenceNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsBatchReferenceNumberClosePost");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = BATCH_SESSION_CLOSE.getUrl()
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber));

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
     * Otwarcie sesji wsadowej
     * Inicjalizacja wysyłki wsadowej paczki faktur.
     *
     * @param openBatchSessionRequest (optional)
     * @return ApiResponse&lt;OpenBatchSessionResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<OpenBatchSessionResponse> batchOpenWithHttpInfo(OpenBatchSessionRequest openBatchSessionRequest) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = batchOpenRequestBuilder(openBatchSessionRequest);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, BATCH_SESSION_OPEN.getOperationId());

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

    private HttpRequest.Builder batchOpenRequestBuilder(OpenBatchSessionRequest openBatchSessionRequest) throws ApiException {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = BATCH_SESSION_OPEN.getUrl();

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/json");

        try {
            byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(openBatchSessionRequest);
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
}
