package com.javarush.example;

import java.util.Scanner;
import java.io.*;

/**
 * Slide12_CheckedExceptionsDrawbacks - демонстрация недостатков использования checked-исключений.
 * Пример: цепная передача исключений и загромождение API.
 */
public class Slide12_CheckedExceptionsDrawbacks {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Демонстрация недостатков checked-исключений ===");

        while (true) {
            try {
                System.out.print("Введите имя файла: ");
                String fileName = scanner.nextLine();

                // Вызов метода, который использует checked-исключения
                processFileWithChecked(fileName);

                System.out.println("✅ Файл успешно обработан (через checked).");

            } catch (IOException e) {
                System.out.println("❌ ОШИБКА (checked): " + e.getMessage());
            } catch (Exception e) {
                System.out.println("❓ Неизвестная ошибка: " + e.getMessage());
            } finally {
                System.out.println("---");
            }

            System.out.print("Продолжить? (да/нет): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("да") && !answer.equalsIgnoreCase("yes")) {
                break;
            }
        }

        scanner.close();
        System.out.println("✅ Программа завершена. Цепная передача checked-исключений продемонстрирована!");
    }

    /**
     * Метод, который вызывает другие методы — вынужден объявлять throws IOException.
     * @param fileName имя файла
     * @throws IOException если произошла ошибка ввода-вывода
     */
    static void processFileWithChecked(String fileName) throws IOException {
        // Этот метод не знает, что делает readAndValidateFile — но вынужден передавать исключение
        String content = readAndValidateFile(fileName);
        System.out.println("📄 Содержимое: " + content);
    }

    /**
     * Метод, который может выбросить checked-исключение.
     * @param fileName имя файла
     * @return содержимое файла
     * @throws IOException если файл не найден или ошибка чтения
     */
    static String readAndValidateFile(String fileName) throws IOException {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым!"); // unchecked
        }

        if (!fileName.equals("data.txt")) {
            throw new FileNotFoundException("Файл '" + fileName + "' не найден.");
        }

        return "Это содержимое файла data.txt.\nСтрока 1.\nСтрока 2.";
    }

    // ✅ Альтернатива: используем unchecked-исключение — нет необходимости в throws
    static void processFileWithUnchecked(String fileName) {
        try {
            String content = readAndValidateFileUnchecked(fileName);
            System.out.println("📄 Содержимое (unchecked): " + content);
        } catch (RuntimeException e) {
            throw new RuntimeException("Ошибка при обработке файла: " + e.getMessage(), e);
        }
    }

    static String readAndValidateFileUnchecked(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым!");
        }

        if (!fileName.equals("data.txt")) {
            throw new RuntimeException("Файл '" + fileName + "' не найден.");
        }

        return "Это содержимое файла data.txt.\nСтрока 1.\nСтрока 2.";
    }
}
