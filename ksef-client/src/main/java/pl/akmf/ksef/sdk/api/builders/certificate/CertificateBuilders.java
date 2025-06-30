package pl.akmf.ksef.sdk.api.builders.certificate;

import org.bouncycastle.asn1.x500.X500Name;

import java.util.ArrayList;
import java.util.List;

public class CertificateBuilders {
    private final List<String> xnameList = new ArrayList<>();

    public CertificateBuilders withOrganizationName(String organizationName) {
        xnameList.add("2.5.4.10=" + organizationName);
        return this;
    }

    public CertificateBuilders withOrganizationIdentifier(String organizationIdentifier) {
        xnameList.add("2.5.4.97=" + organizationIdentifier);
        return this;
    }

    public CertificateBuilders withCommonName(String commonName) {
        xnameList.add("2.5.4.3=" + commonName);
        return this;
    }

    public CertificateBuilders withSerialNumber(String serialNumber) {
        xnameList.add("2.5.4.5=" + serialNumber);
        return this;
    }

    public CertificateBuilders withGivenName(String givenName) {
        xnameList.add("2.5.4.42=" + givenName);
        return this;
    }

    public CertificateBuilders withSurname(String surname) {
        xnameList.add("2.5.4.4=" + surname);
        return this;
    }

    public X500Name build() {
        xnameList.add("2.5.4.6=PL");
        String value = xnameList.toString();

        value = value.replace("[", "");
        value = value.replace("]", "");
        return new X500Name(value);
    }

    public X500Name buildForOrganization(String organizationName, String organizationIdentifier, String commonName) {
        withOrganizationIdentifier(organizationIdentifier);
        withOrganizationName(organizationName);
        withCommonName(commonName);

        xnameList.add("2.5.4.6=PL");
        String value = xnameList.toString();

        value = value.replace("[", "");
        value = value.replace("]", "");
        return new X500Name(value);

    }

    public X500Name buildForPerson(String givenName, String surname, String serialNumber, String commonName) {
        withGivenName(givenName);
        withSurname(surname);
        withSerialNumber(serialNumber);
        withCommonName(commonName);
        xnameList.add("2.5.4.6=PL");

        String value = xnameList.toString();

        value = value.replace("[", "");
        value = value.replace("]", "");
        return new X500Name(value);
    }
}
