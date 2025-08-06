package pl.akmf.ksef.sdk.client.interfaces;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QrCodeService {

    /**
     * Generuje kod QR jako tablicę bajtów PNG.
     *
     * @param payloadUrl           - URL/link do zakodowania.
     * @param pixelsPerModule      - Rozmiar modułu w pikselach (domyślnie 20).
     * @param qrCodeWidthAndHeight - Rozmiar obrazka w pikselach (domyślnie 300).
     * @return
     */
    byte[] generateQrCode(String payloadUrl, int pixelsPerModule, int qrCodeWidthAndHeight) throws WriterException, IOException;

    byte[] generateQrCode(String payloadUrl) throws WriterException, IOException;

    /**
     * Dokleja podpis (label) pod istniejącym PNG z kodem QR.
     *
     * @param qrCodePng
     * @param label
     * @param fontSizePx - rozmiar czcionki w pikselach (domyślnie 14).
     * @return
     */
    byte[] addLabelToQrCode(byte[] qrCodePng, String label, int fontSizePx) throws IOException;

    byte[] addLabelToQrCode(byte[] qrCodePng, String label) throws IOException;
}
