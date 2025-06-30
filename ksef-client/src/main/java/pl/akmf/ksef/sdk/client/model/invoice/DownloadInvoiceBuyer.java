package pl.akmf.ksef.sdk.client.model.invoice;

/**
 * DownloadInvoiceBuyer
 */
public class DownloadInvoiceBuyer {
  private BuyerIdentifierType identifierType;

  private String identifier;

  private String name;

  public DownloadInvoiceBuyer() { 
  }

  public BuyerIdentifierType getIdentifierType() {
    return identifierType;
  }

  public void setIdentifierType(BuyerIdentifierType identifierType) {
    this.identifierType = identifierType;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

