package com.javarush.example;

import java.io.*;
import java.util.Scanner;

/**
 * Демонстрация работы с внешними ресурсами и важность метода close().
 * Показывает проблему утечки ресурсов и решение через try-catch-finally.
 */
public class Slide08_ExternalResourcesClose {

    public static void main(String[] args) {
        System.out.println("=== Работа с внешними ресурсами и метод close() ===");

        // Демонстрация проблемы утечки ресурсов
        demonstrateResourceLeakProblem();

        System.out.println("\n=== Правильное освобождение ресурсов ===");
        // Показываем корректное использование try-catch-finally
        demonstrateProperResourceClosing();

        System.out.println("\n=== Работа с несколькими ресурсами ===");
        // Демонстрация работы с несколькими ресурсами одновременно
        demonstrateMultipleResources();
    }

    /**
     * Демонстрирует проблему утечки ресурсов при неправильной работе
     */
    public static void demonstrateResourceLeakProblem() {
        System.out.println("1. Проблема утечки ресурсов:");

        FileWriter writer = null;
        try {
            // Создаем файл для записи - захватываем внешний ресурс
            writer = new FileWriter("demo_file.txt");
            writer.write("Это тестовые данные\n");
            writer.write("Вторая строка данных\n");

            System.out.println("Данные успешно записаны в файл");

            // Имитируем исключение в процессе работы
            System.out.println("Имитируем исключение...");
            throw new RuntimeException("Внезапная ошибка в бизнес-логике!");

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Бизнес-ошибка: " + e.getMessage());
            // Обратите внимание: файл НЕ закрывается при таком сценарии!
            System.out.println("ВНИМАНИЕ: Файл не был закрыт из-за исключения!");
        }

        // Проблема: writer.close() никогда не вызывается при исключении
        // Файл остается заблокированным для этой программы
    }

    /**
     * Демонстрирует правильное освобождение ресурсов с помощью finally
     */
    public static void demonstrateProperResourceClosing() {
        System.out.println("2. Корректное закрытие ресурсов (try-catch-finally):");

        FileWriter writer = null;
        try {
            writer = new FileWriter("correct_demo.txt");
            writer.write("Корректный пример работы с файлом\n");

            // Может произойти исключение - но это не страшно
            int a = 10 / 0; // ArithmeticException

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Арифметическая ошибка: " + e.getMessage());
        } finally {
            System.out.println("Блок finally: освобождаем ресурсы");
            // Этот код выполнится ВСЕГДА, независимо от исключений

            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("Ресурс успешно закрыт в блоке finally");
                } catch (IOException e) {
                    System.out.println("Ошибка при закрытии файла: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Демонстрирует работу с несколькими ресурсами
     */
    public static void demonstrateMultipleResources() {
        System.out.println("3. Работа с несколькими ресурсами:");

        FileReader reader = null;
        FileWriter writer = null;
        Scanner scanner = null;

        try {
            // Открываем несколько ресурсов
            reader = new FileReader("source.txt");
            writer = new FileWriter("destination.txt");
            scanner = new Scanner(reader);

            System.out.println("Все ресурсы открыты, начинаем обработку...");

            // Читаем из одного файла и пишем в другой
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                writer.write("Обработано: " + line + "\n");
            }

            System.out.println("Обработка завершена успешно");

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } finally {
            System.out.println("Блок finally: закрываем все ресурсы");

            // Важно: закрывать ресурсы в порядке, обратном открытию
            if (scanner != null) {
                scanner.close();
                System.out.println("Сканер закрыт");
            }

            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("Writer закрыт");
                } catch (IOException e) {
                    System.out.println("Ошибка при закрытии writer: " + e.getMessage());
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("Reader закрыт");
                } catch (IOException e) {
                    System.out.println("Ошибка при закрытии reader: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Вспомогательный метод для создания тестового файла
     */
    private static void createTestFile() {
        try (FileWriter fw = new FileWriter("source.txt")) {
            fw.write("Первая строка\n");
            fw.write("Вторая строка\n");
            fw.write("Третья строка\n");
        } catch (IOException e) {
            System.out.println("Не удалось создать тестовый файл: " + e.getMessage());
        }
    }

    // Статический блок для создания тестовых файлов при загрузке класса
    static {
        createTestFile();
    }
}