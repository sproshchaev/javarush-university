package com.javarush.morse.service;

import com.javarush.morse.core.Alphabet;
import com.javarush.morse.exception.MorseException;

/**
 * Валидация отдельно от алгоритма
 */
public class Validator {

    public void validateText(String line, int lineNumber) throws MorseException {
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);
            if (!Alphabet.isSupported(symbol)) {
                throw new MorseException("Строка " + lineNumber + ", позиция " + (i + 1)
                        + ": символа '" + symbol + "' нет в алфавите");
            }
        }
    }

    public void validateMorse(String line, int lineNumber) throws MorseException {
        String[] codes = line.trim().split(" +");
        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];
            if (!code.isEmpty() && !Alphabet.isKnownCode(code)) {
                throw new MorseException("Строка " + lineNumber + ", код " + (i + 1) + ": кода '" + code + "' нет в алфавите");
            }
        }
    }


}
