package pl.akmf.ksef.sdk.client.model.session;

import java.util.ArrayList;
import java.util.List;


/**
 * AuthenticationListResponse
 */
public class AuthenticationListResponse {
  private String continuationToken;

  private List<AuthenticationOperationStatusResponse> items = new ArrayList<>();

  public AuthenticationListResponse() { 
  }

  public String getContinuationToken() {
    return continuationToken;
  }

  public void setContinuationToken(String continuationToken) {
    this.continuationToken = continuationToken;
  }

  public List<AuthenticationOperationStatusResponse> getItems() {
    return items;
  }

  public void setItems(List<AuthenticationOperationStatusResponse> items) {
    this.items = items;
  }
}

