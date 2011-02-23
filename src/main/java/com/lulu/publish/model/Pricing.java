package com.lulu.publish.model;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pricing {

    public static final String CURRENCY_CODE_USD = "USD";

    private ProductType productType;
    private String currencyCode;
    private BigDecimal royalty;
    private BigDecimal totalPrice;
    private int discountPercent;

    @JsonProperty("product")
    public ProductType getProductType() {
        return productType;
    }

    @JsonProperty("product")
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @JsonProperty("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currency_code")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty
    public BigDecimal getRoyalty() {
        return royalty;
    }

    @JsonProperty
    public void setRoyalty(BigDecimal royalty) {
        this.royalty = royalty;
    }

    @JsonProperty("total_price")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @JsonProperty("total_price")
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @JsonProperty("discount_percent")
    public int getDiscountPercent() {
        return discountPercent;
    }

    @JsonProperty("discount_percent")
    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "Pricing{"
                + "currencyCode='" + currencyCode + '\''
                + ", productType=" + productType
                + ", royalty=" + royalty
                + ", totalPrice=" + totalPrice
                + ", discountPercent=" + discountPercent
                + '}';
    }
}
