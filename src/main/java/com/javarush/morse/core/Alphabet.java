package com.javarush.morse.core;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Типа утилитарного класса который содержит основу кодирования
 */
public class Alphabet {

    // TEXT_TO_MORSE
    private static final Map<Character, String> TEXT_TO_MORSE = new HashMap<>();
    // MORSE_TO_TEXT
    private static final Map<String, Character> MORSE_TO_TEXT = new HashMap<>();

    static {
        // TODO: заполнить буквы, цифры и тд
    }

    private Alphabet() {

    }

    private static void add(char symbol, String code) {
        // TODO: положить пару в обе карты
    }

    private static char normalize(char symbol) {
        // TODO: перевод в верхний регистр
        return symbol;
    }

    // Alphabet.isSupported('A')
    public static boolean isSupported(char symbol) {
        // TODO: проверка - есть ли символ в азбуке
        return false;
    }

    public static boolean isKnownCode(String code) {
        // TODO: есть ли такой код в азбуке
        return false;
    }

    public static String toMorse(char symbol) {
        // TODO: код для символа
        return "";
    }

    public static char fromMorse() {
        // TODO: символ для кода
        return ' ';
    }

    public static Map<Character, String> table() {
        // TODO: копия таблицы для вывода справки
        return new LinkedHashMap<>();
    }

}
