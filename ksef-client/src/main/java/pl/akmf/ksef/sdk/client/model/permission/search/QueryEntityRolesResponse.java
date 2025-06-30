package pl.akmf.ksef.sdk.client.model.permission.search;

import java.util.List;


public class QueryEntityRolesResponse {
    private List<EntityRole> roles;
    private Integer page;
    private Integer pageSize;

    QueryEntityRolesResponse() {
    }

    public QueryEntityRolesResponse(List<EntityRole> roles, Integer page, Integer pageSize) {
        this.roles = roles;
        this.page = page;
        this.pageSize = pageSize;
    }

    public List<EntityRole> getRoles() {
        return roles;
    }

    public void setRoles(List<EntityRole> roles) {
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
}

