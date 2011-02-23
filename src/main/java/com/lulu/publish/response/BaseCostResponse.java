package com.lulu.publish.response;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class BaseCostResponse {

    private static final Logger LOG = LoggerFactory.getLogger(BaseCostResponse.class); // NOPMD

    private BigDecimal cost;
    private String currency;

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "BaseCostResponse{"
                + "cost=" + cost
                + ", currency='" + currency + '\''
                + '}';
    }

}
