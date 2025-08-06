package pl.akmf.ksef.sdk.api;

import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.function.Consumer;

import static pl.akmf.ksef.sdk.api.Url.PUBLIC_KEY;
import static pl.akmf.ksef.sdk.client.model.ApiException.getApiException;

public class PublicKeyEnvironmentApi extends BaseApi {
    private final String memberVarBaseUri;
    private final Consumer<HttpRequest.Builder> memberVarInterceptor;
    private final Duration memberVarReadTimeout;

    public PublicKeyEnvironmentApi(ApiClient apiClient) {
        super(apiClient.getHttpClient(),apiClient.getResponseInterceptor());
        memberVarBaseUri = apiClient.getBaseUri();
        memberVarInterceptor = apiClient.getRequestInterceptor();
        memberVarReadTimeout = apiClient.getReadTimeout();
    }

    /**
     * Pobranie klucza publicznego dla danego środowiska
     *
     * @return byte[]
     * @throws ApiException
     */
    public byte[] apiV2SPublicKeyGet() throws ApiException {
        ApiResponse<byte[]> localVarResponse = apiV2PublicKeyGetWithHttpInfo();
        return localVarResponse.getData();
    }

    public ApiResponse<byte[]> apiV2PublicKeyGetWithHttpInfo() throws ApiException {
        HttpRequest.Builder localVarRequestBuilder = apiV2PublicKeyGetRequestBuilder();
        try {
            HttpResponse<InputStream> localVarResponse = getInputStreamHttpResponse(localVarRequestBuilder, PUBLIC_KEY.getOperationId());

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

    private HttpRequest.Builder apiV2PublicKeyGetRequestBuilder() {

        HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

        String localVarPath = PUBLIC_KEY.getUrl();

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
