package com.javarush.service;

/**
 * Платежный шлюз с повтором: до трех попыток при сбое клиента банка.
 */
public class RetryingPaymentService implements PaymentGateway {
    static final int MAX_ATTEMPTS = 3;

    private final PaymentClient client;

    public RetryingPaymentService(PaymentClient client) {
        this.client = client;
    }

    @Override
    public boolean processPayment(double amount, String cardNumber) {
        RuntimeException lastError = null;
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            try {
                return client.pay(amount, cardNumber);
            } catch (RuntimeException e) {
                lastError = e;
            }
        }
        throw lastError;
    }
}
