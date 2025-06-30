package pl.akmf.ksef.sdk.api.builders.auth;

import pl.akmf.ksef.sdk.client.model.xml.AuthTokenRequest;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.client.model.xml.IpChangePolicyEnum;
import pl.akmf.ksef.sdk.client.model.xml.SubjectIdentifierTypeEnum;

import java.util.List;

public class AuthTokenRequestBuilder {

    private String challenge;
    private boolean challengeSet = false;
    private AuthTokenRequest.ContextIdentifier context;
    private boolean contextSet = false;
    private SubjectIdentifierTypeEnum subjectIdentifierTypeEnum;
    private AuthTokenRequest.IpAddressPolicy ipPolicy;

    /**
     * Mandatory: sets the challenge token to sign.
     * Must be called before withContext().
     *
     * @param challenge The challenge token to sign
     * @return This builder instance for method chaining
     * @throws IllegalArgumentException if challenge is null or empty
     */
    public AuthTokenRequestBuilder withChallenge(String challenge) {
        if (challenge == null || challenge.trim().isEmpty()) {
            throw new IllegalArgumentException("Challenge cannot be null or empty.");
        }

        this.challenge = challenge;
        this.challengeSet = true;
        return this;
    }

    /**
     * Mandatory: sets the context (and subject).
     * Must be called after withChallenge().
     *
     * @param type  The context identifier type
     * @param value The context value
     * @return This builder instance for method chaining
     * @throws IllegalStateException    if withChallenge() was not called first
     * @throws IllegalArgumentException if value is null or empty
     */
    public AuthTokenRequestBuilder withContext(ContextIdentifierTypeEnum type, String value) {
        if (!challengeSet) {
            throw new IllegalStateException("You must call withChallenge() before withContext().");
        }

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Context value cannot be null or empty.");
        }

        this.context = new AuthTokenRequest.ContextIdentifier();
        this.context.setType(type);
        this.context.setValue(value);
        this.contextSet = true;
        return this;
    }

    public AuthTokenRequestBuilder withSubjectType(SubjectIdentifierTypeEnum value) {
        if (!challengeSet) {
            throw new IllegalStateException("You must call withChallenge() before withSubject().");
        }

        this.subjectIdentifierTypeEnum = value;
        return this;
    }


    /**
     * Optional: configure IP-address policy.
     *
     * @param ipPolicyChanges The IP address policy change enum
     * @param ipAddress       The IP address policy address list enum
     * @param ipRange         The IP address policy range list
     * @param ipMask          The IP address policy mask list
     * @return This builder instance for method chaining
     * @throws IllegalArgumentException if ipPolicy is null
     */
    public AuthTokenRequestBuilder withIpAddressPolicy(String ipPolicyChanges,
                                                       List<String> ipAddress,
                                                       List<String> ipRange,
                                                       List<String> ipMask) {
        AuthTokenRequest.IpAddressPolicy.AllowedIps allowedIps = new AuthTokenRequest.IpAddressPolicy.AllowedIps();
        allowedIps.withIpAddress(ipAddress);
        allowedIps.withIpRange(ipRange);
        allowedIps.withIpMask(ipMask);

        this.ipPolicy = new AuthTokenRequest.IpAddressPolicy();
        this.ipPolicy.setOnClientIpChange(IpChangePolicyEnum.fromValue(ipPolicyChanges));
        this.ipPolicy.setAllowedIps(allowedIps);
        return this;
    }

    /**
     * Builds the AuthTokenRequest.
     * Throws if mandatory steps were skipped.
     *
     * @return The constructed AuthTokenRequest
     * @throws IllegalStateException if mandatory steps were not completed
     */
    public AuthTokenRequest build() {
        if (!challengeSet) {
            throw new IllegalStateException("Challenge has not been set. Call withChallenge() first.");
        }
        if (!contextSet) {
            throw new IllegalStateException("Context has not been set. Call withContext() after withChallenge().");
        }

        AuthTokenRequest request = new AuthTokenRequest();
        request.setChallenge(challenge);
        request.setContextIdentifier(context);
        request.setSubjectIdentifierType(subjectIdentifierTypeEnum);
        request.setIpAddressPolicy(ipPolicy);
        return request;
    }
}
