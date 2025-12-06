package com.javarush.morse.service;

import com.javarush.morse.exception.MorseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileService {

    public String readFile(String filePath) throws MorseException {
        try {
            // 1) преобразовать путь в Path
            Path path = Path.of(filePath);
            // 2) проверить существование файла
            if (!Files.exists(path)) {
                throw new MorseException("Файл не существует " + filePath);
            }
            // 3) проверить права на чтение
            if (!Files.isReadable(path)) {
                throw new MorseException("Нет прав на чтение файла " + filePath);
            }
            // todo 4) проверить содержимое
            return Files.readString(path);
            // 5) обработать IOException
        } catch (IOException e) {
            throw new MorseException("Ошибка чтения файла: " + e.getMessage(), e);
        }
    }

    public void writeFile(String content, String filePath) throws MorseException {

        try {
            // 1) преобразовать путь
            Path path = Path.of(filePath);

            Path parentDir = path.getParent();
            // 2) создать родительские директории (*)
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            // 3) записать содержимое с правильными опциями
            Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            // 4) обработать IOException
            throw new MorseException("Ошибка записи файла: " + e.getMessage(), e);
        }
    }

    public boolean fileExists(String filePath) {
        return Files.exists(Path.of(filePath));
    }

}
