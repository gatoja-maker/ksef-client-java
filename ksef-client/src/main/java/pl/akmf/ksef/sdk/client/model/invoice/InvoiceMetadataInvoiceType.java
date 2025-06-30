package pl.akmf.ksef.sdk.client.model.invoice;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets InvoiceMetadataInvoiceType
 */
public enum InvoiceMetadataInvoiceType {
  
  VAT("Vat"),
  
  KOR("Kor"),
  
  ZAL("Zal"),
  
  ROZ("Roz"),
  
  UPR("Upr"),
  
  KOR_ZAL("Kor_Zal"),
  
  KOR_ROZ("Kor_Roz");

  private String value;

  InvoiceMetadataInvoiceType(String value) {
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
  public static InvoiceMetadataInvoiceType fromValue(String value) {
    for (InvoiceMetadataInvoiceType b : InvoiceMetadataInvoiceType.values()) {
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

