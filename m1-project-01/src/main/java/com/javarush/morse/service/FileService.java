package com.javarush.morse.service;

import com.javarush.morse.exception.MorseException;

public class FileService {

    public String readFile(String filePath) throws MorseException {
        // todo: чтение файла с валидацией
        // 1) преобразовать путь в Path
        // 2) проверить сущестование файла
        // 3) проверить права на чтение
        // 4) проверить содержимое
        // 5) обработать IOException
        return null;
    }

    public void writeFile(String content, String filePath) throws MorseException {
        // todo: запись файла с созданием дирректории
        // 1) преобразовать путь
        // 2) создать родительские дирректории (*)
        // 3) записать содержимаое с правильными опциями
        // 4) обработать IOException
    }

    public boolean fileExists(String filePath) {
        // todo% проверка существования файла
        return false;
    }

}
