package pl.akmf.ksef.sdk;

import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.akmf.ksef.sdk.api.builders.session.OpenOnlineSessionRequestBuilder;
import pl.akmf.ksef.sdk.api.builders.session.SendInvoiceRequestBuilder;
import pl.akmf.ksef.sdk.api.services.DefaultCryptographyService;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.session.EncryptionData;
import pl.akmf.ksef.sdk.client.model.session.FormCode;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoice;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoicesResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionStatusResponse;
import pl.akmf.ksef.sdk.client.model.session.UpoPageResponse;
import pl.akmf.ksef.sdk.client.model.session.online.OpenOnlineSessionResponse;
import pl.akmf.ksef.sdk.client.model.session.online.SendInvoiceResponse;
import pl.akmf.ksef.sdk.client.model.xml.ContextIdentifierTypeEnum;
import pl.akmf.ksef.sdk.configuration.BaseIntegrationTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

class OnlineSessionIntegrationTest extends BaseIntegrationTest {

    private EncryptionData encryptionData;

    //TODO FAKTURY SIE NIE PRZETWARZAJĄ
    @Test
    void onlineSessionE2EIntegrationTest() throws JAXBException, IOException, InterruptedException, ApiException {
        String contextNip = TestUtils.generateRandomNIP();
        authWithCustomNip(contextNip, ContextIdentifierTypeEnum.NIP, contextNip);

        var cryptographyService = new DefaultCryptographyService(defaultKsefClient);
        encryptionData = cryptographyService.getEncryptionData();

        // Step 1: Open session and return referenceNumber
        String sessionReferenceNumber = openOnlineSession(encryptionData);
        Thread.sleep(sleepTime);
        // Step 2: Send invoice
        sendInvoiceOnlineSession(TestUtils.generateRandomNIP(), sessionReferenceNumber, encryptionData, cryptographyService);
        Thread.sleep(2000);
        // Step 3: Check status
        getOnlineSessionStatus(sessionReferenceNumber);
        Thread.sleep(sleepTime);
        // Step 4: Close session
        closeOnlineSession(sessionReferenceNumber);
        Thread.sleep(2000);
        // Step 5: Get documents
        String ksefNumber = getOnlineSessionDocuments(sessionReferenceNumber);
        // Step 6: Get status after close
        String upoReferenceNumber = getOnlineSessionStatusAfterCloseSession(sessionReferenceNumber);
        // Step 7: Get UPO
        getOnlineSessionInvoiceUpo(sessionReferenceNumber, ksefNumber);
        // Step 8: Get session UPO
        getOnlineSessionUpo(sessionReferenceNumber, upoReferenceNumber);
    }

    private void getOnlineSessionStatus(String sessionReferenceNumber) throws ApiException {
        SessionStatusResponse statusResponse = defaultKsefClient.getSessionStatus(sessionReferenceNumber);
        Assertions.assertNotNull(statusResponse);
        Assertions.assertNotNull(statusResponse.getSuccessfulInvoiceCount());
        Assertions.assertEquals(1, (int) statusResponse.getSuccessfulInvoiceCount());
        Assertions.assertNull(statusResponse.getFailedInvoiceCount());
        Assertions.assertNull(statusResponse.getUpo());
        Assertions.assertEquals(100, (int) statusResponse.getStatus().getCode());
    }

    private String getOnlineSessionStatusAfterCloseSession(String sessionReferenceNumber) throws ApiException {
        SessionStatusResponse statusResponse = defaultKsefClient.getSessionStatus(sessionReferenceNumber);
        Assertions.assertNotNull(statusResponse);
        Assertions.assertNotNull(statusResponse.getSuccessfulInvoiceCount());
        Assertions.assertEquals(1, (int) statusResponse.getSuccessfulInvoiceCount());
        Assertions.assertNull(statusResponse.getFailedInvoiceCount());
        Assertions.assertNotNull(statusResponse.getUpo());
        Assertions.assertEquals(200, (int) statusResponse.getStatus().getCode());
        UpoPageResponse upoPageResponse = statusResponse.getUpo().getPages().getFirst();
        Assertions.assertNotNull(upoPageResponse);
        Assertions.assertNotNull(upoPageResponse.getReferenceNumber());

        return upoPageResponse.getReferenceNumber();
    }

    private String openOnlineSession(EncryptionData encryptionData) throws ApiException {
        var request = new OpenOnlineSessionRequestBuilder()
                .withFormCode(new FormCode("FA (2)","1-0E","FA"))
                .withEncryptionInfo(encryptionData.encryptionInfo())
                .build();

        OpenOnlineSessionResponse openOnlineSessionResponse = defaultKsefClient.openOnlineSession(request);
        Assertions.assertNotNull(openOnlineSessionResponse);
        Assertions.assertNotNull(openOnlineSessionResponse.getReferenceNumber());
        return openOnlineSessionResponse.getReferenceNumber();
    }

    private void sendInvoiceOnlineSession(String nip, String sessionReferenceNumber, EncryptionData encryptionData, DefaultCryptographyService cryptographyService) throws IOException, ApiException {
        String invoiceTemplate = new String(BaseIntegrationTest.class.getResourceAsStream("/xml/invoices/sample/invoice-template.xml")
                .readAllBytes(), StandardCharsets.UTF_8)
                .replace("#nip#", nip)
                .replace("#invoice_number#", UUID.randomUUID().toString());

        var invoice = invoiceTemplate.getBytes(StandardCharsets.UTF_8);

        var encryptedInvoice = cryptographyService.encryptBytesWithAES256(invoice,
                encryptionData.cipherKey(),
                encryptionData.cipherIv());

        var invoiceMetadata = cryptographyService.getMetaData(invoice);
        var encryptedInvoiceMetadata = cryptographyService.getMetaData(encryptedInvoice);

        var sendInvoiceRequest = new SendInvoiceRequestBuilder()
                .withInvoiceHash(invoiceMetadata.getHashSHA())
                .withInvoiceSize(invoiceMetadata.getFileSize())
                .withEncryptedInvoiceHash(encryptedInvoiceMetadata.getHashSHA())
                .withEncryptedInvoiceSize(encryptedInvoiceMetadata.getFileSize())
                .withEncryptedInvoiceContent(Base64.getEncoder().encodeToString(encryptedInvoice))
                .build();

        SendInvoiceResponse sendInvoiceResponse = defaultKsefClient.onlineSessionSendInvoice(sessionReferenceNumber, sendInvoiceRequest);
        Assertions.assertNotNull(sendInvoiceResponse);
        Assertions.assertNotNull(sendInvoiceResponse.getReferenceNumber());
    }

    private void closeOnlineSession(String sessionReferenceNumber) throws ApiException {
        defaultKsefClient.closeOnlineSession(sessionReferenceNumber);
    }

    private String getOnlineSessionDocuments(String sessionReferenceNumber) throws ApiException {
        SessionInvoicesResponse sessionInvoices = defaultKsefClient.getSessionInvoices(sessionReferenceNumber, 10, 0);
        Assertions.assertEquals(1, sessionInvoices.getTotalCount());
        Assertions.assertEquals(1, sessionInvoices.getInvoices().size());
        SessionInvoice invoice = sessionInvoices.getInvoices().getFirst();
        Assertions.assertNotNull(invoice);
        Assertions.assertNotNull(invoice.getOrdinalNumber());
        Assertions.assertNotNull(invoice.getInvoiceNumber());
        Assertions.assertNotNull(invoice.getKsefNumber());
        Assertions.assertNotNull(invoice.getReferenceNumber());
        Assertions.assertNotNull(invoice.getInvoiceHash());
        Assertions.assertNotNull(invoice.getReceiveDate());
        Assertions.assertNotNull(invoice.getStatus());
        Assertions.assertEquals(200, invoice.getStatus().getCode());

        return invoice.getKsefNumber();
    }

    private void getOnlineSessionInvoiceUpo(String sessionReferenceNumber, String ksefNumber) throws ApiException {
        var upoResponse = defaultKsefClient.getSessionInvoiceUpoByKsefNumber(sessionReferenceNumber, ksefNumber);

        Assertions.assertNotNull(upoResponse);
    }

    private void getOnlineSessionUpo(String sessionReferenceNumber, String upoReferenceNumber) throws ApiException {
        var sessionUpo = defaultKsefClient.getSessionUpo(sessionReferenceNumber, upoReferenceNumber);

        Assertions.assertNotNull(sessionUpo);
    }
}
