package pl.akmf.ksef.sdk.client.model.permission.search;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PersonPermissionsAuthorIdentifierType {
  
  NIP("Nip"),
  
  PESEL("Pesel"),
  
  FINGERPRINT("Fingerprint");

  private final String value;

  PersonPermissionsAuthorIdentifierType(String value) {
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
  public static PersonPermissionsAuthorIdentifierType fromValue(String value) {
    for (PersonPermissionsAuthorIdentifierType b : PersonPermissionsAuthorIdentifierType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

