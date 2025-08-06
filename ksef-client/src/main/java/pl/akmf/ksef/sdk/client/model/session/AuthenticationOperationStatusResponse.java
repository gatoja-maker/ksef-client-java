package pl.akmf.ksef.sdk.client.model.session;


import pl.akmf.ksef.sdk.client.model.StatusInfo;

import java.time.OffsetDateTime;


/**
 * AuthenticationOperationStatusResponse
 */
public class AuthenticationOperationStatusResponse {
    private String referenceNumber;

    private OffsetDateTime startDate;

    private AuthenticationMethod authenticationMethod;

    private StatusInfo status;

    private Boolean isTokenRedeemed;

    private Boolean isCurrent;

    private OffsetDateTime lastTokenRefreshDate;

    private OffsetDateTime refreshTokenValidUntil;

    public AuthenticationOperationStatusResponse() {
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }

    public AuthenticationMethod getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(AuthenticationMethod authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public StatusInfo getStatus() {
        return status;
    }

    public void setStatus(StatusInfo status) {
        this.status = status;
    }

    public Boolean getTokenRedeemed() {
        return isTokenRedeemed;
    }

    public void setTokenRedeemed(Boolean tokenRedeemed) {
        isTokenRedeemed = tokenRedeemed;
    }

    public OffsetDateTime getLastTokenRefreshDate() {
        return lastTokenRefreshDate;
    }

    public void setLastTokenRefreshDate(OffsetDateTime lastTokenRefreshDate) {
        this.lastTokenRefreshDate = lastTokenRefreshDate;
    }

    public OffsetDateTime getRefreshTokenValidUntil() {
        return refreshTokenValidUntil;
    }

    public void setRefreshTokenValidUntil(OffsetDateTime refreshTokenValidUntil) {
        this.refreshTokenValidUntil = refreshTokenValidUntil;
    }
}

