package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.certificate.publickey.PublicKeyCertificate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

import static pl.akmf.ksef.sdk.api.Url.SECURITY_PUBLIC_KEY_CERTIFICATE;
import static pl.akmf.ksef.sdk.client.model.ApiException.getApiException;

public class PublicKeyCertificateApi extends BaseApi {
    private final ObjectMapper memberVarObjectMapper;
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;

    public PublicKeyCertificateApi(ApiClient apiClient) {
        super(apiClient.getHttpClient(),apiClient.getResponseInterceptor());
        memberVarObjectMapper = apiClient.getObjectMapper();
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
    }

    /**
     * Pobranie certyfikatów
     * Zwraca informacje o kluczach publicznych używanych do szyfrowania danych przesyłanych do systemu KSeF.
     *
     * @return ApiResponse&lt;List&lt;PublicKeyCertificate&gt;&gt;
     * @throws ApiException if fails to make API call
     */
    public ApiResponse<List<PublicKeyCertificate>> apiV2SecurityPublicKeyCertificatesGet() throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2SecurityPublicKeyCertificatesGetRequestBuilder();
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, SECURITY_PUBLIC_KEY_CERTIFICATE.getOperationId());

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

    private HttpRequest.Builder apiV2SecurityPublicKeyCertificatesGetRequestBuilder() {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = SECURITY_PUBLIC_KEY_CERTIFICATE.getUrl();

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
}
