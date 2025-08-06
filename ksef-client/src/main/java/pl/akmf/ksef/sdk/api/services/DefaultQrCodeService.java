package pl.akmf.ksef.sdk.api.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import pl.akmf.ksef.sdk.client.interfaces.QrCodeService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

public class DefaultQrCodeService implements QrCodeService {

    @Override
    public byte[] generateQrCode(String payloadUrl) throws WriterException, IOException {
        return generateQrCode(payloadUrl, 20, 300);
    }

    @Override
    public byte[] addLabelToQrCode(byte[] qrCodePng, String label) throws IOException {
        return addLabelToQrCode(qrCodePng, label, 14);
    }

    @Override
    public byte[] generateQrCode(String payloadUrl, int pixelsPerModule, int qrCodeWidthAndHeight) throws IOException, WriterException {
        BufferedImage qrImage = createQrImage(payloadUrl, pixelsPerModule);
        BufferedImage resized = resizePng(qrImage, qrCodeWidthAndHeight, qrCodeWidthAndHeight);
        return toByteArray(resized);
    }

    @Override
    public byte[] addLabelToQrCode(byte[] qrPng, String label, int fontSizePx) throws IOException {
        // 1. Bitmapa QR z bajtów
        InputStream is = new ByteArrayInputStream(qrPng);
        BufferedImage qrImage = ImageIO.read(is);

        // 2. Font + wysokość napisu
        Font font = new Font("Arial", Font.BOLD, fontSizePx);
        int labelHeight = fontSizePx + 4;

        // 3. Nowa bitmapa = QR + pasek na tekst
        int width = qrImage.getWidth();
        int height = qrImage.getHeight() + labelHeight;
        BufferedImage labeledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = labeledImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 4. Rysuj QR
        g.drawImage(qrImage, 0, 0, null);

        // 5. Rysuj tekst wyśrodkowany
        g.setFont(font);
        g.setColor(Color.BLACK);
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(label);
        int x = (width - textWidth) / 2;
        int y = qrImage.getHeight() + ((labelHeight + metrics.getAscent()) / 2) - 2;
        g.drawString(label, x, y);
        g.dispose();

        // 6. PNG → bajt[]
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(labeledImage, "PNG", baos);
        return baos.toByteArray();
    }

    private BufferedImage createQrImage(String payloadUrl, int scale) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.MARGIN, 1);

        BitMatrix bitMatrix = qrCodeWriter.encode(payloadUrl, BarcodeFormat.QR_CODE, 0, 0, hints);

        int width = bitMatrix.getWidth() * scale;
        int height = bitMatrix.getHeight() * scale;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); // background
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK); // QR color

        for (int y = 0; y < bitMatrix.getHeight(); y++) {
            for (int x = 0; x < bitMatrix.getWidth(); x++) {
                if (bitMatrix.get(x, y)) {
                    graphics.fillRect(x * scale, y * scale, scale, scale);
                }
            }
        }
        graphics.dispose();
        return image;
    }

    private BufferedImage resizePng(BufferedImage original, int targetWidth, int targetHeight) {
        BufferedImage resized = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(original, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resized;
    }

    private byte[] toByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
}
