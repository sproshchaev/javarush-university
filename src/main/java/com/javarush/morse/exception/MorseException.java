package com.javarush.morse.exception;

/**
 * Класс собственных исключений
 */
public class MorseException extends Exception {

    public MorseException(String message) {
        super(message);
    }

    public MorseException(String message, Throwable cause) {
        super(message, cause);
    }

}
