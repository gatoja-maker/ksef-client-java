package pl.akmf.ksef.sdk.client.model;

public class BaseStatusInfo {
    private Integer code;
    private String description;

    public BaseStatusInfo(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public BaseStatusInfo() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
