package pl.akmf.ksef.sdk.client.model;

import java.util.Objects;

public class Pair {
    private String name = "";
    private String value = "";

    public Pair(String name, String value) {
        setName(name);
        setValue(value);
    }

    private void setName(String name) {
        if (Objects.isNull(value)) {
            return;
        }

        this.name = name;
    }

    private void setValue(String value) {
        if (Objects.isNull(value)) {
            return;
        }

        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }
}
