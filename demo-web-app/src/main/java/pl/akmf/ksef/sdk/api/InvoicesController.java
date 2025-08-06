package pl.akmf.ksef.sdk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.akmf.ksef.sdk.api.services.DefaultKsefClient;
import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.invoice.AsyncInvoicesQueryStatus;
import pl.akmf.ksef.sdk.client.model.invoice.DownloadInvoiceRequest;
import pl.akmf.ksef.sdk.client.model.invoice.InitAsyncInvoicesQueryResponse;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesAsynqQueryRequest;
import pl.akmf.ksef.sdk.client.model.invoice.InvoicesQueryRequest;
import pl.akmf.ksef.sdk.client.model.invoice.QueryInvoicesReponse;

@RestController
@RequiredArgsConstructor
public class InvoicesController {
    private final DefaultKsefClient ksefClient;

    @GetMapping("/invoices/ksef/{ksefReferenceNumber}")
    public String getInvoice(@PathVariable String ksefReferenceNumber) throws ApiException {
        return ksefClient.getInvoice(ksefReferenceNumber);
    }

    @PostMapping("/invoices/")
    public String getInvoice(@RequestBody DownloadInvoiceRequest downloadInvoiceRequest) throws ApiException {
        return ksefClient.getInvoice(downloadInvoiceRequest);
    }

    @PostMapping("/invoices/metadata")
    QueryInvoicesReponse getInvoiceMetadata(@RequestParam Integer pageOffset, @RequestParam  Integer pageSize, @RequestBody InvoicesQueryRequest request) throws ApiException {
        return ksefClient.queryInvoices(pageOffset, pageSize, request);
    }

    @PostMapping("/invoices/query/async")
    InitAsyncInvoicesQueryResponse initAsyncInvoiceQuery(@RequestBody InvoicesAsynqQueryRequest request) throws ApiException {
        return ksefClient.initAsyncQueryInvoice(request);
    }

    @GetMapping("/invoices/query/async")
    AsyncInvoicesQueryStatus checkStatusAsyncQueryInvoice(@RequestParam String operationReferenceNumber) throws ApiException {
        return ksefClient.checkStatusAsyncQueryInvoice(operationReferenceNumber);
    }

}
