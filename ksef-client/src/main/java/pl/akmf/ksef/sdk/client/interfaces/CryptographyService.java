package pl.akmf.ksef.sdk.client.interfaces;

import pl.akmf.ksef.sdk.client.model.ApiException;
import pl.akmf.ksef.sdk.client.model.certificate.CertificateEnrollmentsInfoResponse;
import pl.akmf.ksef.sdk.client.model.certificate.CsrResult;
import pl.akmf.ksef.sdk.client.model.session.EncryptionData;
import pl.akmf.ksef.sdk.client.model.session.FileMetadata;
import pl.akmf.ksef.sdk.system.SystemKSeFSDKException;

public interface CryptographyService {

    EncryptionData getEncryptionData() throws SystemKSeFSDKException, ApiException;

    public  byte[] encryptKsefTokenWithRSAUsingPublicKey(byte[] content) throws SystemKSeFSDKException, ApiException;

    byte[] encryptBytesWithAES256(byte[] content, byte[] key, byte[] iv) throws SystemKSeFSDKException;

    CsrResult generateCsr(CertificateEnrollmentsInfoResponse certificateInfo) throws SystemKSeFSDKException;

    FileMetadata getMetaData(byte[] file) throws SystemKSeFSDKException;

}
