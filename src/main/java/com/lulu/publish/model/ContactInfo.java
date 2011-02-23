package com.lulu.publish.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class ContactInfo {
    private String name;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    @JsonProperty
    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    @JsonProperty
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postal_code")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @JsonProperty
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ContactInfo{"
                + "city='" + city + '\''
                + ", name='" + name + '\''
                + ", street1='" + street1 + '\''
                + ", street2='" + street2 + '\''
                + ", state='" + state + '\''
                + ", postalCode='" + postalCode + '\''
                + ", country='" + country + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}
