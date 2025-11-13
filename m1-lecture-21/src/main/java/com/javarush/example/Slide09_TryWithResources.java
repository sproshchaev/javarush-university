package com.javarush.example;

import java.io.*;
import java.util.Scanner;

/**
 * Демонстрация оператора try-with-resources (Java 7+).
 * Показывает автоматическое управление ресурсами и сравнение
 * со старым подходом (try-catch-finally).
 */
public class Slide09_TryWithResources {

    public static void main(String[] args) {
        System.out.println("=== Try-with-resources (автоматическое закрытие) ===");

        // Сравнение старого и нового подхода
        compareOldAndNewApproach();

        System.out.println("\n=== Работа с несколькими ресурсами ===");
        demonstrateMultipleResources();

        System.out.println("\n=== Обработка исключений в try-with-resources ===");
        demonstrateExceptionHandling();
    }

    /**
     * Сравнивает старый подход (try-finally) и новый (try-with-resources)
     */
    public static void compareOldAndNewApproach() {
        System.out.println("1. Сравнение подходов:");

        // Старый подход (до Java 7) - МНОГО КОДА
        System.out.println("а) Старый подход (try-finally):");
        oldStyleFileWriting();

        // Новый подход (Java 7+) - КОРОТКО И ПОНЯТНО
        System.out.println("\nб) Новый подход (try-with-resources):");
        newStyleFileWriting();
    }

    /**
     * Старый способ работы с ресурсами (до Java 7)
     */
    public static void oldStyleFileWriting() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("old_style.txt");
            writer.write("Старый способ: много шаблонного кода\n");
            System.out.println("  Файл записан (старый способ)");
        } catch (IOException e) {
            System.out.println("  Ошибка записи: " + e.getMessage());
        } finally {
            // Обязательно нужно не забыть закрыть ресурс
            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("  Ресурс закрыт вручную в finally");
                } catch (IOException e) {
                    System.out.println("  Ошибка при закрытии: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Новый способ работы с ресурсами (Java 7+)
     */
    public static void newStyleFileWriting() {
        // Ресурс объявляется в круглых скобках после try
        // Автоматически закрывается при выходе из блока try
        try (FileWriter writer = new FileWriter("new_style.txt")) {
            writer.write("Новый способ: компактно и надежно\n");
            System.out.println("  Файл записан (новый способ)");
        } catch (IOException e) {
            System.out.println("  Ошибка записи: " + e.getMessage());
        }
        // Файл УЖЕ автоматически закрыт - не нужно писать finally!
    }

    /**
     * Демонстрирует работу с несколькими ресурсами в одном операторе
     */
    public static void demonstrateMultipleResources() {
        System.out.println("2. Несколько ресурсов в одном операторе:");

        // Создаем исходный файл для чтения
        createSourceFile();

        // Обратите внимание: несколько ресурсов в одном операторе try
        // Закрываются в порядке, ОБРАТНОМ объявлению
        try (FileReader reader = new FileReader("source.txt");
             FileWriter writer = new FileWriter("output.txt");
             Scanner scanner = new Scanner(reader)) {

            System.out.println("  Все ресурсы открыты автоматически");

            int lineCount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                writer.write("[" + (++lineCount) + "] " + line + "\n");
            }

            System.out.println("  Обработано строк: " + lineCount);
            System.out.println("  Все ресурсы будут закрыты автоматически");

        } catch (IOException e) {
            System.out.println("  Ошибка: " + e.getMessage());
        }
        // Все три ресурса (reader, writer, scanner) уже закрыты!
    }

    /**
     * Демонстрирует обработку исключений в try-with-resources
     */
    public static void demonstrateExceptionHandling() {
        System.out.println("3. Исключения и try-with-resources:");

        try (ProblematicResource resource = new ProblematicResource()) {
            System.out.println("  Работаем с ресурсом...");
            resource.doSomething();

            // Имитируем исключение во время работы
            throw new RuntimeException("Внезапная ошибка в бизнес-логике!");

        } catch (Exception e) {
            System.out.println("  Поймано исключение: " + e.getMessage());

            // Показываем suppressed exceptions (подавленные исключения)
            Throwable[] suppressed = e.getSuppressed();
            if (suppressed.length > 0) {
                System.out.println("  Подавленные исключения (из close()):");
                for (Throwable t : suppressed) {
                    System.out.println("    - " + t.getMessage());
                }
            }
        }
        // Ресурс БУДЕТ закрыт, даже если было исключение!
    }

    /**
     * Вспомогательный метод для создания тестового файла
     */
    private static void createSourceFile() {
        try (FileWriter fw = new FileWriter("source.txt")) {
            fw.write("Строка один\n");
            fw.write("Строка два\n");
            fw.write("Строка три\n");
        } catch (IOException e) {
            System.out.println("Не удалось создать тестовый файл: " + e.getMessage());
        }
    }

    /**
     * Вспомогательный класс, который имитирует проблемный ресурс
     * с исключениями при работе и при закрытии
     */
    static class ProblematicResource implements AutoCloseable {
        public void doSomething() {
            System.out.println("  Ресурс выполняет полезную работу");
        }

        @Override
        public void close() throws Exception {
            System.out.println("  Ресурс закрывается...");
            // Имитируем исключение при закрытии
            throw new IllegalStateException("Ошибка при закрытии ресурса!");
        }
    }
}