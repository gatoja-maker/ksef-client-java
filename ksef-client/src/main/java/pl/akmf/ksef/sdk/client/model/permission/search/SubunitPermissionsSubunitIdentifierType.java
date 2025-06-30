package pl.akmf.ksef.sdk.client.model.permission.search;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SubunitPermissionsSubunitIdentifierType {
  
  INTERNALID("InternalId"),
  NIP("Nip");

  private final String value;

  SubunitPermissionsSubunitIdentifierType(String value) {
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
  public static SubunitPermissionsSubunitIdentifierType fromValue(String value) {
    for (SubunitPermissionsSubunitIdentifierType b : SubunitPermissionsSubunitIdentifierType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

