package pl.akmf.ksef.sdk.client.model;

import java.util.List;

public class StatusInfo {
    private Integer code;
    private String description;
    private List<String> details;

    public StatusInfo() {
    }

    public StatusInfo(Integer code, String description, List<String> details) {
        this.code = code;
        this.description = description;
        this.details = details;
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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
