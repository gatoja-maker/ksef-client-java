package pl.akmf.ksef.sdk.api.services;

import org.apache.commons.collections4.CollectionUtils;
import pl.akmf.ksef.sdk.api.ActiveSessionApi;
import pl.akmf.ksef.sdk.api.AuthenticationApi;
import pl.akmf.ksef.sdk.api.BatchInvoiceApi;
import pl.akmf.ksef.sdk.api.CertificateApi;
import pl.akmf.ksef.sdk.api.DownloadInvoiceApi;
import pl.akmf.ksef.sdk.api.EuSubjectAdministratorApi;
import pl.akmf.ksef.sdk.api.EuSubjectRepresentationApi;
import pl.akmf.ksef.sdk.api.ForAuthorizedSubjectApi;
import pl.akmf.ksef.sdk.api.GrantDirectlyApi;
import pl.akmf.ksef.sdk.api.InteractiveSessionApi;
import pl.akmf.ksef.sdk.api.NaturalPersonKseFApi;
import pl.akmf.ksef.sdk.api.OperationApi;
import pl.akmf.ksef.sdk.api.PublicKeyCertificateApi;
import pl.akmf.ksef.sdk.api.PublicKeyEnvironmentApi;
import pl.akmf.ksef.sdk.api.SearchPermissionApi;
import pl.akmf.ksef.sdk.api.SendStatusAndUpoApi;
import pl.akmf.ksef.sdk.api.SubUnitSubjectAdministratorApi;
import pl.akmf.ksef.sdk.api.SubjectForInvoiceApi;
import pl.akmf.ksef.sdk.api.TokensApi;
import pl.akmf.ksef.sdk.client.interfaces.KSeFClient;
import pl.akmf.ksef.sdk.client.model.ApiClient;
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
import pl.akmf.ksef.sdk.client.model.certificate.publickey.PublicKeyCertificate;
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
import pl.akmf.ksef.sdk.client.model.session.AuthenticationListResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoice;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoicesResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionStatusResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionsQueryRequest;
import pl.akmf.ksef.sdk.client.model.session.SessionsQueryResponse;
import pl.akmf.ksef.sdk.client.model.session.batch.BatchPartSendingInfo;
import pl.akmf.ksef.sdk.client.model.session.batch.OpenBatchSessionRequest;
import pl.akmf.ksef.sdk.client.model.session.batch.OpenBatchSessionResponse;
import pl.akmf.ksef.sdk.client.model.session.batch.PackagePartSignatureInitResponseType;
import pl.akmf.ksef.sdk.client.model.session.online.OpenOnlineSessionRequest;
import pl.akmf.ksef.sdk.client.model.session.online.OpenOnlineSessionResponse;
import pl.akmf.ksef.sdk.client.model.session.online.SendInvoiceRequest;
import pl.akmf.ksef.sdk.client.model.session.online.SendInvoiceResponse;
import pl.akmf.ksef.sdk.system.KsefEnviroments;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultKsefClient implements KSeFClient {
    private final ApiClient apiClient;
    private AuthenticationApi authenticationApi;
    private InteractiveSessionApi interactiveSessionApi;
    private CertificateApi certificateApi;
    private SearchPermissionApi searchPermissionApi;
    private ForAuthorizedSubjectApi forAuthorizedSubjectApi;
    private GrantDirectlyApi grantDirectlyApi;
    private DownloadInvoiceApi downloadInvoiceApi;
    private SubjectForInvoiceApi subjectForInvoiceApi;
    private EuSubjectRepresentationApi euSubjectRepresentationApi;
    private EuSubjectAdministratorApi euSubjectAdministratorApi;
    private SubUnitSubjectAdministratorApi subUnitSubjectAdministratorApi;
    private SendStatusAndUpoApi sendStatusAndUpoApi;
    private BatchInvoiceApi batchInvoiceApi;
    private NaturalPersonKseFApi naturalPersonKseFApi;
    private OperationApi operationApi;
    private PublicKeyEnvironmentApi publicKeyEnvironmentApi;
    private TokensApi tokensApi;
    private PublicKeyCertificateApi publicKeyCertificateApi;
    private ActiveSessionApi activeSessionApi;

    public DefaultKsefClient(KsefEnviroments ksefEnviroments) {
        this.apiClient = createApiClient(ksefEnviroments);
        initApiClient();
    }

    @Override
    public PermissionsOperationResponse grantsPermissionsProxyEntity(GrantProxyEntityPermissionsRequest body) throws ApiException {

        return forAuthorizedSubjectApi.apiV2PermissionsAuthorizationsGrantsPost(body).getData();
    }

    @Override
    public PermissionsOperationResponse grantsPermissionIndirectEntity(GrantIndirectEntityPermissionsRequest body) throws ApiException {
        return grantDirectlyApi.apiV2PermissionsIndirectGrantsPost(body).getData();
    }

    @Override
    public OpenBatchSessionResponse openBatchSession(OpenBatchSessionRequest body) throws ApiException {

        return batchInvoiceApi.batchOpenWithHttpInfo(body).getData();
    }

    @Override
    public void closeBatchSession(String referenceNumber) throws ApiException {
        batchInvoiceApi.apiV2SessionsBatchReferenceNumberClosePostWithHttpInfo(referenceNumber);
    }

    @Override
    public void sendBatchParts(OpenBatchSessionResponse openBatchSessionResponse, List<BatchPartSendingInfo> parts) throws IOException, InterruptedException {
        if (CollectionUtils.isEmpty(parts)) {
            throw new IllegalArgumentException("No files to send.");
        }

        List<PackagePartSignatureInitResponseType> responsePartUploadRequests = openBatchSessionResponse.getPartUploadRequests();
        if (CollectionUtils.isEmpty(responsePartUploadRequests)) {
            throw new IllegalStateException("No information about parts to send.");
        }

        HttpClient client = HttpClient.newHttpClient();
        List<String> errors = new ArrayList<>();

        for (PackagePartSignatureInitResponseType responsePart : responsePartUploadRequests) {
            byte[] fileBytes = parts.stream()
                    .filter(p -> responsePart.getOrdinalNumber() == p.getOrdinalNumber())
                    .findFirst()
                    .orElseThrow()
                    .getData();

            HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofByteArray(fileBytes);

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(responsePart.getUrl())
                    .PUT(bodyPublisher);
            requestBuilder.header("Content-Type", "application/octet-stream");

            Map<String, String> headerEntryList = responsePart.getHeaders();
            if (headerEntryList != null) {
                headerEntryList.forEach(requestBuilder::header);
            }

            HttpResponse<String> responseResult = client.send(requestBuilder.build(), HttpResponse.BodyHandlers.ofString());

            if (responseResult.statusCode() >= 400) {
                errors.add("Error sends part " + responsePart.getOrdinalNumber() + ": " +
                        responseResult.statusCode() + " " + responseResult.body());
            }
        }

        if (!errors.isEmpty()) {
            throw new IOException("Errors when sending parts:\n" + String.join("\n", errors));
        }
    }

    @Override
    public OpenOnlineSessionResponse openOnlineSession(OpenOnlineSessionRequest body) throws ApiException {
        return interactiveSessionApi.onlineSessionOpenWithHttpInfo(body).getData();
    }

    @Override
    public void closeOnlineSession(String referenceNumber) throws ApiException {
        interactiveSessionApi.apiV2SessionsOnlineReferenceNumberClosePostWithHttpInfo(referenceNumber);
    }

    @Override
    public SendInvoiceResponse onlineSessionSendInvoice(String referenceNumber, SendInvoiceRequest body) throws ApiException {
        return interactiveSessionApi.apiV2SessionsOnlineReferenceNumberInvoicesPostWithHttpInfo(referenceNumber, body).getData();
    }

    @Override
    public CertificateLimitsResponse getCertificateLimits() throws ApiException {
        return certificateApi.apiV2CertificatesLimitsGetWithHttpInfo().getData();
    }

    @Override
    public CertificateEnrollmentsInfoResponse getCertificateEnrollmentInfo() throws ApiException {
        return certificateApi.apiV2CertificatesEnrollmentsDataGetWithHttpInfo().getData();
    }

    @Override
    public CertificateEnrollmentResponse sendCertificateEnrollment(SendCertificateEnrollmentRequest body) throws ApiException {
        return certificateApi.apiV2CertificatesEnrollmentsPostWithHttpInfo(body).getData();
    }

    @Override
    public CertificateEnrollmentStatusResponse getCertificateEnrollmentStatus(String referenceNumber) throws ApiException {
        return certificateApi.apiV2CertificatesEnrollmentsReferenceNumberGetWithHttpInfo(referenceNumber).getData();
    }

    @Override
    public CertificateListResponse getCertificateList(CertificateListRequest body) throws ApiException {
        return certificateApi.apiV2CertificatesRetrievePostWithHttpInfo(body).getData();
    }

    @Override
    public void revokeCertificate(CertificateRevokeRequest body, String serialNumber) throws ApiException {
        certificateApi.apiV2CertificatesCertificateSerialNumberRevokePostWithHttpInfo(serialNumber, body);
    }

    @Override
    public CertificateMetadataListResponse getCertificateMetadataList(CertificateMetadataListRequest body, int pageSize, int pageOffset) throws ApiException {
        return certificateApi.apiV2CertificatesQueryPostWithHttpInfo(pageSize, pageOffset, body).getData();
    }

    @Override
    public AuthenticationChallengeResponse getAuthChallenge() throws ApiException {
        return authenticationApi.apiV2AuthChallengePostWithHttpInfo().getData();
    }

    @Override
    public AuthenticationInitResponse submitAuthTokenRequest(String signedXml, boolean verifyCertificateChain) throws ApiException {
        var initResponse = authenticationApi.apiV2AuthTokenSignaturePostWithHttpInfo(signedXml, verifyCertificateChain).getData();

        if (initResponse.getAuthenticationToken() == null) {
            throw new ApiException();
        }

        setNewAccessToken(initResponse.getAuthenticationToken().getToken());
        initApiClient();

        return initResponse;
    }

    @Override
    public AuthenticationInitResponse authorizeByKSeFToken(AuthKsefTokenRequest body) throws ApiException {
        var initResponse = authenticationApi.apiV2AuthTokeKSeFPostWithHttpInfo(body).getData();

        if (initResponse.getAuthenticationToken() == null) {
            throw new ApiException();
        }

        setNewAccessToken(initResponse.getAuthenticationToken().getToken());
        initApiClient();

        return initResponse;
    }

    @Override
    public pl.akmf.ksef.sdk.client.model.session.AuthenticationOperationStatusResponse getAuthStatus(String referenceNumber) throws ApiException {

        return authenticationApi.apiV2AuthTokenTokenReferenceNumberGetWithHttpInfo(referenceNumber).getData();
    }

    @Override
    public AuthenticationOperationStatusResponse redeemToken() throws ApiException {
        var authenticationOperationStatusResponse = authenticationApi.apiV2AuthRedeemTokenPost().getData();


        setNewAccessToken(authenticationOperationStatusResponse.getAccessToken().getToken());
        initApiClient();

        return authenticationOperationStatusResponse;
    }

    @Override
    public AuthenticationTokenRefreshResponse refreshAccessToken(String refreshToken) throws ApiException {
        setNewAccessToken(refreshToken);

        initApiClient();
        var refreshTokenResponse = authenticationApi.apiV2AuthTokenRefreshPostWithHttpInfo().getData();

        if (refreshTokenResponse.getAccessToken() == null) {
            throw new ApiException();

        }

        setNewAccessToken(refreshTokenResponse.getAccessToken().getToken());
        initApiClient();

        return refreshTokenResponse;
    }

    @Override
    public void revokeAccessToken() throws ApiException {
        authenticationApi.apiV2AuthTokenDeleteWithHttpInfo();
    }

    @Override
    public PermissionStatusInfo permissionOperationStatus(String referenceNumber) throws ApiException {
        return operationApi.apiV2PermissionsOperationsReferenceNumberGet(referenceNumber).getData();
    }

    //----------------- Search Permission -------------------
    @Override
    public QueryPersonPermissionsResponse searchGrantedPersonPermissions(PersonPermissionsQueryRequest request, int pageOffset, int pageSize) throws ApiException {
        return searchPermissionApi.apiV2PermissionsQueryPersonsGrantsPost(pageOffset, pageSize, request).getData();
    }

    @Override
    public QuerySubunitPermissionsResponse searchSubunitAdminPermissions(SubunitPermissionsQueryRequest request, int pageOffset, int pageSize) throws ApiException {
        return searchPermissionApi.apiV2PermissionsQuerySubunitsGrantsPost(pageOffset, pageSize, request).getData();
    }

    @Override
    public QueryEntityRolesResponse searchEntityInvoiceRoles(int pageOffset, int pageSize) throws ApiException {
        return searchPermissionApi.apiV2PermissionsQueryEntitiesRolesGet(pageOffset, pageSize).getData();
    }

    @Override
    public QuerySubordinateEntityRolesResponse searchSubordinateEntityInvoiceRoles(SubordinateEntityRolesQueryRequest request, int pageOffset, int pageSize) throws ApiException {
        return searchPermissionApi.apiV2PermissionsQuerySubordinateEntitiesRolesPost(pageOffset, pageSize, request).getData();
    }

    @Override
    public QueryEntityAuthorizationPermissionsResponse searchEntityAuthorizationGrants(EntityAuthorizationPermissionsQueryRequest request, int pageOffset, int pageSize) throws ApiException {
        return searchPermissionApi.apiV2PermissionsQueryAuthorizationsGrantsPost(pageOffset, pageSize, request).getData();
    }

    @Override
    public QueryEuEntityPermissionsResponse searchGrantedEuEntityPermissions(EuEntityPermissionsQueryRequest request, int pageOffset, int pageSize) throws ApiException {
        return searchPermissionApi.apiV2PermissionsQueryEuEntitiesGrantsPost(pageOffset, pageSize, request).getData();
    }

    //------------------ END Search Permission------------------

    @Override
    public PermissionsOperationResponse grantsPermissionEUEntity(GrantEUEntityPermissionsRequest body) throws ApiException {
        return euSubjectAdministratorApi.apiV2PermissionsEuEntitiesAdministrationGrantsPost(body).getData();
    }

    @Override
    public PermissionsOperationResponse grantsPermissionEUEntityRepresentative(GrantEUEntityRepresentativePermissionsRequest body) throws ApiException {
        return euSubjectRepresentationApi.apiV2PermissionsEuEntitiesGrantsPost(body).getData();
    }

    @Override
    public String getInvoice(String ksefReferenceNumber) throws ApiException {
        return downloadInvoiceApi.apiV2InvoicesKsefKsefReferenceNumberGet(ksefReferenceNumber).getData();
    }

    public String getInvoice(DownloadInvoiceRequest request) throws ApiException {
        return downloadInvoiceApi.apiV2InvoicesDownloadPost(request).getData();
    }

    public QueryInvoicesReponse queryInvoices(Integer pageOffset, Integer pageSize, InvoicesQueryRequest request) throws ApiException {
        return downloadInvoiceApi.apiV2InvoicesQueryPost(pageOffset, pageSize, request).getData();
    }

    public InitAsyncInvoicesQueryResponse initAsyncQueryInvoice(InvoicesAsynqQueryRequest request) throws ApiException {
        return downloadInvoiceApi.apiV2InvoicesAsyncQueryPost(request).getData();
    }

    public AsyncInvoicesQueryStatus checkStatusAsyncQueryInvoice(String operationReferenceNumber) throws ApiException {
        return downloadInvoiceApi.apiV2InvoicesAsyncQueryOperationReferenceNumberGet(operationReferenceNumber).getData();
    }

    @Override
    public PermissionsOperationResponse grantsPermissionEntity(GrantEntityPermissionsRequest body) throws ApiException {
        return subjectForInvoiceApi.apiV2PermissionsEntitiesGrantsPost(body).getData();
    }

    //------------------ Start Session------------------

    @Override
    public SessionStatusResponse getSessionStatus(String referenceNumber) throws ApiException {
        return sendStatusAndUpoApi.apiV2SessionsReferenceNumberGet(referenceNumber).getData();
    }

    @Override
    public SessionInvoice getSessionInvoiceStatus(String referenceNumber, String invoiceReferenceNumber) throws ApiException {
        return sendStatusAndUpoApi.apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberGet(referenceNumber, invoiceReferenceNumber).getData();
    }

    @Override
    public byte[] getSessionInvoiceUpoByReferenceNumber(String referenceNumber, String invoiceReferenceNumber) throws ApiException {
        return sendStatusAndUpoApi.apiV2SessionsReferenceNumberInvoicesInvoiceReferenceNumberUpoGet(referenceNumber, invoiceReferenceNumber).getData();
    }

    @Override
    public byte[] getSessionInvoiceUpoByKsefNumber(String referenceNumber, String ksefNumber) throws ApiException {
        return sendStatusAndUpoApi.apiV2SessionsReferenceNumberInvoicesKsefKsefNumberUpoGet(referenceNumber, ksefNumber).getData();
    }

    @Override
    public byte[] getSessionUpo(String referenceNumber, String upoReferenceNumber) throws ApiException {
        return sendStatusAndUpoApi.apiV2SessionsReferenceNumberUpoUpoReferenceNumberGet(referenceNumber, upoReferenceNumber).getData();
    }

    @Override
    public SessionInvoicesResponse getSessionInvoices(String referenceNumber, Integer pageSize, Integer pageOffset) throws ApiException {
        return sendStatusAndUpoApi.apiV2SessionsReferenceNumberInvoicesGet(referenceNumber, pageOffset, pageSize).getData();
    }

    @Override
    public SessionInvoicesResponse getSessionFailedInvoices(String referenceNumber, String continuationToken, Integer pageSize) throws ApiException {
        return sendStatusAndUpoApi.apiV2SessionsReferenceNumberInvoicesFailedGet(referenceNumber, continuationToken, pageSize).getData();
    }

    @Override
    public SessionsQueryResponse getSessions(SessionsQueryRequest request, Integer pageSize, String continuationToken) throws ApiException {
        return sendStatusAndUpoApi.apiV2SessionsGet(request, pageSize, continuationToken).getData();
    }

    @Override
    public AuthenticationListResponse getActiveSessions(Integer pageSize, String continuationToken) throws ApiException {
        return activeSessionApi.apiV2AuthSessionsGet(pageSize, continuationToken).getData();
    }

    @Override
    public void revokeCurrentSession() throws ApiException {
        activeSessionApi.apiV2AuthSessionsCurrentDelete();
    }

    @Override
    public void revokeSession(String referenceNumber) throws ApiException {
        activeSessionApi.apiV2AuthSessionsReferenceNumberDelete(referenceNumber);
    }

    //------------------ END Session------------------

    //------------------ START Person permissions ------------------

    @Override
    public PermissionsOperationResponse grantsPermissionPerson(GrantPersonPermissionsRequest request) throws ApiException {
        return naturalPersonKseFApi.apiV2PermissionsPersonsGrantsPost(request).getData();
    }

    //------------------ END Person permissions ------------------

    @Override
    public PermissionsOperationResponse grantsPermissionSubUnit(GrantSubUnitPermissionsRequest body) throws ApiException {
        return subUnitSubjectAdministratorApi.apiV2PermissionsSubunitsGrantsPost(body).getData();
    }

    @Override
    public PermissionsOperationResponse revokeCommonPermission(String permissionId) throws ApiException {
        return operationApi.apiV2PermissionsCommonGrantsPermissionIdDelete(permissionId).getData();
    }

    @Override
    public PermissionsOperationResponse revokeAuthorizationsPermission(String permissionId) throws ApiException {
        return operationApi.apiV2PermissionsAuthorizationsGrantsPermissionIdDelete(permissionId).getData();
    }

    @Override
    public byte[] getPublicKey() throws ApiException {
        return publicKeyEnvironmentApi.apiV2SPublicKeyGet();
    }

    //------------------ START Tokens ------------------

    @Override
    public GenerateTokenResponse generateKsefToken(GenerateTokenRequest tokenRequest) throws ApiException {
        return tokensApi.apiV2TokensPost(tokenRequest).getData();
    }

    @Override
    public QueryTokensResponse queryKsefTokens(List<AuthenticationTokenStatus> statuses, String continuationToken, Integer pageSize) throws ApiException {

        return tokensApi.apiV2TokensGet(statuses, continuationToken, pageSize).getData();
    }

    @Override
    public AuthenticationToken getKsefToken(String referenceNumber) throws ApiException {

        return tokensApi.apiV2TokensReferenceNumberGet(referenceNumber).getData();
    }

    @Override
    public void revokeKsefToken(String referenceNumber) throws ApiException {

        tokensApi.apiV2TokensReferenceNumberDelete(referenceNumber);
    }

    @Override
    public List<PublicKeyCertificate> retrievePublicKeyCertificate() throws ApiException {

        return publicKeyCertificateApi.apiV2SecurityPublicKeyCertificatesGet().getData();
    }

    //------------------ END Tokens ------------------


    private static ApiClient createApiClient(KsefEnviroments ksefEnviroments) {
        ApiClient apiClient = new ApiClient();
        apiClient.setScheme("https");
        apiClient.setHost(ksefEnviroments.getUrl());
        apiClient.setConnectTimeout(Duration.ofSeconds(10));
        apiClient.setReadTimeout(Duration.ofSeconds(10));
        return apiClient;
    }

    private void initApiClient() {
        this.authenticationApi = new AuthenticationApi(apiClient);
        this.interactiveSessionApi = new InteractiveSessionApi(apiClient);
        this.certificateApi = new CertificateApi(apiClient);
        this.searchPermissionApi = new SearchPermissionApi(apiClient);
        this.forAuthorizedSubjectApi = new ForAuthorizedSubjectApi(apiClient);
        this.grantDirectlyApi = new GrantDirectlyApi(apiClient);
        this.downloadInvoiceApi = new DownloadInvoiceApi(apiClient);
        this.subjectForInvoiceApi = new SubjectForInvoiceApi(apiClient);
        this.euSubjectRepresentationApi = new EuSubjectRepresentationApi(apiClient);
        this.euSubjectAdministratorApi = new EuSubjectAdministratorApi(apiClient);
        this.subUnitSubjectAdministratorApi = new SubUnitSubjectAdministratorApi(apiClient);
        this.sendStatusAndUpoApi = new SendStatusAndUpoApi(apiClient);
        this.batchInvoiceApi = new BatchInvoiceApi(apiClient);
        this.naturalPersonKseFApi = new NaturalPersonKseFApi(apiClient);
        this.operationApi = new OperationApi(apiClient);
        this.publicKeyEnvironmentApi = new PublicKeyEnvironmentApi(apiClient);
        this.tokensApi = new TokensApi(apiClient);
        this.publicKeyCertificateApi = new PublicKeyCertificateApi(apiClient);
        this.activeSessionApi = new ActiveSessionApi(apiClient);
    }

    private void setNewAccessToken(String accessToken) {
        apiClient.setRequestInterceptor(builder -> builder.header("Authorization", "Bearer " + accessToken));
    }
}
