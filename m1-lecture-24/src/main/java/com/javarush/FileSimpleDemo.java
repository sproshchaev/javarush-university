package com.javarush;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSimpleDemo {

    public static void main(String[] args) throws IOException {

        Path testDir = Path.of("test_dir");
        Path testFile = Path.of("text.txt");

        // Создаем директорию и файл
        Files.createDirectories(testDir);
        Files.createFile(testFile);

        System.out.println("Создана директория: " + testDir);
        System.out.println("Создан файл: " + testFile);
        System.out.println("Файл существует: " + Files.exists(testFile));

        // todo  продолжить приведение примеров для остальных методов из слайда №10! :)

    }

}
