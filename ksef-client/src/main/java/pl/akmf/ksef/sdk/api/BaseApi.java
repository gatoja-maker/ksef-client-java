package pl.akmf.ksef.sdk.api;

import pl.akmf.ksef.sdk.client.model.ApiException;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;

import static pl.akmf.ksef.sdk.client.model.ApiException.getApiException;

public abstract class BaseApi {
    private final HttpClient memberVarHttpClient;
    private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;

    public BaseApi(HttpClient memberVarHttpClient, Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor) {
        this.memberVarHttpClient = memberVarHttpClient;
        this.memberVarResponseInterceptor = memberVarResponseInterceptor;
    }

    protected HttpResponse<InputStream> getInputStreamHttpResponse(HttpRequest.Builder localVarRequestBuilder, String operationId) throws IOException, InterruptedException, ApiException {
        HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
                localVarRequestBuilder.build(),
                HttpResponse.BodyHandlers.ofInputStream());
        if (memberVarResponseInterceptor != null) {
            memberVarResponseInterceptor.accept(localVarResponse);
        }
        if (localVarResponse.statusCode() / 100 != 2) {
            throw getApiException(operationId, localVarResponse);
        }
        return localVarResponse;
    }
}
