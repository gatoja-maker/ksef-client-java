package pl.akmf.ksef.sdk.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.api.builders.session.OpenOnlineSessionRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.session.SendInvoiceRequestBuilder;
import pl.akmf.ksef.sdk.api.services.DefaultCryptographyService;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.session.FormCode;
import pl.akmf.ksef.sdk.client.model.session.online.OpenOnlineSessionResponse;
import pl.akmf.ksef.sdk.client.model.session.online.SendInvoiceResponse;
import pl.akmf.ksef.sdk.client.model.session.EncryptionData;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class OnlineSessionController {
    private final DefaultKsefClient ksefClient;
    private EncryptionData encryptionData;

    /**
     * Otwarcie sesji interaktywnej
     *
     * @return OpenOnlineSessionResponse
     * @throws ApiException if fails to make API call
     */
    @PostMapping(value = "/open-session")
    public OpenOnlineSessionResponse initSession() throws Exception {
        //przygotowanie serwisu kryptograficznego
        var cryptographyService = new DefaultCryptographyService(ksefClient);
        encryptionData = cryptographyService.getEncryptionData();

        //stworzenie zapytania
        var request = new OpenOnlineSessionRequestBuilder()
                .withFormCode(new FormCode("FA (2)","1-0E","FA"))
                .withEncryptionInfo(encryptionData.encryptionInfo())
                .build();

        //otwarcie sesji interaktywnej
        return ksefClient.openOnlineSession(request);
    }

    /**
     * Wysłanie faktury
     *
     * @param referenceNumber numer referencyjny otwartej sesji
     * @return OpenOnlineSessionResponse
     * @throws ApiException if fails to make API call
     */
    @PostMapping(value = "/send-invoice/{referenceNumber}/{contextIdentifier}")
    public SendInvoiceResponse sendInvoiceOnlineSessionAsync(@PathVariable String referenceNumber, @PathVariable String contextIdentifier) throws Exception {
        //init cryptography service
        var cryptographyService = new DefaultCryptographyService(ksefClient);

        //read example invoice
        String invoicePath = "demo-web-app/src/main/resources/xml/invoices/sample/invoice-template.xml";
        String invoiceTemplate = Files.readString(Paths.get(invoicePath), StandardCharsets.UTF_8)
                .replace("#nip#", contextIdentifier)
                .replace("#invoice_number#", UUID.randomUUID().toString());
        var invoice = invoiceTemplate.getBytes(StandardCharsets.UTF_8);

        //encrypt invoice
        var encryptedInvoice = cryptographyService.encryptBytesWithAES256(invoice,
                encryptionData.cipherKey(),
                encryptionData.cipherIv());

        var invoiceMetadata = cryptographyService.getMetaData(invoice);
        var encryptedInvoiceMetadata = cryptographyService.getMetaData(encryptedInvoice);

        //prepare request
        var sendInvoiceRequest = new SendInvoiceRequestBuilder()
                .withInvoiceHash(invoiceMetadata.getHashSHA())
                .withInvoiceSize(invoiceMetadata.getFileSize())
                .withEncryptedInvoiceHash(encryptedInvoiceMetadata.getHashSHA())
                .withEncryptedInvoiceSize(encryptedInvoiceMetadata.getFileSize())
                .withEncryptedInvoiceContent(Base64.getEncoder().encodeToString(encryptedInvoice))
                .build();

        //send invoice
        return ksefClient.onlineSessionSendInvoice(referenceNumber, sendInvoiceRequest);
    }

    /**
     * Zamknięcie sesji interaktywnej
     *
     * @throws pl.akmf.ksef.sdk.ApiException if fails to make API call
     */
    @PostMapping(value = "/close-session/{referenceNumber}")
    public void sessionClose(@PathVariable String referenceNumber) throws ApiException {
        ksefClient.closeOnlineSession(referenceNumber);
    }
}
