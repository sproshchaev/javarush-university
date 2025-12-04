package com.javarush.morse.exception;

// Проверяемые исключения
public class MorseException extends Exception {

    public MorseException(String message) {
        // todo: вызвать конструктор родителя с сообщением
    }

    // Непроверяемые исключения
    public MorseException(String message, Throwable cause) {
        //  todo: вызвать конструктор родителя с сообщением и причиной
    }

}
