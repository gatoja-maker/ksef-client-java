package pl.akmf.ksef.sdk.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.session.AuthenticationListResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionsQueryRequest;
import pl.akmf.ksef.sdk.client.model.session.SessionsQueryResponse;

@RestController
public class ActiveSessionController {

    DefaultKsefClient ksefClient;

    @PostMapping("/session/query/{pageSize}")
    public SessionsQueryResponse getSessions(@RequestBody SessionsQueryRequest request, @PathVariable Integer pageSize) throws ApiException {
        return ksefClient.getSessions(request, pageSize, null);
    }

    @PostMapping("/session/active/{pageSize}")
    public AuthenticationListResponse getActiveSessions(@PathVariable Integer pageSize) throws ApiException {
        return ksefClient.getActiveSessions(pageSize, null);
    }

    @DeleteMapping("/session/revoke/current")
    public void revokeCurrentSession() throws ApiException {
        ksefClient.revokeCurrentSession();
    }

    @DeleteMapping("/session/revoke/{sessionReferenceNumber}")
    public void revokeSession(@PathVariable String sessionReferenceNumber) throws ApiException {
        ksefClient.revokeSession(sessionReferenceNumber);
    }
}
