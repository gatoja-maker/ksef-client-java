package pl.akmf.ksef.sdk.client.model.permission.search;

import java.util.List;

public class QueryPersonPermissionsResponse {
    private List<PersonPermission> permissions;
    private Integer page;
    private Integer pageSize;

    QueryPersonPermissionsResponse() {
    }

    public QueryPersonPermissionsResponse(List<PersonPermission> permissions, Integer page, Integer pageSize) {
        this.permissions = permissions;
        this.page = page;
        this.pageSize = pageSize;
    }

    public List<PersonPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PersonPermission> permissions) {
        this.permissions = permissions;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

