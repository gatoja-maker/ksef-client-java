package pl.akmf.ksef.sdk.client.model.permission.entity;

import java.util.List;

public class GrantEntityPermissionsRequest {
  private SubjectIdentifier subjectIdentifier;
  private List<EntityPermission> permissions;
  private String description;

  public SubjectIdentifier getSubjectIdentifier() {
    return subjectIdentifier;
  }

  public void setSubjectIdentifier(SubjectIdentifier subjectIdentifier) {
    this.subjectIdentifier = subjectIdentifier;
  }

  public List<EntityPermission> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<EntityPermission> permissions) {
    this.permissions = permissions;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

