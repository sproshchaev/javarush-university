package com.javarush.service;

/**
 * Клиент внешнего банка. Может упасть по таймауту, поэтому его оборачивает RetryingPaymentService.
 */
public interface PaymentClient {
    boolean pay(double amount, String cardNumber);
}
