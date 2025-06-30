package pl.akmf.ksef.sdk.client.model.certificate.publickey;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets pl.akmf.ksef.sdk.client.model.certificate.publickey.PublicKeyCertificateUsage
 */
public enum PublicKeyCertificateUsage {
  
  KSEFTOKENENCRYPTION("KsefTokenEncryption"),
  
  SYMMETRICKEYENCRYPTION("SymmetricKeyEncryption");

  private String value;

  PublicKeyCertificateUsage(String value) {
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
  public static PublicKeyCertificateUsage fromValue(String value) {
    for (PublicKeyCertificateUsage b : PublicKeyCertificateUsage.values()) {
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

