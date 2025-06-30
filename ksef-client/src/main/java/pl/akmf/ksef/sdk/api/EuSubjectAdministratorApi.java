package pl.akmf.ksef.sdk.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.akmf.ksef.sdk.client.model.ApiClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.ApiResponse;
import pl.akmf.ksef.sdk.client.model.permission.PermissionsOperationResponse;
import pl.akmf.ksef.sdk.client.model.permission.euentity.GrantEUEntityPermissionsRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.function.Consumer;

public class EuSubjectAdministratorApi {
  private final HttpClient memberVarHttpClient;
  private final ObjectMapper memberVarObjectMapper;
  private final String memberVarBaseUri;
  private final Consumer<HttpRequest.Builder> memberVarInterceptor;
  private final Duration memberVarReadTimeout;
  private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;

  public EuSubjectAdministratorApi() {
    this(new ApiClient());
  }

  public EuSubjectAdministratorApi(ApiClient apiClient) {
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
    return new ApiException(response.statusCode(), message);
  }

  private String formatExceptionMessage(String operationId, int statusCode, String body) {
    if (body == null || body.isEmpty()) {
      body = "[no body]";
    }
    return operationId + " call failed with: " + statusCode + " - " + body;
  }

  /**
   * [mock] Pobranie listy uprawnień administratora podmiotu unijnego
   * 
   * @return ApiResponse&lt;Void&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<Void> apiV2PermissionsEuEntitiesAdministrationGrantsGetWithHttpInfo() throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = apiV2PermissionsEuEntitiesAdministrationGrantsGetRequestBuilder();
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
      try {
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("apiV2PermissionsEuEntitiesAdministrationGrantsGet", localVarResponse);
        }
        return new ApiResponse<>(
                localVarResponse.statusCode(),
                localVarResponse.headers().map(),
                null
        );
      } finally {
        // Drain the InputStream
        localVarResponse.body().close();
      }
    } catch (IOException e) {
      throw new ApiException(e);
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ApiException(e);
    }
  }

  private HttpRequest.Builder apiV2PermissionsEuEntitiesAdministrationGrantsGetRequestBuilder() {

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/api/v2/permissions/eu-entities/administration/grants";

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
   * Nadanie uprawnień administratora podmiotu unijnego
   * 
   * @param grantEUEntityPermissionsRequest  (optional)
   * @return ApiResponse&lt;PermissionsOperationResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public ApiResponse<PermissionsOperationResponse> apiV2PermissionsEuEntitiesAdministrationGrantsPost(GrantEUEntityPermissionsRequest grantEUEntityPermissionsRequest) throws ApiException {
    HttpRequest.Builder localVarRequestBuilder = apiV2PermissionsEuEntitiesAdministrationGrantsPostRequestBuilder(grantEUEntityPermissionsRequest);
    try {
      HttpResponse<InputStream> localVarResponse = memberVarHttpClient.send(
          localVarRequestBuilder.build(),
          HttpResponse.BodyHandlers.ofInputStream());
      if (memberVarResponseInterceptor != null) {
        memberVarResponseInterceptor.accept(localVarResponse);
      }
        if (localVarResponse.statusCode()/ 100 != 2) {
          throw getApiException("apiV2PermissionsEuEntitiesAdministrationGrantsPost", localVarResponse);
        }
        return new ApiResponse<>(
                localVarResponse.statusCode(),
                localVarResponse.headers().map(),
                localVarResponse.body() == null ? null : memberVarObjectMapper.readValue(localVarResponse.body(), new TypeReference<>() {
                }) // closes the InputStream
        );
    } catch (IOException e) {
      throw new ApiException(e);
    }
    catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new ApiException(e);
    }
  }

  private HttpRequest.Builder apiV2PermissionsEuEntitiesAdministrationGrantsPostRequestBuilder(GrantEUEntityPermissionsRequest grantEUEntityPermissionsRequest) throws ApiException {

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/api/v2/permissions/eu-entities/administration/grants";

    localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(grantEUEntityPermissionsRequest);
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
