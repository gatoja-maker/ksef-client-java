package pl.akmf.ksef.sdk.client.model.permission.person;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PersonPermissionsSubjectIdentifierType {

    NIP("Nip"),
    PESEL("Pesel"),
    FINGERPRINT("Fingerprint");

    private final String value;

    PersonPermissionsSubjectIdentifierType(String value) {
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
    public static PersonPermissionsSubjectIdentifierType fromValue(String value) {
        for (PersonPermissionsSubjectIdentifierType b : PersonPermissionsSubjectIdentifierType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

