package com.javarush.morse.core;

import java.util.HashMap;
import java.util.Map;

public class Alphabet {

    public static final Map<Character, String> TEXT_TO_MORSE;
    public static final Map<String, Character> MORSE_TO_TEXT;

    static {
        // todo: инициализировать алфавит Морзе
        // - создать временные HashMap
        // - заполнить русские буквы
        // - заполнить цифры (*)
        // - заполнить пунктуацию (*)
        // - создать обратное отображение
        // - сделать коллекции неизменяемыми
        TEXT_TO_MORSE = null;
        MORSE_TO_TEXT = null;
    }

    private Alphabet() {
        // todo% запрет создания  - утилитарный класс
    }

}
