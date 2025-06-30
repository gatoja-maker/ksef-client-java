package pl.akmf.ksef.sdk.client.model.permission.search;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EntityAuthorizationsAuthorizingEntityIdentifierType {
  
  NIP("Nip");

  private final String value;

  EntityAuthorizationsAuthorizingEntityIdentifierType(String value) {
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
  public static EntityAuthorizationsAuthorizingEntityIdentifierType fromValue(String value) {
    for (EntityAuthorizationsAuthorizingEntityIdentifierType b : EntityAuthorizationsAuthorizingEntityIdentifierType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

