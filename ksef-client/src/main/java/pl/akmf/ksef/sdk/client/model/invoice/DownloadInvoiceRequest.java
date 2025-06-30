package pl.akmf.ksef.sdk.client.model.invoice;

/**
 * DownloadInvoiceRequest
 */
public class DownloadInvoiceRequest {
  private String ksefNumber;

  private DwonloadInvoiceMetadata invoiceMetadata;

  public DownloadInvoiceRequest() { 
  }

  public String getKsefNumber() {
    return ksefNumber;
  }

  public void setKsefNumber(String ksefNumber) {
    this.ksefNumber = ksefNumber;
  }

  public DwonloadInvoiceMetadata getInvoiceMetadata() {
    return invoiceMetadata;
  }

  public void setInvoiceMetadata(DwonloadInvoiceMetadata invoiceMetadata) {
    this.invoiceMetadata = invoiceMetadata;
  }
}

