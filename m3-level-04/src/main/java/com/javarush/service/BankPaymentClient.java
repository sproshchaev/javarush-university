package com.javarush.service;

/**
 * Учебный банк: одобряет платеж, если сумма не больше лимита карты.
 */
public class BankPaymentClient implements PaymentClient {
    private final double limit;

    public BankPaymentClient(double limit) {
        this.limit = limit;
    }

    @Override
    public boolean pay(double amount, String cardNumber) {
        boolean approved = amount <= limit;
        System.out.printf("[BANK] карта %s, сумма %.2f: %s%n",
                cardNumber, amount, approved ? "одобрено" : "отказ");
        return approved;
    }
}
