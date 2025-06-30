package pl.akmf.ksef.sdk.api.services;

import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.MimeTypeEnum;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.SignaturePackaging;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.model.x509.CertificateToken;
import eu.europa.esig.dss.validation.CommonCertificateVerifier;
import eu.europa.esig.dss.xades.XAdESSignatureParameters;
import eu.europa.esig.dss.xades.signature.XAdESService;
import pl.akmf.ksef.sdk.client.interfaces.SignatureService;
import pl.akmf.ksef.sdk.sign.LocalSigningContext;
import pl.akmf.ksef.sdk.sign.SignContextProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class DefaultSignatureService implements SignatureService {

    public String sign(byte[] xml, X509Certificate signatureCertificate, PrivateKey privateKey) throws IOException {
        SignContextProvider signContextProvider = new LocalSigningContext();

        DSSDocument toSignDocument = new InMemoryDocument(xml, null, MimeTypeEnum.XML);

        CommonCertificateVerifier commonCertificateVerifier = new CommonCertificateVerifier();
        XAdESService service = new XAdESService(commonCertificateVerifier);

        XAdESSignatureParameters parameters = prepareParameters(signatureCertificate);
        ToBeSigned dataToSign = service.getDataToSign(toSignDocument, parameters);
        SignatureValue signatureValue = signContextProvider.createSignatureValue(dataToSign, signatureCertificate, privateKey);

        DSSDocument signedDocument = service.signDocument(toSignDocument, parameters, signatureValue);
        try (ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) signedDocument.openStream()) {
            return new String(byteArrayInputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private XAdESSignatureParameters prepareParameters(X509Certificate signatureCertificate) {
        XAdESSignatureParameters parameters = new XAdESSignatureParameters();
        parameters.setSignaturePackaging(SignaturePackaging.ENVELOPED);
        parameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_B);
        parameters.setDigestAlgorithm(DigestAlgorithm.SHA256);
        parameters.setSigningCertificateDigestMethod(DigestAlgorithm.SHA256);
        parameters.setEn319132(false);
        parameters.setSigningCertificate(new CertificateToken(signatureCertificate));

        return parameters;
    }
}
