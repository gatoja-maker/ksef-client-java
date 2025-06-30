package pl.akmf.ksef.sdk.client.model.invoice;

/**
 * Dane sprzedawcy.
 */
public class InvoicesAsynqQueryRequestSeller {
    private String identifier;

    private String name;

    public InvoicesAsynqQueryRequestSeller() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

