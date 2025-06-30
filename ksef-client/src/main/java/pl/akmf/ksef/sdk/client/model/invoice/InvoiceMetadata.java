package pl.akmf.ksef.sdk.client.model.invoice;

import pl.akmf.ksef.sdk.client.model.session.FormCode;

import java.time.OffsetDateTime;


/**
 * InvoiceMetadata
 */
public class InvoiceMetadata {
  private String ksefNumber;

  private String invoiceNumber;

  private OffsetDateTime invoiceDate;

  private OffsetDateTime acquisitionDate;

  private InvoiceMetadataSeller seller;

  private InvoiceMetadataBuyer buyer;

  private Double netAmount;

  private Double grossAmount;

  private Double vatAmount;

  private CurrencyCode currency;

  private InvoicingMode invoicingMode;

  private InvoiceMetadataInvoiceType invoiceType;

  private FormCode formCode;

  private Boolean isHidden;

  private Boolean isSelfInvoicing;

  public InvoiceMetadata() { 
  }

  public String getKsefNumber() {
    return ksefNumber;
  }

  public void setKsefNumber(String ksefNumber) {
    this.ksefNumber = ksefNumber;
  }

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  public OffsetDateTime getInvoiceDate() {
    return invoiceDate;
  }

  public void setInvoiceDate(OffsetDateTime invoiceDate) {
    this.invoiceDate = invoiceDate;
  }

  public OffsetDateTime getAcquisitionDate() {
    return acquisitionDate;
  }

  public void setAcquisitionDate(OffsetDateTime acquisitionDate) {
    this.acquisitionDate = acquisitionDate;
  }

  public InvoiceMetadataSeller getSeller() {
    return seller;
  }

  public void setSeller(InvoiceMetadataSeller seller) {
    this.seller = seller;
  }

  public InvoiceMetadataBuyer getBuyer() {
    return buyer;
  }

  public void setBuyer(InvoiceMetadataBuyer buyer) {
    this.buyer = buyer;
  }

  public Double getNetAmount() {
    return netAmount;
  }

  public void setNetAmount(Double netAmount) {
    this.netAmount = netAmount;
  }

  public Double getGrossAmount() {
    return grossAmount;
  }

  public void setGrossAmount(Double grossAmount) {
    this.grossAmount = grossAmount;
  }

  public Double getVatAmount() {
    return vatAmount;
  }

  public void setVatAmount(Double vatAmount) {
    this.vatAmount = vatAmount;
  }

  public CurrencyCode getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyCode currency) {
    this.currency = currency;
  }

  public InvoicingMode getInvoicingMode() {
    return invoicingMode;
  }

  public void setInvoicingMode(InvoicingMode invoicingMode) {
    this.invoicingMode = invoicingMode;
  }

  public InvoiceMetadataInvoiceType getInvoiceType() {
    return invoiceType;
  }

  public void setInvoiceType(InvoiceMetadataInvoiceType invoiceType) {
    this.invoiceType = invoiceType;
  }

  public FormCode getFormCode() {
    return formCode;
  }

  public void setFormCode(FormCode formCode) {
    this.formCode = formCode;
  }

  public Boolean getHidden() {
    return isHidden;
  }

  public void setHidden(Boolean hidden) {
    isHidden = hidden;
  }

  public Boolean getSelfInvoicing() {
    return isSelfInvoicing;
  }

  public void setSelfInvoicing(Boolean selfInvoicing) {
    isSelfInvoicing = selfInvoicing;
  }
}

