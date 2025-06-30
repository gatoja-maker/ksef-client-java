package pl.akmf.ksef.sdk.client.model.permission.proxy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProxyEntityPermissionType {

    TAXREPRESENTATIVE("TaxRepresentative"),

    SELFINVOICING("SelfInvoicing"),

    RRINVOICING("RrInvoicing");

    private final String value;

    ProxyEntityPermissionType(String value) {
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
    public static ProxyEntityPermissionType fromValue(String value) {
        for (ProxyEntityPermissionType b : ProxyEntityPermissionType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

