package com.javarush.demo.exception;

/**
 * Кастомное проверяемое исключение
 */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message); // означает что мы вызываем конструктор у родителя
    }
}
