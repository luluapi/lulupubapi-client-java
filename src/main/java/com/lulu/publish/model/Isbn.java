package com.lulu.publish.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Isbn {

    private IsbnIntent isbnIntent;
    private String number;
    private String publisher;
    private ContactInfo contactInfo;

    @JsonProperty("intent")
    public IsbnIntent getIsbnIntent() {
        return isbnIntent;
    }

    @JsonProperty("intent")
    public void setIsbnIntent(IsbnIntent isbnIntent) {
        this.isbnIntent = isbnIntent;
    }

    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("contact_info")
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    @JsonProperty("contact_info")
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Isbn{"
                + "contactInfo=" + contactInfo
                + ", isbnIntent=" + isbnIntent
                + ", number='" + number + '\''
                + ", publisher='" + publisher + '\''
                + '}';
    }
}
