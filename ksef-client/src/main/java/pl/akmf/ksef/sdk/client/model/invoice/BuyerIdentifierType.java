package pl.akmf.ksef.sdk.client.model.invoice;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets BuyerIdentifierType
 */
public enum BuyerIdentifierType {
  
  NONE("None"),
  
  OTHER("Other"),
  
  NIP("Nip"),
  
  VATUE("VatUe");

  private String value;

  BuyerIdentifierType(String value) {
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
  public static BuyerIdentifierType fromValue(String value) {
    for (BuyerIdentifierType b : BuyerIdentifierType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  /**
   * Convert the instance into URL query string.
   *
   * @param prefix prefix of the query string
   * @return URL query string
   */
  public String toUrlQueryString(String prefix) {
    if (prefix == null) {
      prefix = "";
    }

    return String.format("%s=%s", prefix, this.toString());
  }

}

