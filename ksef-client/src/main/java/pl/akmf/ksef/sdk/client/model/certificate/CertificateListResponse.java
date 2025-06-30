package pl.akmf.ksef.sdk.client.model.certificate;

import java.util.List;

public class CertificateListResponse {
    private List<CertificateResponse> certificates;

    public CertificateListResponse() {
    }

    public CertificateListResponse(List<CertificateResponse> certificates) {
        this.certificates = certificates;
    }

    public List<CertificateResponse> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateResponse> certificates) {
        this.certificates = certificates;
    }
}

