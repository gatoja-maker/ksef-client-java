package pl.akmf.ksef.sdk.client.model.permission.search;

import java.util.List;

public class QueryEntityAuthorizationPermissionsResponse {
    private List<EntityAuthorizationGrant> authorizationGrants;
    private Integer page;
    private Integer pageSize;

    QueryEntityAuthorizationPermissionsResponse() {
    }

    public QueryEntityAuthorizationPermissionsResponse(List<EntityAuthorizationGrant> authorizationGrants, Integer page, Integer pageSize) {
        this.authorizationGrants = authorizationGrants;
        this.page = page;
        this.pageSize = pageSize;
    }

    public List<EntityAuthorizationGrant> getAuthorizationGrants() {
        return authorizationGrants;
    }

    public void setAuthorizationGrants(List<EntityAuthorizationGrant> authorizationGrants) {
        this.authorizationGrants = authorizationGrants;
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
