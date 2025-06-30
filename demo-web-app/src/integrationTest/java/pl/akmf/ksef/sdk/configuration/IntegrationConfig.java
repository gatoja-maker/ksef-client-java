package pl.akmf.ksef.sdk.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class IntegrationConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    WireMockServer wireMockServer(@Value("${server.port}") int wireMockServerPort) {
        return new WireMockServer(wireMockServerPort);
    }
}
