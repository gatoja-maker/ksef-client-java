package pl.akmf.ksef.sdk.client.model.invoice;

import pl.akmf.ksef.sdk.client.model.StatusInfo;

import java.util.List;


/**
 * AsyncInvoicesQueryStatus
 */
public class AsyncInvoicesQueryStatus {
    private String operationReferenceNumber;

    private StatusInfo status;

    private List<InvoicePackagePart> packageParts;

    public AsyncInvoicesQueryStatus() {
    }

    public String getOperationReferenceNumber() {
        return operationReferenceNumber;
    }

    public void setOperationReferenceNumber(String operationReferenceNumber) {
        this.operationReferenceNumber = operationReferenceNumber;
    }

    public StatusInfo getStatus() {
        return status;
    }

    public void setStatus(StatusInfo status) {
        this.status = status;
    }

    public List<InvoicePackagePart> getPackageParts() {
        return packageParts;
    }

    public void setPackageParts(List<InvoicePackagePart> packageParts) {
        this.packageParts = packageParts;
    }
}

