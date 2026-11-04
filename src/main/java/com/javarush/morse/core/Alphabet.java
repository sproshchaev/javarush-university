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
        add('А', ".-");
        add('Б', "-...");
        add('В', ".--");
        // TODO: до заполнить буквы, цифры и тд
    }

    private Alphabet() {

    }

    private static void add(char symbol, String code) {
        TEXT_TO_MORSE.put(symbol, code);
        MORSE_TO_TEXT.put(code, symbol);
    }

    private static char normalize(char symbol) {
        char upper = Character.toUpperCase(symbol);
        return upper == 'Ё' ? 'Е' : upper;
    }

    public static boolean isSupported(char symbol) {
        return TEXT_TO_MORSE.containsKey(symbol);
    }

    public static boolean isKnownCode(String code) {
        return MORSE_TO_TEXT.containsKey(code);
    }

    public static String toMorse(char symbol) {
        return TEXT_TO_MORSE.get(normalize(symbol));
    }

    public static char fromMorse(String code) {
        return MORSE_TO_TEXT.get(code.trim());
    }

    public static Map<Character, String> table() {
        return new LinkedHashMap<>(TEXT_TO_MORSE);
    }

}
