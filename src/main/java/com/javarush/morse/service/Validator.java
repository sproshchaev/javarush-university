package com.javarush.morse.service;

import com.javarush.morse.exception.MorseException;

public class Validator {

    public void validateText(String line, int lineNumber) throws MorseException {
        // TODO: каждый символ строки есть в азбуке, иначе исключение с номером строки и позицией
    }

    public void validateMorse(String line, int lineNumber) throws MorseException {
        // TODO: каждый код строки есть в азбуке, иначе исключение с номером строки и кода
    }


}
