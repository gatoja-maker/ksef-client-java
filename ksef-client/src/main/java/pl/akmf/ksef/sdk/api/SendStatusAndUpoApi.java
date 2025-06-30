package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.Pair;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoice;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoicesResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionStatusResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

public class SendStatusAndUpoApi {
    private final HttpClient memberVarHttpClient;
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;
    private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;

    public SendStatusAndUpoApi() {
        this(new ApiClient());
    }

    public SendStatusAndUpoApi(ApiClient apiClient) {
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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2SessionsReferenceNumberInvoicesFailedGet", localVarResponse);
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

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesFailedGetRequestBuilder(String referenceNumber, String xContinuationToken, Integer pageSize) throws ApiException {
        // verify the required parameter 'referenceNumber' is set
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesFailedGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/sessions/{referenceNumber}/invoices/failed"
                .replace("{referenceNumber}", ApiClient.urlEncode(referenceNumber.toString()));

        List<Pair> localVarQueryParams = new ArrayList<>();
        StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
        localVarQueryParams.addAll(ApiClient.parameterToPairs("pageSize", pageSize));

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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2SessionsReferenceNumberInvoicesGet", localVarResponse);
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

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesGetRequestBuilder(String referenceNumber, Integer pageOffset, Integer pageSize) throws ApiException {
        // verify the required parameter 'referenceNumber' is set
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/sessions/{referenceNumber}/invoices"
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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGet", localVarResponse);
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

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGetRequestBuilder(String referenceNumber, String invoiceReferenceNumber) throws ApiException {
        // verify the required parameter 'referenceNumber' is set
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGet");
        }
        // verify the required parameter 'invoiceReferenceNumber' is set
        if (invoiceReferenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'invoiceReferenceNumber' when calling apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/sessions/{referenceNumber}/invoices/{invoiceReferenceNumber}"
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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGet", localVarResponse);
            }
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : localVarResponse.body().readAllBytes() // closes the InputStream
            );

        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGetRequestBuilder(String referenceNumber, String invoiceReferenceNumber) throws ApiException {
        // verify the required parameter 'referenceNumber' is set
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGet");
        }
        // verify the required parameter 'invoiceReferenceNumber' is set
        if (invoiceReferenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'invoiceReferenceNumber' when calling apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/sessions/{referenceNumber}/invoices/{invoiceReferenceNumber}/upo"
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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGet", localVarResponse);
            }
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : localVarResponse.body().readAllBytes()  // closes the InputStream
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGetRequestBuilder(String referenceNumber, String ksefNumber) throws ApiException {
        // verify the required parameter 'referenceNumber' is set
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGet");
        }
        // verify the required parameter 'ksefNumber' is set
        if (ksefNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'ksefNumber' when calling apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/sessions/{referenceNumber}/invoices/ksef/{ksefNumber}/upo"
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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2SessionsReferenceNumberGet", localVarResponse);
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

    private HttpRequest.Builder apiV2SessionsReferenceNumberGetRequestBuilder(String referenceNumber) throws ApiException {
        // verify the required parameter 'referenceNumber' is set
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/sessions/{referenceNumber}"
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
            HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                    localVarRequestBuilder.build(),
                    HttpResponse.BodyHandlers.ofInputStream());
            if (memberVarResponseInterceptor != null) {
                memberVarResponseInterceptor.accept(localVarResponse);
            }
            if (localVarResponse.statusCode() / 100 != 2) {
                throw getApiException("apiV2SessionsReferenceNumberUpoUpoReferenceNumberGet", localVarResponse);
            }
            return new ApiResponse<>(
                    localVarResponse.statusCode(),
                    localVarResponse.headers().map(),
                    localVarResponse.body() == null ? null : localVarResponse.body().readAllBytes() // closes the InputStream
            );
        } catch (IOException e) {
            throw new ApiException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException(e);
        }
    }

    private HttpRequest.Builder apiV2SessionsReferenceNumberUpoUpoReferenceNumberGetRequestBuilder(String referenceNumber, String upoReferenceNumber) throws ApiException {
        // verify the required parameter 'referenceNumber' is set
        if (referenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'referenceNumber' when calling apiV2SessionsReferenceNumberUpoUpoReferenceNumberGet");
        }
        // verify the required parameter 'upoReferenceNumber' is set
        if (upoReferenceNumber == null) {
            throw new ApiException(400, "Missing the required parameter 'upoReferenceNumber' when calling apiV2SessionsReferenceNumberUpoUpoReferenceNumberGet");
        }

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = "/api/v2/sessions/{referenceNumber}/upo/{upoReferenceNumber}"
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

}
