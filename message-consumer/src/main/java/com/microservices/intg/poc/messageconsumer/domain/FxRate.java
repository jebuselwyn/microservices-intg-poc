package com.microservices.intg.poc.messageconsumer.domain;

public class FxRate {

    private String currencyPair;
    private Double fxRate;

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Double getFxRate() {
        return fxRate;
    }

    public void setFxRate(Double fxRate) {
        this.fxRate = fxRate;
    }
}
