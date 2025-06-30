package pl.akmf.ksef.sdk.api.builders.invoices;

import pl.akmf.ksef.sdk.client.model.invoice.CurrencyCode;
import pl.akmf.ksef.sdk.client.model.invoice.InvoiceMetadataInvoiceType;
import pl.akmf.ksef.sdk.client.model.invoice.InvoiceMetadataSchema;
import pl.akmf.ksef.sdk.client.model.invoice.InvoiceQueryDateRange;
import pl.akmf.ksef.sdk.client.model.invoice.InvoiceQuerySubjectType;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequestAmount;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequestBuyer;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequestSeller;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesQueryRequest;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicingMode;

import java.util.List;

public class InvoicesQueryRequestBuilder {
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

    public InvoicesQueryRequestBuilder withSubjectType(InvoiceQuerySubjectType subjectType) {
        this.subjectType = subjectType;
        return this;
    }

    public InvoicesQueryRequestBuilder withDateRange(InvoiceQueryDateRange dateRange) {
        this.dateRange = dateRange;
        return this;
    }

    public InvoicesQueryRequestBuilder withKsefNumber(String ksefNumber) {
        this.ksefNumber = ksefNumber;
        return this;
    }

    public InvoicesQueryRequestBuilder withInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public InvoicesQueryRequestBuilder withAmount(InvoicesAsynqQueryRequestAmount amount) {
        this.amount = amount;
        return this;
    }

    public InvoicesQueryRequestBuilder withSeller(InvoicesAsynqQueryRequestSeller seller) {
        this.seller = seller;
        return this;
    }

    public InvoicesQueryRequestBuilder withBuyer(InvoicesAsynqQueryRequestBuyer buyer) {
        this.buyer = buyer;
        return this;
    }

    public InvoicesQueryRequestBuilder withCurrencyCodes(List<CurrencyCode> currencyCodes) {
        this.currencyCodes = currencyCodes;
        return this;
    }

    public InvoicesQueryRequestBuilder withIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
        return this;
    }

    public InvoicesQueryRequestBuilder withInvoicingMode(InvoicingMode invoicingMode) {
        this.invoicingMode = invoicingMode;
        return this;
    }

    public InvoicesQueryRequestBuilder withIsSelfInvoicing(Boolean isSelfInvoicing) {
        this.isSelfInvoicing = isSelfInvoicing;
        return this;
    }

    public InvoicesQueryRequestBuilder withInvoiceSchema(InvoiceMetadataSchema invoiceSchema) {
        this.invoiceSchema = invoiceSchema;
        return this;
    }

    public InvoicesQueryRequestBuilder withInvoiceTypes(List<InvoiceMetadataInvoiceType> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
        return this;
    }

    public InvoicesQueryRequest build() {
        InvoicesQueryRequest request = new InvoicesQueryRequest();
        request.setSubjectType(subjectType);
        request.setDateRange(dateRange);
        request.setKsefNumber(ksefNumber);
        request.setInvoiceNumber(invoiceNumber);
        request.setAmount(amount);
        request.setSeller(seller);
        request.setBuyer(buyer);
        request.setCurrencyCodes(currencyCodes);
        request.setHidden(isHidden);
        request.setInvoicingMode(invoicingMode);
        request.setSelfInvoicing(isSelfInvoicing);
        request.setInvoiceSchema(invoiceSchema);
        request.setInvoiceTypes(invoiceTypes);
        return request;
    }
}

