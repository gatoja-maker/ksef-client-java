package pl.akmf.ksef.sdk.client.model.permission.euentity;

import java.util.List;

public class GrantEUEntityRepresentativePermissionsRequest {
  private SubjectIdentifier subjectIdentifier;
  private List<EuEntityPermissionType> permissions;
  private String description;

  public SubjectIdentifier getSubjectIdentifier() {
    return subjectIdentifier;
  }

  public void setSubjectIdentifier(SubjectIdentifier subjectIdentifier) {
    this.subjectIdentifier = subjectIdentifier;
  }

  public List<EuEntityPermissionType> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<EuEntityPermissionType> permissions) {
    this.permissions = permissions;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

