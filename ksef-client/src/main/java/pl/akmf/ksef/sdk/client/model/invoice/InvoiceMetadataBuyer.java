
package pl.akmf.ksef.sdk.client.model.invoice;

/**
 * InvoiceMetadataBuyer
 */
public class InvoiceMetadataBuyer {
    private BuyerIdentifierType identifierType;

    private String identifier;

    private String name;

    public InvoiceMetadataBuyer() {
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

