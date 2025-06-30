package pl.akmf.ksef.sdk.client.model.certificate;

import java.util.Objects;

public class CsrResult {
    private final byte[] csr;
    private final byte[] privateKey;

    public CsrResult(byte[] csr, byte[] privateKey) {
        this.csr = csr;
        this.privateKey = privateKey;
    }

    public byte[] csr() {
        return csr;
    }

    public byte[] privateKey() {
        return privateKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CsrResult) obj;
        return Objects.equals(this.csr, that.csr) &&
                Objects.equals(this.privateKey, that.privateKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(csr, privateKey);
    }

    @Override
    public String toString() {
        return "CsrResult[" +
                "csr=" + csr + ", " +
                "privateKey=" + privateKey + ']';
    }

}
