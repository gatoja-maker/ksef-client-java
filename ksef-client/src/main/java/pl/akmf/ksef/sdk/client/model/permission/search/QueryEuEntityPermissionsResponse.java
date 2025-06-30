package pl.akmf.ksef.sdk.client.model.permission.search;

import java.util.List;


public class QueryEuEntityPermissionsResponse {
    private List<EuEntityPermission> permissions;
    private Integer page;
    private Integer pageSize;

    QueryEuEntityPermissionsResponse() {
    }

    public QueryEuEntityPermissionsResponse(List<EuEntityPermission> permissions, Integer page, Integer pageSize) {
        this.permissions = permissions;
        this.page = page;
        this.pageSize = pageSize;
    }

    public List<EuEntityPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<EuEntityPermission> permissions) {
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

