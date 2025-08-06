package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.Pair;
import pl.akmf.ksef.sdk.client.model.invoice.AsyncInvoicesQueryStatus;
import pl.akmf.ksef.sdk.client.model.invoice.DownloadInvoiceRequest;
import pl.akmf.ksef.sdk.client.model.invoice.InitAsyncInvoicesQueryResponse;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequest;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesQueryRequest;
import pl.akmf.ksef.sdk.client.model.invoice.QueryInvoicesReponse;

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

import static pl.akmf.ksef.sdk.api.Url.INVOICE_DOWNLOAD;
import static pl.akmf.ksef.sdk.api.Url.INVOICE_DOWNLOAD_BY_KSEF;
import static pl.akmf.ksef.sdk.api.Url.INVOICE_QUERY;
import static pl.akmf.ksef.sdk.api.Url.INVOICE_QUERY_ASYNC;
import static pl.akmf.ksef.sdk.api.Url.INVOICE_QUERY_STATUS;

public class DownloadInvoiceApi extends BaseApi {
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;

    public DownloadInvoiceApi(ApiClient apiClient) {
        super(apiClient.getHttpClient(), apiClient.getResponseInterceptor());
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
    }

    /**
     * Pobranie dokumentu po numerze KSeF
     * Zwraca dokument o podanym numerze KSeF.
     *
     * @param ksefReferenceNumber Numer KSeF dokumentu (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<String> apiV2InvoicesKsefKsefReferenceNumberGet(String ksefReferenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2InvoicesKsefKsefReferenceNumberGetRequestBuilder(ksefReferenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, INVOICE_DOWNLOAD_BY_KSEF.getOperationId());
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

    private HttpRequest.Builder apiV2InvoicesKsefKsefReferenceNumberGetRequestBuilder(String ksefReferenceNumber) throws ApiException {
        if (ksefReferenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'ksefReferenceNumber' when calling apiV2InvoicesKsefKsefReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = INVOICE_DOWNLOAD_BY_KSEF.getUrl()
                .replace("{ksefReferenceNumber}", ApiClient.urlEncode(ksefReferenceNumber));

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
     * [mock] Sprawdza status asynchronicznego zapytania o pobranie faktur
     * Pobiera status wcześniej zainicjalizowanego zapytania asynchronicznego na podstawie identyfikatora operacji. Umożliwia śledzenie postępu przetwarzania zapytania oraz pobranie gotowych paczek z wynikami, jeśli są już dostępne.
     *
     * @param operationReferenceNumber Unikalny identyfikator operacji zwrócony podczas inicjalizacji zapytania. (required)
     * @return ApiResponse&lt;AsyncInvoicesQueryStatus&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<AsyncInvoicesQueryStatus> apiV2InvoicesAsyncQueryOperationReferenceNumberGet(String operationReferenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2InvoicesAsyncQueryOperationReferenceNumberGetRequestBuilder(operationReferenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, INVOICE_QUERY_STATUS.getOperationId());

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

    private HttpRequest.Builder apiV2InvoicesAsyncQueryOperationReferenceNumberGetRequestBuilder(String operationReferenceNumber) throws ApiException {
        if (operationReferenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'operationReferenceNumber' when calling apiV2InvoicesAsyncQueryOperationReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = INVOICE_QUERY_STATUS.getUrl()
                .replace("{operationReferenceNumber}", ApiClient.urlEncode(operationReferenceNumber));

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
     * [mock] Inicjalizuje asynchroniczne zapytanie o pobranie faktur
     * Rozpoczyna asynchroniczny proces wyszukiwania faktur w systemie KSeF na podstawie przekazanych filtrów. Wymagane jest przekazanie informacji o szyfrowaniu w polu &#x60;Encryption&#x60;, które służą do zaszyfrowania wygenerowanych paczek z fakturami.
     *
     * @param invoicesAsynqQueryRequest Zestaw filtrów dla wyszukiwania faktur. (optional)
     * @return ApiResponse&lt;InitAsyncInvoicesQueryResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<InitAsyncInvoicesQueryResponse> apiV2InvoicesAsyncQueryPost(InvoicesAsynqQueryRequest invoicesAsynqQueryRequest) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2InvoicesAsyncQueryPostRequestBuilder(invoicesAsynqQueryRequest);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, INVOICE_QUERY_ASYNC.getOperationId());

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

    private HttpRequest.Builder apiV2InvoicesAsyncQueryPostRequestBuilder(InvoicesAsynqQueryRequest invoicesAsynqQueryRequest) throws ApiException {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = INVOICE_QUERY_ASYNC.getUrl();

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/json");

        try {
            byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(invoicesAsynqQueryRequest);
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
     * Pobranie faktury  na podstawie numeru KSeF oraz danych faktury
     * Faktura zostanie zwrócona wyłącznie, jeśli wszystkie dane wejściowe są zgodne z danymi faktury w systemie.
     *
     * @param downloadInvoiceRequest (optional)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<String> apiV2InvoicesDownloadPost(DownloadInvoiceRequest downloadInvoiceRequest) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2InvoicesDownloadPostRequestBuilder(downloadInvoiceRequest);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, INVOICE_DOWNLOAD.getOperationId());

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

    private HttpRequest.Builder apiV2InvoicesDownloadPostRequestBuilder(DownloadInvoiceRequest downloadInvoiceRequest) throws ApiException {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = INVOICE_DOWNLOAD.getUrl();

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/xml");

        try {
            byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(downloadInvoiceRequest);
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
     * [mock] Pobranie listy metadanych faktur
     * Zwraca listę metadanych faktur spełniające podane kryteria wyszukiwania.
     *
     * @param pageOffset           Indeks pierwszej strony wyników (domyślnie 0). (optional)
     * @param pageSize             Rozmiar strony wyników(domyślnie 10). (optional)
     * @param invoicesQueryRequest Zestaw filtrów dla wyszukiwania metadanych. (optional)
     * @return ApiResponse&lt;QueryInvoicesReponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<QueryInvoicesReponse> apiV2InvoicesQueryPost(Integer pageOffset, Integer pageSize, InvoicesQueryRequest invoicesQueryRequest) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2InvoicesQueryPostRequestBuilder(pageOffset, pageSize, invoicesQueryRequest);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, INVOICE_QUERY.getOperationId());

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

    private HttpRequest.Builder apiV2InvoicesQueryPostRequestBuilder(Integer pageOffset, Integer pageSize, InvoicesQueryRequest invoicesQueryRequest) throws ApiException {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = INVOICE_QUERY.getUrl();

        List<Pair> localVarQueryParams = new ArrayList<>();
        StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
        localVarQueryParams.addAll(ApiClient.parameterToPairs("pageOffset", pageOffset));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("pageSize", pageSize));

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

        localVarRequestBuilder.header("Content-Type", "application/json");
        localVarRequestBuilder.header("Accept", "application/json");

        try {
            byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(invoicesQueryRequest);
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
