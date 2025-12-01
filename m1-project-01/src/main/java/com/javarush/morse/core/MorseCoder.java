package com.javarush.morse.core;

import com.javarush.morse.exception.MorseException;
import com.javarush.morse.model.ProcessingResult;
import com.javarush.morse.service.ValidationService;

public class MorseCoder {

    private final ValidationService validationService;


    public MorseCoder(ValidationService validationService) {
        // todo: инициализировать ValidationService
        this.validationService = validationService;
    }

    public ProcessingResult encodeText(String text) throws MorseException {
        // todo: кодирование текста в Морзе
        // 1) валидировать входной текст
        // 2) привести к верхнему регистру
        // 3) пройти по всем символам
        // 4) найти код Морзе для каждого символа
        // 5) Собрать результат с пробелами
        // 6) вернуть ProcessingResult
        return null;
    }

    public ProcessingResult decodeText(String morseCode) throws MorseException {
        // todo: декодирование кода Морзе в текст
        // 1) валидировать код Морзе
        // 2) разбить на отдельные символы
        // 3) найти букву для каждого слова
        // 4) обработать разделитель слов
        // 5) Собрать результат
        // 6) вернуть ProcessingResult
        return null;
    }

    public String getPreview(String text) {
        // todo: создание превью текста
        // 1) если текст короткий - вернуть как есть
        // 2) если длинный - обрезать и добавить "..."
        return null;
    }


}
