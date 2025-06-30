package pl.akmf.ksef.sdk.client.model.auth;

import java.time.OffsetDateTime;

public class AuthenticationChallengeResponse {
    private String challenge;
    private OffsetDateTime timestamp;

    public AuthenticationChallengeResponse() {

    }

    public AuthenticationChallengeResponse(String challenge, OffsetDateTime timestamp) {
        this.challenge = challenge;
        this.timestamp = timestamp;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

