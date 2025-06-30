package pl.akmf.ksef.sdk.client.model.permission;

import java.io.Serializable;

public class PermissionsOperationResponse implements Serializable {
    private String operationReferenceNumber;

    public PermissionsOperationResponse() {
    }

    public PermissionsOperationResponse(String operationReferenceNumber) {
        this.operationReferenceNumber = operationReferenceNumber;
    }

    public String getOperationReferenceNumber() {
        return operationReferenceNumber;
    }

    public void setOperationReferenceNumber(String operationReferenceNumber) {
        this.operationReferenceNumber = operationReferenceNumber;
    }
}
