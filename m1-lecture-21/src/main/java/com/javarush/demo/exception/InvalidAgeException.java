package com.javarush.demo.exception;

/**
 * Кастомное непроверяемое исключение
 */
public class InvalidAgeException extends RuntimeException {

    public InvalidAgeException(String message) {
        super(message);
    }

}
