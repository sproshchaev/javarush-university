package com.javarush.morse.service;

import com.javarush.morse.exception.MorseException;

public class ValidationService {

    public void validateTextForEncoding(String text) throws MorseException {
        // todo: валидация текста для кодирования
        // 1) проверить на null и пустоту
        // 2) пройти по всем символам
        // 3) проверить наличие в алфавите
        // 4) выбросить исключение с инфо о позиции ошибки
    }

    public void validateMorseCode(String morseCode)  throws MorseException {
        // todo: валидация кода Морзе
        // 1) проверить на null и пустоту
        // 2) разбить на отдельные коды
        // 3) Проверить каждый код (кроме разделителя слов)
        // 4) Выбросить исключение с инфо о позиции ошибки
    }

}
