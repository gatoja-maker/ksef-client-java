package pl.akmf.ksef.sdk.client.model.auth;

import java.time.Instant;

public class AuthenticationChallengeResponse {
    private String challenge;
    private Instant timestamp;

    public AuthenticationChallengeResponse() {

    }

    public AuthenticationChallengeResponse(String challenge, Instant timestamp) {
        this.challenge = challenge;
        this.timestamp = timestamp;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

