package pl.akmf.ksef.sdk.client.model.permission.search;

import java.util.List;

public class QuerySubordinateEntityRolesResponse {
    private List<SubordinateEntityRole> roles;
    private Integer page;
    private Integer pageSize;

    QuerySubordinateEntityRolesResponse() {
    }

    public QuerySubordinateEntityRolesResponse(List<SubordinateEntityRole> roles, Integer page, Integer pageSize) {
        this.roles = roles;
        this.page = page;
        this.pageSize = pageSize;
    }

    public List<SubordinateEntityRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SubordinateEntityRole> roles) {
        this.roles = roles;
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

