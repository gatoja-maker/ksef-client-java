package pl.akmf.ksef.sdk.client.interfaces;

import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.auth.AuthKsefTokenRequest;
import pl.akmf.ksef.sdk.client.model.auth.AuthStatus;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationChallengeResponse;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationInitResponse;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationOperationStatusResponse;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationToken;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationTokenRefreshResponse;
import pl.akmf.ksef.sdk.client.model.auth.AuthenticationTokenStatus;
import pl.akmf.ksef.sdk.client.model.auth.GenerateTokenRequest;
import pl.akmf.ksef.sdk.client.model.auth.GenerateTokenResponse;
import pl.akmf.ksef.sdk.client.model.auth.QueryTokensResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentStatusResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentsInfoResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateLimitsResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateListRequest;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateListResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateMetadataListRequest;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateMetadataListResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateRevokeRequest;
import pl.akmf.ksef.sdk.client.model.certificate.SendCertificateEnrollmentRequest;
import pl.akmf.ksef.sdk.client.model.invoice.AsyncInvoicesQueryStatus;
import pl.akmf.ksef.sdk.client.model.invoice.DownloadInvoiceRequest;
import pl.akmf.ksef.sdk.client.model.invoice.InitAsyncInvoicesQueryResponse;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequest;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesQueryRequest;
import pl.akmf.ksef.sdk.client.model.invoice.QueryInvoicesReponse;
import pl.akmf.ksef.sdk.client.model.permission.PermissionStatusInfo;
import pl.akmf.ksef.sdk.client.model.permission.PermissionsOperationResponse;
import pl.akmf.ksef.sdk.client.model.permission.entity.GrantEntityPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.euentity.GrantEUEntityPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.euentity.GrantEUEntityRepresentativePermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.indirect.GrantIndirectEntityPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.person.GrantPersonPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.proxy.GrantProxyEntityPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.permission.search.EntityAuthorizationPermissionsQueryRequest;
import pl.akmf.ksef.sdk.client.model.permission.search.EuEntityPermissionsQueryRequest;
import pl.akmf.ksef.sdk.client.model.permission.search.PersonPermissionsQueryRequest;
import pl.akmf.ksef.sdk.client.model.permission.search.QueryEntityAuthorizationPermissionsResponse;
import pl.akmf.ksef.sdk.client.model.permission.search.QueryEntityRolesResponse;
import pl.akmf.ksef.sdk.client.model.permission.search.QueryEuEntityPermissionsResponse;
import pl.akmf.ksef.sdk.client.model.permission.search.QueryPersonPermissionsResponse;
import pl.akmf.ksef.sdk.client.model.permission.search.QuerySubordinateEntityRolesResponse;
import pl.akmf.ksef.sdk.client.model.permission.search.QuerySubunitPermissionsResponse;
import pl.akmf.ksef.sdk.client.model.permission.search.SubordinateEntityRolesQueryRequest;
import pl.akmf.ksef.sdk.client.model.permission.search.SubunitPermissionsQueryRequest;
import pl.akmf.ksef.sdk.client.model.permission.subunit.GrantSubUnitPermissionsRequest;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoice;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoicesResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionStatusResponse;
import pl.akmf.ksef.sdk.client.model.session.batch.BatchPartSendingInfo;
import pl.akmf.ksef.sdk.client.model.session.batch.OpenBatchSessionRequest;
import pl.akmf.ksef.sdk.client.model.session.batch.OpenBatchSessionResponse;
import pl.akmf.ksef.sdk.client.model.session.online.OpenOnlineSessionRequest;
import pl.akmf.ksef.sdk.client.model.session.online.OpenOnlineSessionResponse;
import pl.akmf.ksef.sdk.client.model.session.online.SendInvoiceRequest;
import pl.akmf.ksef.sdk.client.model.session.online.SendInvoiceResponse;

import java.io.IOException;
import java.util.List;

public interface KSeFClient {

    OpenBatchSessionResponse batchOpen(OpenBatchSessionRequest body) throws ApiException;

    void closeBatchSession(String referenceNumber) throws ApiException;

    void sendBatchParts(OpenBatchSessionResponse response, List<BatchPartSendingInfo> parts) throws IOException, InterruptedException;

    OpenOnlineSessionResponse openOnlineSession(OpenOnlineSessionRequest body) throws ApiException;

    void closeOnlineSession(String referenceNumber) throws ApiException;

    SendInvoiceResponse onlineSessionSendInvoice(String referenceNumber, SendInvoiceRequest body) throws ApiException;

    CertificateLimitsResponse getCertificateLimits() throws ApiException;

    CertificateEnrollmentsInfoResponse getCertificateEnrollmentInfo() throws ApiException;

    CertificateEnrollmentResponse sendCertificateEnrollment(SendCertificateEnrollmentRequest body) throws ApiException;

    CertificateEnrollmentStatusResponse getCertificateEnrollmentStatus(String referenceNumber) throws ApiException;

    CertificateListResponse getCertificateList(CertificateListRequest body) throws ApiException;

    void revokeCertificate(CertificateRevokeRequest body, String serialNumber) throws ApiException;

    CertificateMetadataListResponse getCertificateMetadataList(CertificateMetadataListRequest body, int pageSize, int pageOffset) throws ApiException;

    AuthenticationChallengeResponse getAuthChallenge() throws ApiException;

    AuthenticationInitResponse submitAuthTokenRequest(String body) throws ApiException;

    AuthenticationInitResponse authorizeByKSeFToken(AuthKsefTokenRequest body) throws ApiException;

    AuthStatus getAuthStatus(String referenceNumber) throws ApiException;

    AuthenticationOperationStatusResponse redeemToken() throws ApiException;

    AuthenticationTokenRefreshResponse refreshAccessToken(String refreshToken) throws ApiException;

    void revokeAccessToken() throws ApiException;

    PermissionsOperationResponse grantsPermissionsProxyEntity(GrantProxyEntityPermissionsRequest body) throws ApiException;

    PermissionsOperationResponse grantsPermissionIndirectEntity(GrantIndirectEntityPermissionsRequest body) throws ApiException;

    PermissionStatusInfo operations(String referenceNumber) throws ApiException;

    QueryPersonPermissionsResponse searchGrantedPersonPermissions(PersonPermissionsQueryRequest request, int pageOffset, int pageSize) throws ApiException;

    QuerySubunitPermissionsResponse searchSubunitAdminPermissions(SubunitPermissionsQueryRequest request, int pageOffset, int pageSize) throws ApiException;

    QueryEntityRolesResponse searchEntityInvoiceRoles(int pageOffset, int pageSize) throws ApiException;

    QuerySubordinateEntityRolesResponse searchSubordinateEntityInvoiceRoles(SubordinateEntityRolesQueryRequest request, int pageOffset, int pageSize) throws ApiException;

    QueryEntityAuthorizationPermissionsResponse searchEntityAuthorizationGrants(EntityAuthorizationPermissionsQueryRequest request, int pageOffset, int pageSize) throws ApiException;

    QueryEuEntityPermissionsResponse searchGrantedEuEntityPermissions(EuEntityPermissionsQueryRequest request, int pageOffset, int pageSize) throws ApiException;

    PermissionsOperationResponse grantsPermissionEUEntity(GrantEUEntityPermissionsRequest body) throws ApiException;

    PermissionsOperationResponse grantsPermissionEUEntityRepresentative(GrantEUEntityRepresentativePermissionsRequest body) throws ApiException;

    SessionStatusResponse getSessionStatus(String referenceNumber) throws ApiException;

    SessionInvoice getSessionInvoiceStatus(String referenceNumber, String invoiceReferenceNumber) throws ApiException;

    byte[] getSessionInvoiceUpoByReferenceNumber(String referenceNumber, String invoiceReferenceNumber) throws ApiException;

    byte[] getSessionInvoiceUpoByKsefNumber(String referenceNumber, String ksefNumber) throws ApiException;

    byte[] getSessionUpo(String referenceNumber, String upoReferenceNumber) throws ApiException;

    SessionInvoicesResponse getSessionInvoices(String referenceNumber, Integer pageSize, Integer pageOffset) throws ApiException;

    SessionInvoicesResponse getSessionFailedInvoices(String referenceNumber, Integer pageSize) throws ApiException;

    PermissionsOperationResponse grantsPermissionPerson(GrantPersonPermissionsRequest request) throws ApiException;

    String getInvoice(String ksefReferenceNumber) throws ApiException;

    String getInvoice(DownloadInvoiceRequest request) throws ApiException;

    QueryInvoicesReponse getInvoiceMetadane(Integer pageOffset, Integer pageSize, InvoicesQueryRequest request) throws ApiException;

    InitAsyncInvoicesQueryResponse initAsyncQueryInvoice(InvoicesAsynqQueryRequest request) throws ApiException;

    AsyncInvoicesQueryStatus checkStatusAsyncQueryInvoice(String operationReferenceNumber) throws ApiException;

    PermissionsOperationResponse grantsPermissionEntity(GrantEntityPermissionsRequest body) throws ApiException;

    // SUB ENTITY
    PermissionsOperationResponse grantsPermissionSubUnit(GrantSubUnitPermissionsRequest body) throws ApiException;

    PermissionsOperationResponse revokeCommonPermission(String permissionId) throws ApiException;

    PermissionsOperationResponse revokeAuthorizationsPermission(String permissionId) throws ApiException;

    byte[] getPublicKey() throws ApiException;

    GenerateTokenResponse generateKsefToken(GenerateTokenRequest tokenRequest) throws ApiException;

    QueryTokensResponse queryKsefTokens(List<AuthenticationTokenStatus> status, String continuationToken, Integer pageSize) throws ApiException;

    AuthenticationToken getKsefToken(String referenceNumber) throws ApiException;

    void revokeKsefToken(String referenceNumber) throws ApiException;
}