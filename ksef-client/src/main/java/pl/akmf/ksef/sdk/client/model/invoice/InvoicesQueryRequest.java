package pl.akmf.ksef.sdk.client.model.invoice;

import java.util.List;


/**
 * InvoicesQueryRequest
 */
public class InvoicesQueryRequest {
    private InvoiceQuerySubjectType subjectType;

    private InvoiceQueryDateRange dateRange;

    private String ksefNumber;

    private String invoiceNumber;

    private InvoicesAsynqQueryRequestAmount amount;

    private InvoicesAsynqQueryRequestSeller seller;

    private InvoicesAsynqQueryRequestBuyer buyer;

    private List<CurrencyCode> currencyCodes;

    private Boolean isHidden;

    private InvoicingMode invoicingMode;

    private Boolean isSelfInvoicing;

    private InvoiceMetadataSchema invoiceSchema;

    private List<InvoiceMetadataInvoiceType> invoiceTypes;

    public InvoicesQueryRequest() {
    }

    public InvoiceQuerySubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(InvoiceQuerySubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public InvoiceQueryDateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(InvoiceQueryDateRange dateRange) {
        this.dateRange = dateRange;
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

    public InvoicesAsynqQueryRequestAmount getAmount() {
        return amount;
    }

    public void setAmount(InvoicesAsynqQueryRequestAmount amount) {
        this.amount = amount;
    }

    public InvoicesAsynqQueryRequestSeller getSeller() {
        return seller;
    }

    public void setSeller(InvoicesAsynqQueryRequestSeller seller) {
        this.seller = seller;
    }

    public InvoicesAsynqQueryRequestBuyer getBuyer() {
        return buyer;
    }

    public void setBuyer(InvoicesAsynqQueryRequestBuyer buyer) {
        this.buyer = buyer;
    }

    public List<CurrencyCode> getCurrencyCodes() {
        return currencyCodes;
    }

    public void setCurrencyCodes(List<CurrencyCode> currencyCodes) {
        this.currencyCodes = currencyCodes;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public InvoicingMode getInvoicingMode() {
        return invoicingMode;
    }

    public void setInvoicingMode(InvoicingMode invoicingMode) {
        this.invoicingMode = invoicingMode;
    }

    public Boolean getSelfInvoicing() {
        return isSelfInvoicing;
    }

    public void setSelfInvoicing(Boolean selfInvoicing) {
        isSelfInvoicing = selfInvoicing;
    }

    public InvoiceMetadataSchema getInvoiceSchema() {
        return invoiceSchema;
    }

    public void setInvoiceSchema(InvoiceMetadataSchema invoiceSchema) {
        this.invoiceSchema = invoiceSchema;
    }

    public List<InvoiceMetadataInvoiceType> getInvoiceTypes() {
        return invoiceTypes;
    }

    public void setInvoiceTypes(List<InvoiceMetadataInvoiceType> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
    }
}

