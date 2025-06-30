package pl.akmf.ksef.sdk;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.system.KsefEnviroments;

@Configuration
@RequiredArgsConstructor
public class KsefClientConfig {

    private final Environment env;

    @Bean
    public DefaultKsefClient init() {
        String[] activeProfiles = env.getActiveProfiles();
        String activeProfile = activeProfiles.length > 0 ? activeProfiles[0] : "";
        return new DefaultKsefClient(KsefEnviroments.fromProfileName(activeProfile));
    }
}
