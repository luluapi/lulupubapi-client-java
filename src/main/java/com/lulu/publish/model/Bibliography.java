package com.lulu.publish.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Bibliography {
    public static final String LANGUAGE_ENGLISH = "EN";
    public static final String COUNTRY_USA = "US";

    private String title;
    private List<Author> authors;
    private Integer category;
    private Integer copyrightYear;
    private String description;
    private List<String> keywords;
    private String license;
    private String copyrightCitation;
    private String publisher;
    private String edition;
    private String language;
    private String countryCode;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("authors")
    public List<Author> getAuthors() {
        return authors;
    }

    @JsonProperty("authors")
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @JsonProperty("category")
    public Integer getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Integer category) {
        this.category = category;
    }

    @JsonProperty("copyright_year")
    public Integer getCopyrightYear() {
        return copyrightYear;
    }

    @JsonProperty("copyright_year")
    public void setCopyrightYear(Integer copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("keywords")
    public List<String> getKeywords() {
        return keywords;
    }

    @JsonProperty("keywords")
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    @JsonProperty("license")
   public String getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    @JsonProperty("copyright_citation")
     public String getCopyrightCitation() {
        return copyrightCitation;
    }

    @JsonProperty("copyright_citation")
    public void setCopyrightCitation(String copyrightCitation) {
        this.copyrightCitation = copyrightCitation;
    }

    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @JsonProperty("edition")
    public String getEdition() {
        return edition;
    }

    @JsonProperty("edition")
    public void setEdition(String edition) {
        this.edition = edition;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("country_code")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Bibliography{"
                + "authors=" + authors
                + ", title='" + title + '\''
                + ", category=" + category
                + ", copyrightYear=" + copyrightYear
                + ", description='" + description + '\''
                + ", keywords=" + keywords
                + ", license='" + license + '\''
                + ", copyrightCitation='" + copyrightCitation + '\''
                + ", publisher='" + publisher + '\''
                + ", edition='" + edition + '\''
                + ", language='" + language + '\''
                + ", countryCode='" + countryCode + '\''
                + '}';
    }

}
