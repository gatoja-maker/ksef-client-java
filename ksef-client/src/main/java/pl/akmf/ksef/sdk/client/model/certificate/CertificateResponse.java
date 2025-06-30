package pl.akmf.ksef.sdk.client.model.certificate;

public class CertificateResponse {
    private byte[] certificate;
    private String certificateName;
    private String certificateSerialNumber;

    public CertificateResponse() {
    }

    public CertificateResponse(byte[] certificate, String certificateName, String certificateSerialNumber) {
        this.certificate = certificate;
        this.certificateName = certificateName;
        this.certificateSerialNumber = certificateSerialNumber;
    }

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateSerialNumber() {
        return certificateSerialNumber;
    }

    public void setCertificateSerialNumber(String certificateSerialNumber) {
        this.certificateSerialNumber = certificateSerialNumber;
    }
}

