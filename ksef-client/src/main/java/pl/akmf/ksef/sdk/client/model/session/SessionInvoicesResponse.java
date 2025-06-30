package pl.akmf.ksef.sdk.client.model.session;

import java.util.List;

public class SessionInvoicesResponse {
    private String continuationToken;
    private List<SessionInvoice> invoices;
    private Integer totalCount;

    public SessionInvoicesResponse() {
    }

    public SessionInvoicesResponse(String continuationToken, List<SessionInvoice> invoices, Integer totalCount) {
        this.continuationToken = continuationToken;
        this.invoices = invoices;
        this.totalCount = totalCount;
    }

    public String getContinuationToken() {
        return continuationToken;
    }

    public void setContinuationToken(String continuationToken) {
        this.continuationToken = continuationToken;
    }

    public List<SessionInvoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<SessionInvoice> invoices) {
        this.invoices = invoices;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}

