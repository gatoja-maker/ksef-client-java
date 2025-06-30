package pl.akmf.ksef.sdk.client.model.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SubjectIdentifierType {
    NONE("None"),
    NIP("Nip"),
    PESEL("Pesel"),
    FINGERPRINT("Fingerprint"),
    TOKEN("Token");

    private final String value;

    SubjectIdentifierType(String value) {
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
    public static SubjectIdentifierType fromValue(String value) {
        for (SubjectIdentifierType b : SubjectIdentifierType.values()) {
            if (b.value.equalsIgnoreCase(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

