package com.javarush.morse.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Alphabet {

    public static final Map<Character, String> TEXT_TO_MORSE;
    public static final Map<String, Character> MORSE_TO_TEXT;

    // Этот код выполняется при загрузке
    static {
        // todo: инициализировать алфавит Морзе
        // - создать временные HashMap
        Map<Character, String> textToMorse = new HashMap<>();
        Map<String, Character> morseToText = new HashMap<>();

        // - заполнить русские буквы
        textToMorse.put('А', ".-");
        textToMorse.put('Б', "-...");
        // todo продолжить от А до Я
        textToMorse.put('Я', ".-.-");

        // - заполнить цифры (*)
        textToMorse.put('0', "-----");
        // todo продолжить от 1 до 8
        textToMorse.put('9', "----.");

        // - заполнить пунктуацию (*)
        textToMorse.put('.', "......");
        // todo заполнить остальные знаки пунктуации
        textToMorse.put(' ', "/");

        // - создать обратное отображение
        for (Map.Entry<Character, String> entry : textToMorse.entrySet()) {
            morseToText.put(entry.getValue(), entry.getKey());
        }

        // - сделать коллекции неизменяемыми
        TEXT_TO_MORSE = Collections.unmodifiableMap(textToMorse);
        MORSE_TO_TEXT = Collections.unmodifiableMap(morseToText);
    }

    private Alphabet() {}
}
