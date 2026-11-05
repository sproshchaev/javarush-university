package com.javarush.service;

/**
 * Курс доллара задан заранее. В жизни его отдавал бы банк по сети.
 */
public class FixedCurrencyService implements CurrencyService {
    private final double usdRate;

    public FixedCurrencyService(double usdRate) {
        this.usdRate = usdRate;
    }

    @Override
    public double getUsdRate() {
        return usdRate;
    }
}
