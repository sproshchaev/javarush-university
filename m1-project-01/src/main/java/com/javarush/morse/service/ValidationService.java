package com.javarush.morse.service;

import com.javarush.morse.core.Alphabet;
import com.javarush.morse.exception.MorseException;

public class ValidationService {

    public void validateTextForEncoding(String text) throws MorseException {
        // todo: валидация текста для кодирования

        // 1) проверить на null и пустоту
        if (text == null || text.trim().isEmpty()) {
            throw new MorseException("Текст для кодирования не может быть пустым");
        }

        // 2) пройти по всем символам 3) проверить наличие в алфавите 4) выбросить исключение с инфо о позиции ошибки
        String upperText = text.toUpperCase();
        for (int i = 0; i < upperText.length(); i++) {
            char c = upperText.charAt(i);
            if (!Alphabet.TEXT_TO_MORSE.containsKey(c)) {
                throw new MorseException(String.format("Неподдерживаемый символ '%c' в позиции %d. Выберите \"3. Справка по алфавиту Морзе\" и проверьте содержимое словаря!", c, (i + 1)));
            }
        }
    }

    public void validateMorseCode(String morseCode)  throws MorseException {
        // todo: валидация кода Морзе
        // 1) проверить на null и пустоту
        if (morseCode == null || morseCode.trim().isEmpty()) {
            throw new MorseException("Код Морзе не может быть пустым!");
        }
        // 2) разбить на отдельные коды [.-] [.--] [--] - результат split
        String[] symbols = morseCode.trim().split(" ");
        // 3) Проверить каждый код (кроме разделителя слов) 4) Выбросить исключение с инфо о позиции ошибки
        for (int i = 0; i < symbols.length; i++) {
            String symbol = symbols[i];
            // см textToMorse.put(' ', "/");
            if (!symbol.equals("/") && !Alphabet.MORSE_TO_TEXT.containsKey(symbol)) {
                throw new MorseException(
                        String.format("Некорректный код Морзе '%s' в позиции %d", symbol, (i + 1)));
            }
        }
    }

}
