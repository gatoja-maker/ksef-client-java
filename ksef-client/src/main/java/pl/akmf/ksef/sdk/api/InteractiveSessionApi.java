package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.session.online.OpenOnlineSessionRequest;
import pl.akmf.ksef.sdk.client.model.session.online.OpenOnlineSessionResponse;
import pl.akmf.ksef.sdk.client.model.session.online.SendInvoiceRequest;
import pl.akmf.ksef.sdk.client.model.session.online.SendInvoiceResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.function.Consumer;

import static pl.akmf.ksef.sdk.api.Url.SESSION_CLOSE;
import static pl.akmf.ksef.sdk.api.Url.SESSION_INVOICE_SEND;
import static pl.akmf.ksef.sdk.api.Url.SESSION_OPEN;

public class InteractiveSessionApi extends BaseApi {
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;

    public InteractiveSessionApi(ApiClient apiClient) {
        super(apiClient.getHttpClient(), apiClient.getResponseInterceptor());
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
    }

    /**
     * Zamknięcie sesji interaktywnej
     * Zamyka sesję interaktywną i rozpoczyna generowanie zbiorczego UPO.
     *
     * @param referenceNumber Numer referencyjny sesji (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<Void> apiV2SessionsOnlineReferenceNumberClosePostWithHttpInfo(String referenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsOnlineReferenceNumberClosePostRequestBuilder(referenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_CLOSE.getOperationId());

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

    private HttpRequest.Builder apiV2SessionsOnlineReferenceNumberClosePostRequestBuilder(String referenceNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsOnlineReferenceNumberClosePost");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_CLOSE.getUrl()
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
     * Wysłanie faktury
     * Przyjmuje zaszyfrowaną fakturę oraz jej metadane i rozpoczyna jej przetwarzanie.
     *
     * @param referenceNumber    Numer referencyjny sesji (required)
     * @param sendInvoiceRequest (optional)
     * @return ApiResponse&lt;SendDocumentResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<SendInvoiceResponse> apiV2SessionsOnlineReferenceNumberInvoicesPostWithHttpInfo(String referenceNumber, SendInvoiceRequest sendInvoiceRequest) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsOnlineReferenceNumberInvoicesPostRequestBuilder(referenceNumber, sendInvoiceRequest);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_INVOICE_SEND.getOperationId());

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

    private HttpRequest.Builder apiV2SessionsOnlineReferenceNumberInvoicesPostRequestBuilder(String referenceNumber, SendInvoiceRequest sendInvoiceRequest) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsOnlineReferenceNumberDocumentsPost");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_INVOICE_SEND.getUrl()
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/json");

        try {
            byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(sendInvoiceRequest);
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
     * Otwarcie sesji interaktywnej
     * Inicjalizacja wysyłki interaktywnej faktur.
     *
     * @param openOnlineSessionRequest (optional)
     * @return ApiResponse&lt;OpenOnlineSessionResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<OpenOnlineSessionResponse> onlineSessionOpenWithHttpInfo(OpenOnlineSessionRequest openOnlineSessionRequest) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = onlineSessionOpenRequestBuilder(openOnlineSessionRequest);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_OPEN.getOperationId());

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

    private HttpRequest.Builder onlineSessionOpenRequestBuilder(OpenOnlineSessionRequest openOnlineSessionRequest) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_OPEN.getUrl();

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/json");

        try {
            byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(openOnlineSessionRequest);
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
