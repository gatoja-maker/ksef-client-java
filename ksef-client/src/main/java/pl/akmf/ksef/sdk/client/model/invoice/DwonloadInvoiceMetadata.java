package pl.akmf.ksef.sdk.client.model.invoice;

/**
 * DwonloadInvoiceMetadata
 */
public class DwonloadInvoiceMetadata {
  private String number;

  private Double totalAmountDue;

  private DownloadInvoiceBuyer buyer;

  public DwonloadInvoiceMetadata() { 
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Double getTotalAmountDue() {
    return totalAmountDue;
  }

  public void setTotalAmountDue(Double totalAmountDue) {
    this.totalAmountDue = totalAmountDue;
  }

  public DownloadInvoiceBuyer getBuyer() {
    return buyer;
  }

  public void setBuyer(DownloadInvoiceBuyer buyer) {
    this.buyer = buyer;
  }
}

