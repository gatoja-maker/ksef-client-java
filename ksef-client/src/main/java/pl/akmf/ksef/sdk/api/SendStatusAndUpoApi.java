package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.Pair;
import pl.akmf.ksef.sdk.client.model.session.CommonSessionStatus;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoice;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoicesResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionStatusResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionType;
import pl.akmf.ksef.sdk.client.model.session.SessionsQueryRequest;
import pl.akmf.ksef.sdk.client.model.session.SessionsQueryResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

import static pl.akmf.ksef.sdk.api.Url.SESSION_INVOICE;
import static pl.akmf.ksef.sdk.api.Url.SESSION_INVOICE_FAILED;
import static pl.akmf.ksef.sdk.api.Url.SESSION_INVOICE_GET_BY_REFERENCE_NUMBER;
import static pl.akmf.ksef.sdk.api.Url.SESSION_INVOICE_UPO_BY_INVOICE_REFERENCE;
import static pl.akmf.ksef.sdk.api.Url.SESSION_INVOICE_UPO_BY_KSEF;
import static pl.akmf.ksef.sdk.api.Url.SESSION_LIST;
import static pl.akmf.ksef.sdk.api.Url.SESSION_STATUS;
import static pl.akmf.ksef.sdk.api.Url.SESSION_UPO;
import static pl.akmf.ksef.sdk.client.model.ApiException.getApiException;

public class SendStatusAndUpoApi extends BaseApi {
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;

    public SendStatusAndUpoApi(ApiClient apiClient) {
        super(apiClient.getHttpClient(),apiClient.getResponseInterceptor());
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
    }

    /**
     * Pobranie niepoprawnie przetworzonych faktur sesji
     * Zwraca listę niepoprawnie przetworzonych faktur przesłanych w sesji wraz z ich statusami.
     *
     * @param referenceNumber    Numer referencyjny sesji. (required)
     * @param xContinuationToken Token służący do pobrania kolejnej strony wyników. (optional)
     * @param pageSize           Rozmiar strony wyników. (optional, default to 10)
     * @return ApiResponse&lt;SessionInvoicesResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<SessionInvoicesResponse> apiV2SessionsReferenceNumberInvoicesFailedGet(String referenceNumber, String xContinuationToken, Integer pageSize) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsReferenceNumberInvoicesFailedGetRequestBuilder(referenceNumber, xContinuationToken, pageSize);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_INVOICE_FAILED.getOperationId());

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

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesFailedGetRequestBuilder(String referenceNumber, String xContinuationToken, Integer pageSize) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesFailedGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_INVOICE_FAILED.getUrl()
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber));

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

        if (xContinuationToken != null) {
            localVarRequestBuilder.header("x-continuation-token", xContinuationToken);
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
     * Pobranie faktur sesji
     * Zwraca listę faktur przesłanych w sesji wraz z ich statusami, oraz informacje na temat ilości poprawnie i niepoprawnie przetworzonych faktur.
     *
     * @param referenceNumber Numer referencyjny sesji. (required)
     * @param pageOffset      Numer strony wyników. (optional, default to 0)
     * @param pageSize        Rozmiar strony wyników. (optional, default to 10)
     * @return ApiResponse&lt;SessionInvoicesResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<SessionInvoicesResponse> apiV2SessionsReferenceNumberInvoicesGet(String referenceNumber, Integer pageOffset, Integer pageSize) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsReferenceNumberInvoicesGetRequestBuilder(referenceNumber, pageOffset, pageSize);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_INVOICE.getOperationId());

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

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesGetRequestBuilder(String referenceNumber, Integer pageOffset, Integer pageSize) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_INVOICE.getUrl()
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber));

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
     * Pobranie statusu faktury z sesji
     * Zwraca fakturę przesłaną w sesji wraz ze statusem.
     *
     * @param referenceNumber        Numer referencyjny sesji. (required)
     * @param invoiceReferenceNumber Numer referencyjny faktury. (required)
     * @return ApiResponse&lt;SessionInvoice&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<SessionInvoice> apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGet(String referenceNumber, String invoiceReferenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGetRequestBuilder(referenceNumber, invoiceReferenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_INVOICE_GET_BY_REFERENCE_NUMBER.getOperationId());

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

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGetRequestBuilder(String referenceNumber, String invoiceReferenceNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGet");
        }
        if (invoiceReferenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'invoiceReferenceNumber' when calling apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_INVOICE_GET_BY_REFERENCE_NUMBER.getUrl()
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber))
                .replace("{invoiceReferenceNumber}", ApiClient.urlEncode(invoiceReferenceNumber));

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
     * Pobranie UPO faktury z sesji na podstawie numeru referencyjnego faktury
     * Zwraca UPO faktury przesłanego w sesji na podstawie jego numeru KSeF.
     *
     * @param referenceNumber        Numer referencyjny sesji. (required)
     * @param invoiceReferenceNumber Numer referencyjny faktury. (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<byte[]> apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGet(String referenceNumber, String invoiceReferenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGetRequestBuilder(referenceNumber, invoiceReferenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_INVOICE_UPO_BY_INVOICE_REFERENCE.getOperationId());

            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : localVarResponse.body().readAllBytes()
            );

        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGetRequestBuilder(String referenceNumber, String invoiceReferenceNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGet");
        }
        if (invoiceReferenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'invoiceReferenceNumber' when calling apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_INVOICE_UPO_BY_INVOICE_REFERENCE.getUrl()
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber))
                .replace("{invoiceReferenceNumber}", ApiClient.urlEncode(invoiceReferenceNumber));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/xml");

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
     * Pobranie UPO faktury z sesji na podstawie numeru KSeF
     * Zwraca UPO faktury przesłanego w sesji na podstawie jego numeru KSeF.
     *
     * @param referenceNumber Numer referencyjny sesji. (required)
     * @param ksefNumber      Numer KSeF faktury. (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<byte[]> apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGet(String referenceNumber, String ksefNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGetRequestBuilder(referenceNumber, ksefNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_INVOICE_UPO_BY_KSEF.getOperationId());

            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : localVarResponse.body().readAllBytes()
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGetRequestBuilder(String referenceNumber, String ksefNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGet");
        }
        if (ksefNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'ksefNumber' when calling apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_INVOICE_UPO_BY_KSEF.getUrl()
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber))
                .replace("{ksefNumber}", ApiClient.urlEncode(ksefNumber));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/xml, application/json");

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
     * Pobranie statusu sesji
     * Sprawdza bieżący status sesji o podanym numerze referencyjnym.
     *
     * @param referenceNumber Numer referencyjny sesji. (required)
     * @return ApiResponse&lt;SessionStatusResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<SessionStatusResponse> apiV2SessionsReferenceNumberGet(String referenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsReferenceNumberGetRequestBuilder(referenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_STATUS.getOperationId());

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

    private HttpRequest.Builder apiV2SessionsReferenceNumberGetRequestBuilder(String referenceNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_STATUS.getUrl()
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
     * Pobranie UPO dla sesji
     * Zwraca XML zawierający zbiorcze UPO dla sesji.
     *
     * @param referenceNumber    Numer referencyjny sesji. (required)
     * @param upoReferenceNumber Numer referencyjny UPO. (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<byte[]> apiV2SessionsReferenceNumberUpoUpoReferenceNumberGet(String referenceNumber, String upoReferenceNumber) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsReferenceNumberUpoUpoReferenceNumberGetRequestBuilder(referenceNumber, upoReferenceNumber);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_UPO.getOperationId());

            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : localVarResponse.body().readAllBytes()
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2SessionsReferenceNumberUpoUpoReferenceNumberGetRequestBuilder(String referenceNumber, String upoReferenceNumber) throws ApiException {
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberUpoUpoReferenceNumberGet");
        }
        if (upoReferenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'upoReferenceNumber' when calling apiV2SessionsReferenceNumberUpoUpoReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_UPO.getUrl()
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber))
                .replace("{upoReferenceNumber}", ApiClient.urlEncode(upoReferenceNumber));

        localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

        localVarRequestBuilder.header("Accept", "application/xml, application/json");

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
     * Pobranie listy sesji
     * Zwraca listę sesji spełniających podane kryteria wyszukiwania.
     *
     * @param request enkapsulowane wszystkie pola requesta
     * @param pageSize page size
     * @param continuationToken  continuation token
     * @return ApiResponse&lt;SessionsQueryResponse&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<SessionsQueryResponse> apiV2SessionsGet(SessionsQueryRequest request, Integer pageSize, String continuationToken) throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SessionsGetRequestBuilder(request.getSessionType(), pageSize, request.getReferenceNumber(), request.getDateCreatedFrom(), request.getDateCreatedTo(), request.getDateClosedFrom(), request.getDateClosedTo(), request.getDateModifiedFrom(), request.getDateModifiedTo(), request.getStatuses(), continuationToken);
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SESSION_LIST.getOperationId());
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

    /**
     *
     * @param sessionType        Typ sesji. | Wartość | Opis | | --- | --- | | Online | Wysyłka interaktywna (pojedyncze faktury). | | Batch | Wysyłka wsadowa (paczka faktur). |  (required)
     * @param pageSize           Rozmiar strony. (optional)
     * @param referenceNumber    Numer referencyjny sesji. (optional)
     * @param dateCreatedFrom    Data utworzenia sesji (od). (optional)
     * @param dateCreatedTo      Data utworzenia sesji (do). (optional)
     * @param dateClosedFrom     Data zamknięcia sesji (od). (optional)
     * @param dateClosedTo       Data zamknięcia sesji (do). (optional)
     * @param dateModifiedFrom   Data ostatniej aktywności (wysyłka faktury lub zmiana statusu) w ramach sesji (od). (optional)
     * @param dateModifiedTo     Data ostatniej aktywności (wysyłka faktury lub zmiana statusu) w ramach sesji (do). (optional)
     * @param statuses           Statusy sesji. | Wartość | Opis | | --- | --- | | Succeeded | Sesja przetworzona poprawnie.            W trakcie przetwarzania sesji nie wystąpiły żadne błędy, ale część faktur nadal mogła zostać odrzucona. | | InProgress | Sesja aktywna. | | Failed | Sesja nie przetworzona z powodu błędów.            Na etapie rozpoczynania lub kończenia sesji wystąpiły błędy, które nie pozwoliły na jej poprawne przetworzenie. |  (optional)
     * @param xContinuationToken Token służący do pobrania kolejnej strony wyników. (optional)
     * @throws ApiException if fails to make API call
     */
    private HttpRequest.Builder apiV2SessionsGetRequestBuilder(SessionType sessionType, Integer pageSize, String referenceNumber, OffsetDateTime dateCreatedFrom, OffsetDateTime dateCreatedTo, OffsetDateTime dateClosedFrom, OffsetDateTime dateClosedTo, OffsetDateTime dateModifiedFrom, OffsetDateTime dateModifiedTo, List<CommonSessionStatus> statuses, String xContinuationToken) throws ApiException {
        // verify the required parameter 'sessionType' is set
        if (sessionType == null) {
            throw new ApiException(400, "Missing the required parameter 'sessionType' when calling apiV2SessionsGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SESSION_LIST.getUrl();

        List<Pair> localVarQueryParams = new ArrayList<>();
        StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
        localVarQueryParams.addAll(ApiClient.parameterToPairs("pageSize", pageSize));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("sessionType", sessionType));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("referenceNumber", referenceNumber));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("dateCreatedFrom", dateCreatedFrom));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("dateCreatedTo", dateCreatedTo));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("dateClosedFrom", dateClosedFrom));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("dateClosedTo", dateClosedTo));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("dateModifiedFrom", dateModifiedFrom));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("dateModifiedTo", dateModifiedTo));
        localVarQueryParams.addAll(ApiClient.parameterToPairs("multi", "statuses", statuses));

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

        if (xContinuationToken != null) {
            localVarRequestBuilder.header("x-continuation-token", xContinuationToken.toString());
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
}
