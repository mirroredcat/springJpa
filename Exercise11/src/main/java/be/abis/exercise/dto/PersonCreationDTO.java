package be.abis.exercise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PersonCreationDTO {

    private String firstName;
    private String lastName;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate birthDate;
    private String emailAddress;
    private String password;
    private String language;
    private String companyName;
    private String companyTelephoneNumber;
    private String companyVatNr;
    private String companyAddressStreet;
    private String companyAddressStreetNr;
    private String companyAddressZipcode;
    private String companyAddressTown;
    private String companyAddressCountryCode;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTelephoneNumber() {
        return companyTelephoneNumber;
    }

    public void setCompanyTelephoneNumber(String companyTelephoneNumber) {
        this.companyTelephoneNumber = companyTelephoneNumber;
    }

    public String getCompanyVatNr() {
        return companyVatNr;
    }

    public void setCompanyVatNr(String companyVatNr) {
        this.companyVatNr = companyVatNr;
    }

    public String getCompanyAddressStreet() {
        return companyAddressStreet;
    }

    public void setCompanyAddressStreet(String companyAddressStreet) {
        this.companyAddressStreet = companyAddressStreet;
    }

    public String getCompanyAddressStreetNr() {
        return companyAddressStreetNr;
    }

    public void setCompanyAddressStreetNr(String companyAddressStreetNr) {
        this.companyAddressStreetNr = companyAddressStreetNr;
    }

    public String getCompanyAddressZipcode() {
        return companyAddressZipcode;
    }

    public void setCompanyAddressZipcode(String companyAddressZipcode) {
        this.companyAddressZipcode = companyAddressZipcode;
    }

    public String getCompanyAddressTown() {
        return companyAddressTown;
    }

    public void setCompanyAddressTown(String companyAddressTown) {
        this.companyAddressTown = companyAddressTown;
    }

    public String getCompanyAddressCountryCode() {
        return companyAddressCountryCode;
    }

    public void setCompanyAddressCountryCode(String companyAddressCountryCode) {
        this.companyAddressCountryCode = companyAddressCountryCode;
    }
}
