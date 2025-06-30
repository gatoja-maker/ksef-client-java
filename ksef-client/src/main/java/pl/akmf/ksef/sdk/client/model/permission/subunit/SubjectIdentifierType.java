package pl.akmf.ksef.sdk.client.model.permission.subunit;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SubjectIdentifierType {
  NIP("Nip"),
  PESEL("Pesel"),
  FINGERPRINT("Fingerprint");

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
}

