package pl.akmf.ksef.sdk.client.model.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TokenPermissionType {
    INVOICEREAD("InvoiceRead"),
    INVOICEWRITE("InvoiceWrite"),
    CREDENTIALSREAD("CredentialsRead"),
    CREDENTIALSMANAGE("CredentialsManage");

    private final String value;

    TokenPermissionType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static TokenPermissionType fromValue(String value) {
        for (TokenPermissionType b : TokenPermissionType.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
