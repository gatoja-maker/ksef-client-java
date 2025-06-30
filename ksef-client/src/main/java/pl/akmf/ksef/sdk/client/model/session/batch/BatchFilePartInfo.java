package pl.akmf.ksef.sdk.client.model.session.batch;

public class BatchFilePartInfo {
    private int ordinalNumber;
    private String fileName;
    private long fileSize;
    private String fileHash;

    public BatchFilePartInfo() {

    }

    public BatchFilePartInfo(int ordinalNumber, String fileName, long fileSize, String fileHash) {
        this.ordinalNumber = ordinalNumber;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileHash = fileHash;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }
}
