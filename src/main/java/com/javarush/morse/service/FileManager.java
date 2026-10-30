package com.javarush.morse.service;

import com.javarush.morse.exception.MorseException;

import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public List<String> readLines(String fileName) throws MorseException {
        // TODO: проверить что файл есть, прочитать все строки
        return new ArrayList<>();
    }

    public void writeLines(String fileName, List<String> lines) throws MorseException {
        // TODO: создать папку (если ее нет и записать строки)
    }

    public void checkDifferent(String input, String output) throws MorseException {
        // ToDo: запретить запись результата поверх исходного файла
    }

}
