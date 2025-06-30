package pl.akmf.ksef.sdk.client.model.invoice;

/**
 * Dane nabywcy.
 */
public class InvoicesAsynqQueryRequestBuyer {
    private BuyerIdentifierType identifierType;

    private String identifier;

    private String name;

    public InvoicesAsynqQueryRequestBuyer() {
    }

    public BuyerIdentifierType getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(BuyerIdentifierType identifierType) {
        this.identifierType = identifierType;
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

