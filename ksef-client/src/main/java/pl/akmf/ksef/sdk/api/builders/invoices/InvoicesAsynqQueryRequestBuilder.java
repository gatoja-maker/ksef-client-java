package pl.akmf.ksef.sdk.api.builders.invoices;

import pl.akmf.ksef.sdk.client.model.invoice.CurrencyCode;
import pl.akmf.ksef.sdk.client.model.invoice.InvoiceMetadataInvoiceType;
import pl.akmf.ksef.sdk.client.model.invoice.InvoiceMetadataSchema;
import pl.akmf.ksef.sdk.client.model.invoice.InvoiceQueryDateRange;
import pl.akmf.ksef.sdk.client.model.invoice.InvoiceQuerySubjectType;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequest;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequestAmount;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequestBuyer;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequestSeller;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicingMode;
import pl.akmf.ksef.sdk.client.model.session.EncryptionInfo;

import java.util.List;

public class InvoicesAsynqQueryRequestBuilder {
    private EncryptionInfo encryption;
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

    public InvoicesAsynqQueryRequestBuilder withEncryption(EncryptionInfo encryption) {
        this.encryption = encryption;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withSubjectType(InvoiceQuerySubjectType subjectType) {
        this.subjectType = subjectType;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withDateRange(InvoiceQueryDateRange dateRange) {
        this.dateRange = dateRange;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withKsefNumber(String ksefNumber) {
        this.ksefNumber = ksefNumber;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withAmount(InvoicesAsynqQueryRequestAmount amount) {
        this.amount = amount;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withSeller(InvoicesAsynqQueryRequestSeller seller) {
        this.seller = seller;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withBuyer(InvoicesAsynqQueryRequestBuyer buyer) {
        this.buyer = buyer;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withCurrencyCodes(List<CurrencyCode> currencyCodes) {
        this.currencyCodes = currencyCodes;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withInvoicingMode(InvoicingMode invoicingMode) {
        this.invoicingMode = invoicingMode;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withIsSelfInvoicing(Boolean isSelfInvoicing) {
        this.isSelfInvoicing = isSelfInvoicing;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withInvoiceSchema(InvoiceMetadataSchema invoiceSchema) {
        this.invoiceSchema = invoiceSchema;
        return this;
    }

    public InvoicesAsynqQueryRequestBuilder withInvoiceTypes(List<InvoiceMetadataInvoiceType> invoiceTypes) {
        this.invoiceTypes = invoiceTypes;
        return this;
    }

    public InvoicesAsynqQueryRequest build() {
        InvoicesAsynqQueryRequest request = new InvoicesAsynqQueryRequest();
        request.setEncryption(encryption);
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

