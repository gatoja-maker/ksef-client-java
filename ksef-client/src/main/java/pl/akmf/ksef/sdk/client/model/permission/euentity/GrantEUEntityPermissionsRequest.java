package pl.akmf.ksef.sdk.client.model.permission.euentity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrantEUEntityPermissionsRequest {
  @JsonProperty("subjectIdentifier")
  private SubjectIdentifier subjectIdentifier;
  @JsonProperty("contextIdentifier")
  private ContextIdentifier contextIdentifier;
  @JsonProperty("description")
  private String description;

  public SubjectIdentifier getSubjectIdentifier() {
    return subjectIdentifier;
  }

  public void setSubjectIdentifier(SubjectIdentifier subjectIdentifier) {
    this.subjectIdentifier = subjectIdentifier;
  }

  public ContextIdentifier getContextIdentifier() {
    return contextIdentifier;
  }

  public void setContextIdentifier(ContextIdentifier contextIdentifier) {
    this.contextIdentifier = contextIdentifier;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

