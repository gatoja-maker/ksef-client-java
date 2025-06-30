package pl.akmf.ksef.sdk.api.builders.session;

import pl.akmf.ksef.sdk.client.model.session.online.SendInvoiceRequest;

public class SendInvoiceRequestBuilder {
    private String invoiceHash;
    private long invoiceSize;
    private String encryptedInvoiceHash;
    private long encryptedInvoiceSize;
    private String encryptedInvoiceContent;
    private String hashOfCorrectedInvoice;
    private boolean offlineMode;

    public SendInvoiceRequestBuilder withInvoiceHash(String invoiceHash) {
        this.invoiceHash = invoiceHash;
        return this;
    }

    public SendInvoiceRequestBuilder withInvoiceSize(long invoiceSize) {
        this.invoiceSize = invoiceSize;
        return this;
    }

    public SendInvoiceRequestBuilder withEncryptedInvoiceHash(String encryptedInvoiceHash) {
        this.encryptedInvoiceHash = encryptedInvoiceHash;
        return this;
    }

    public SendInvoiceRequestBuilder withEncryptedInvoiceSize(long encryptedInvoiceSize) {
        this.encryptedInvoiceSize = encryptedInvoiceSize;
        return this;
    }

    public SendInvoiceRequestBuilder withHashOfCorrectedInvoice(String hashOfCorrectedInvoice) {
        this.hashOfCorrectedInvoice = hashOfCorrectedInvoice;
        return this;
    }
    public SendInvoiceRequestBuilder withOfflineMode(boolean offlineMode) {
        this.offlineMode = offlineMode;
        return this;
    }
    public SendInvoiceRequestBuilder withEncryptedInvoiceContent(String encryptedInvoiceContent) {
        this.encryptedInvoiceContent = encryptedInvoiceContent;
        return this;
    }

    public SendInvoiceRequest build() {
        var request = new SendInvoiceRequest();
        request.setInvoiceHash(invoiceHash);
        request.setInvoiceSize(invoiceSize);
        request.setEncryptedInvoiceHash(encryptedInvoiceHash);
        request.setEncryptedInvoiceSize(encryptedInvoiceSize);
        request.setEncryptedInvoiceContent(encryptedInvoiceContent);
        request.setHashOfCorrectedInvoice(hashOfCorrectedInvoice);
        request.setOfflineMode(offlineMode);

        return request;
    }
}
