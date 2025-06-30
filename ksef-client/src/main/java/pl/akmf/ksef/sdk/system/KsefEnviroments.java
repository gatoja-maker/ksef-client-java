package pl.akmf.ksef.sdk.system;

import java.util.Arrays;

public enum KsefEnviroments {
    TEST("ksef-test.mf.gov.pl");

    private final String url;

    KsefEnviroments(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public static KsefEnviroments fromProfileName(String profileName) {
        return Arrays.stream(values())
                .filter(env -> env.name()
                        .equalsIgnoreCase(profileName))
                .findFirst()
                .orElse(KsefEnviroments.TEST);
    }
}
