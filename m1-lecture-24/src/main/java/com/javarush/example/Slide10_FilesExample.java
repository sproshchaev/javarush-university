package com.javarush.example;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class Slide10_FilesExample {
    public static void main(String[] args) {
        try {
            // Базовые пути для работы
            Path baseDir = Path.of("test_files_example");
            Path testFile = baseDir.resolve("example.txt");
            Path copyFile = baseDir.resolve("example_copy.txt");
            Path moveFile = baseDir.resolve("example_moved.txt");

            System.out.println("=== ДЕМОНСТРАЦИЯ КЛАССА FILES ===\n");

            // 1. СОЗДАНИЕ ДИРЕКТОРИЙ И ФАЙЛОВ (Слайд 10)
            System.out.println("1. СОЗДАНИЕ ДИРЕКТОРИЙ И ФАЙЛОВ:");
            Files.createDirectories(baseDir);
            System.out.println("Создана директория: " + baseDir);

            if (!Files.exists(testFile)) {
                Files.createFile(testFile);
                System.out.println("Создан файл: " + testFile);
            }

            // 2. ЗАПИСЬ ДАННЫХ (Слайд 11)
            System.out.println("\n2. ЗАПИСЬ И ЧТЕНИЕ ДАННЫХ:");
            String content = "Привет, JavaRush!\nЭто пример работы с Files.\nТретья строка.";
            Files.writeString(testFile, content);
            System.out.println("Данные записаны в файл");

            // 3. ЧТЕНИЕ ДАННЫХ РАЗНЫМИ СПОСОБАМИ (Слайд 11)
            System.out.println("\n3. ЧТЕНИЕ ДАННЫХ:");

            // Чтение всей строкой
            String fullText = Files.readString(testFile);
            System.out.println("readString(): " + fullText.split("\n")[0] + "...");

            // Чтение по строкам
            List<String> lines = Files.readAllLines(testFile);
            System.out.println("readAllLines(): " + lines.size() + " строк");
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("  Строка " + (i + 1) + ": " + lines.get(i));
            }

            // 4. ПРОВЕРКИ СВОЙСТВ (Слайд 11)
            System.out.println("\n4. ПРОВЕРКИ СВОЙСТВ:");
            System.out.println("exists(): " + Files.exists(testFile));
            System.out.println("isRegularFile(): " + Files.isRegularFile(testFile));
            System.out.println("isDirectory(): " + Files.isDirectory(testFile));
            System.out.println("size(): " + Files.size(testFile) + " байт");

            // 5. ОПЕРАЦИИ С ФАЙЛАМИ (Слайд 10)
            System.out.println("\n5. ОПЕРАЦИИ С ФАЙЛАМИ:");

            // Копирование
            Files.copy(testFile, copyFile);
            System.out.println("Файл скопирован: " + copyFile);

            // Перемещение
            Files.move(copyFile, moveFile);
            System.out.println("Файл перемещен: " + moveFile);

            // 6. ПОЛУЧЕНИЕ СОДЕРЖИМОГО ДИРЕКТОРИИ (Слайд 12)
            System.out.println("\n6. СОДЕРЖИМОЕ ДИРЕКТОРИИ:");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(baseDir)) {
                for (Path entry : stream) {
                    String type = Files.isDirectory(entry) ? "DIR " : "FILE";
                    String size = Files.isRegularFile(entry) ?
                            " (" + Files.size(entry) + " байт)" : "";
                    System.out.println("  " + type + " " + entry.getFileName() + size);
                }
            }

            // 7. УДАЛЕНИЕ (очистка)
            System.out.println("\n7. ОЧИСТКА (удаление созданных файлов):");
            Files.deleteIfExists(moveFile);
            Files.deleteIfExists(testFile);
            Files.deleteIfExists(baseDir);
            System.out.println("Временные файлы удалены");

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
            e.printStackTrace();
        }
    }
}