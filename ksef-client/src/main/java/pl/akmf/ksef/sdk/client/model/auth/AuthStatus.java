package pl.akmf.ksef.sdk.client.model.auth;

import pl.akmf.ksef.sdk.client.model.BaseStatusInfo;

public class AuthStatus {
    private BaseStatusInfo status;

    public AuthStatus() {

    }

    public AuthStatus(BaseStatusInfo status) {
        this.status = status;
    }

    public BaseStatusInfo getStatus() {
        return status;
    }

    public void setStatus(BaseStatusInfo status) {
        this.status = status;
    }
}
