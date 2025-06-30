package pl.akmf.ksef.sdk.client.model.invoice;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Map;


/**
 * InvoicePackagePart
 */
public class InvoicePackagePart {
  private Integer ordinalNumber;

  private String method;

  private URI url;

  private OffsetDateTime expirationDate;

  private Map<String, String> headers ;

  public InvoicePackagePart() { 
  }

  public Integer getOrdinalNumber() {
    return ordinalNumber;
  }

  public void setOrdinalNumber(Integer ordinalNumber) {
    this.ordinalNumber = ordinalNumber;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public URI getUrl() {
    return url;
  }

  public void setUrl(URI url) {
    this.url = url;
  }

  public OffsetDateTime getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(OffsetDateTime expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }
}

