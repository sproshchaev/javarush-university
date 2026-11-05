package com.javarush.service;

public class PriceCalculator {
    private final CurrencyService currencyService;

    public PriceCalculator(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public double toRub(double usd) {
        return usd * currencyService.getUsdRate();
    }
}
