package com.javarush.morse.service;

import com.javarush.morse.exception.MorseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с вводом и выводом (IO)
 */
public class FileManager {

    private static final String BOM = "";

    public List<String> readLines(String fileName) throws MorseException {
        Path path = toPath(fileName);
        if (!Files.exists(path)) {
            throw new MorseException("Файл не найден: " + path.toAbsolutePath());
        }
        try {
            List<String> lines = new ArrayList<>(Files.readAllLines(path));
            if (!lines.isEmpty() && lines.get(0).startsWith(BOM)) {
                lines.set(0, lines.get(0).substring(1));
            }
            return lines;
        } catch (IOException e) {
            throw new MorseException("Не удалось прочитать файл " + fileName + ", нужна кодировка UTF-8", e);
        }
    }

    public void writeLines(String fileName, List<String> lines) throws MorseException {
        Path path = toPath(fileName).toAbsolutePath();
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, lines);
        } catch (IOException e) {
            throw new MorseException("Не удалось записать файл " + fileName, e);
        }
    }

    public void checkDifferent(String input, String output) throws MorseException {
        Path in = toPath(input);
        Path out = toPath(output);
        try {
            if (Files.exists(in) && Files.exists(out) && Files.isSameFile(in, out)) {
                throw new MorseException("Результат нельзя записать в исходный файл");
            }
        } catch (IOException e) {
            throw new MorseException("Не удалось проверить файл " + output, e);
        }
    }

    private Path toPath(String fileName) throws MorseException {
        if (fileName.isEmpty()) {
            throw new MorseException("Путь к файлу не указан");
        }
        try {
            return Path.of(fileName);
        } catch (InvalidPathException e) {
            throw new MorseException("Недопустимый путь: " + fileName, e);
        }
    }

}
