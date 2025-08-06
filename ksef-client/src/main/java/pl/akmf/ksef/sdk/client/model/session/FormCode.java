package pl.akmf.ksef.sdk.client.model.session;

public class FormCode {
    private SystemCode systemCode;
    private String schemaVersion;
    private String value;

    public FormCode() {
    }

    public FormCode(SystemCode systemCode, String schemaVersion, String value) {
        this.systemCode = systemCode;
        this.schemaVersion = schemaVersion;
        this.value = value;
    }

    public SystemCode getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(SystemCode systemCode) {
        this.systemCode = systemCode;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

