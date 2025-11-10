package com.javarush.example;

import java.util.Scanner;
import java.io.*;

/**
 * Slide10_CheckedVsUnchecked - демонстрация разницы между проверяемыми (checked)
 * и непроверяемыми (unchecked) исключениями.
 * Пример: работа с файлом и пользовательским вводом.
 */
public class Slide10_CheckedVsUnchecked {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Работа с checked и unchecked исключениями ===");

        while (true) {
            try {
                System.out.print("Введите имя файла для чтения: ");
                String fileName = scanner.nextLine();

                // Checked exception — компилятор требует обработки
                readFile(fileName);

                System.out.println("✅ Файл успешно прочитан.");

            } catch (java.io.FileNotFoundException e) {
                System.out.println("❌ ОШИБКА (checked): Файл не найден — " + e.getMessage());
            } catch (java.io.IOException e) {
                System.out.println("💥 ОШИБКА (checked): Ошибка ввода-вывода — " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("🚨 ОШИБКА (unchecked): " + e.getMessage());
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
        System.out.println("✅ Программа завершена. Все типы исключений продемонстрированы!");
    }

    /**
     * Метод для чтения файла — может выбросить checked-исключения.
     * @param fileName имя файла
     * @throws FileNotFoundException если файл не существует
     * @throws IOException если произошла ошибка ввода-вывода
     */
    static void readFile(String fileName) throws java.io.FileNotFoundException, java.io.IOException {
        // Для демонстрации — просто проверим имя файла
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым!"); // unchecked
        }

        // Имитация работы с файлом
        if (!fileName.equals("data.txt")) {
            throw new java.io.FileNotFoundException("Файл '" + fileName + "' не найден.");
        }

        System.out.println("📖 Читаю содержимое файла: " + fileName);
        // Здесь мог бы быть реальный код чтения файла...
    }
}