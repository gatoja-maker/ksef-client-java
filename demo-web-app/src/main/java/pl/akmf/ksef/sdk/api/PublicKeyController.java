package pl.akmf.ksef.sdk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.client.model.ApiException;

@RestController
@RequiredArgsConstructor
public class PublicKeyController {
    private final DefaultKsefClient ksefClient;

    @GetMapping("/public-keys")
    public byte[] getPublicKey() throws ApiException {
        return ksefClient.getPublicKey();
    }
}
