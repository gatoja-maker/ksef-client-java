package pl.akmf.ksef.sdk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.client.interfaces.KSeFClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoice;
import pl.akmf.ksef.sdk.client.model.session.SessionInvoicesResponse;
import pl.akmf.ksef.sdk.client.model.session.SessionStatusResponse;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {
    private final KSeFClient ksefClient;

    @GetMapping("/status")
    public SessionStatusResponse getStatusAsync(@RequestParam String referenceNumber) throws ApiException {
        return ksefClient.getSessionStatus(referenceNumber);
    }

    @GetMapping("/invoice-upo")
    public byte[] getInvoiceUpoAsync(
            @RequestParam String referenceNumber,
            @RequestParam String ksefNumber) throws ApiException {
        return ksefClient.getSessionInvoiceUpoByKsefNumber(referenceNumber, ksefNumber);
    }

    @GetMapping("/invoice-upo-by-invoice-number")
    public byte[] getInvoiceUpoByReferenceNumber(
            @RequestParam String referenceNumber,
            @RequestParam String invoiceReferenceNumber) throws ApiException {
        return ksefClient.getSessionInvoiceUpoByReferenceNumber(referenceNumber, invoiceReferenceNumber);
    }

    @GetMapping("/session-upo")
    public byte[] getSessionUpoAsync(
            @RequestParam String referenceNumber,
            @RequestParam String upoReferenceNumber) throws ApiException {
        return ksefClient.getSessionUpo(referenceNumber, upoReferenceNumber);
    }

    @GetMapping("/session-documents")
    public ResponseEntity<SessionInvoicesResponse> getSessionDocumentsAsync(
            @RequestParam String referenceNumber) throws ApiException {
        return ResponseEntity.ok(ksefClient.getSessionInvoices(referenceNumber, 10, 0));
    }

    @GetMapping("/failed-invoices")
    public ResponseEntity<SessionInvoicesResponse> getFailedInvoicesAsync(
            @RequestParam String referenceNumber, @RequestParam Integer pageSize) throws ApiException {
        return ResponseEntity.ok(ksefClient.getSessionFailedInvoices(referenceNumber, pageSize));
    }

    @GetMapping("/invoice-status")
    SessionInvoice getSessionInvoiceStatus(
            @RequestParam String referenceNumber,
            @RequestParam String invoiceReferenceNumber) throws ApiException{
        return ksefClient.getSessionInvoiceStatus(referenceNumber, invoiceReferenceNumber);
    }
}

