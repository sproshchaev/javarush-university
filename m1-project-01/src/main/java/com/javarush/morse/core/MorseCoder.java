package com.javarush.morse.core;

import com.javarush.morse.exception.MorseException;
import com.javarush.morse.model.ProcessingResult;
import com.javarush.morse.service.ValidationService;

public class MorseCoder {

    private final ValidationService validationService;


    public MorseCoder() {
        this.validationService = new ValidationService();
    }

    public ProcessingResult encodeText(String text) throws MorseException {
        // todo: кодирование текста в Морзе

        // 1) валидировать входной текст
        validationService.validateTextForEncoding(text);

        // 2) привести к верхнему регистру
        String upperText = text.toUpperCase();
        StringBuilder result = new StringBuilder();

        // 3) пройти по всем символам 4) найти код Морзе для каждого символа 5) Собрать результат с пробелами
        for (int i = 0; i < upperText.length(); i++) {
            char currentChar = upperText.charAt(i);
            result.append(Alphabet.TEXT_TO_MORSE.get(currentChar));

            if (i < upperText.length() - 1) {
                result.append(" ");
            }
        }

        String encoded = result.toString();

        // 6) вернуть ProcessingResult
        return new ProcessingResult(true, "Текст успешно заколирован",
                getPreview(text), getPreview(encoded));
    }

    public ProcessingResult decodeText(String morseCode) throws MorseException {
        // 1) валидировать код Морзе
        validationService.validateMorseCode(morseCode);

        // 2) разбить на отдельные символы
        StringBuilder result = new StringBuilder();
        String[] symbols = morseCode.trim().split(" ");

        // 3) найти букву для каждого слова 4) обработать разделитель слов 5) Собрать результат
        for (String symbol : symbols) {
            if (Alphabet.MORSE_TO_TEXT.containsKey(symbol)) {
                result.append(Alphabet.TEXT_TO_MORSE.get(symbol));
            } else if (symbol.equals("/")) {
                result.append(" ");
            }
        }

        // 6) вернуть ProcessingResult
        String decoded = result.toString();
        return new ProcessingResult(true, "Код успешно декодирован",
                getPreview(morseCode), getPreview(decoded));
    }

    public String getPreview(String text) {
        // 1) если текст короткий - вернуть как есть 2) если длинный - обрезать и добавить "..."
        if (text.length() <= 100) {
            return text;
        }
        return text.substring(0, 97) + "...";
    }

}
