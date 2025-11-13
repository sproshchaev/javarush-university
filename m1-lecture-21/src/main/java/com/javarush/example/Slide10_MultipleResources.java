package com.javarush.example;

import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

/**
 * Демонстрация работы с несколькими ресурсами в операторе try-with-resources.
 * Показывает объявление и автоматическое закрытие нескольких ресурсов одновременно.
 */
public class Slide10_MultipleResources {

    public static void main(String[] args) {

        System.out.println("=== Несколько ресурсов в try-with-resources ===");

        // Базовый пример с двумя файлами
        demonstrateTwoResources();

        System.out.println("\n=== Три ресурса разных типов ===");
        demonstrateThreeDifferentResources();

        System.out.println("\n=== Копирование с буферизацией ===");
        demonstrateBufferedCopy();

        System.out.println("\n=== Порядок закрытия ресурсов ===");
        demonstrateClosingOrder();
    }

    /**
     * Демонстрирует работу с двумя ресурсами (чтение + запись)
     */
    public static void demonstrateTwoResources() {
        System.out.println("1. Два ресурса - копирование файла:");

        // Создаем исходный файл
        createSourceFile("file1.txt", "Первая строка\nВторая строка\nТретья строка");

        try (FileReader reader = new FileReader("file1.txt");
             FileWriter writer = new FileWriter("file1_copy.txt")) {

            System.out.println("  Ресурсы открыты: FileReader и FileWriter");

            // Простое копирование посимвольно
            int charsCopied = 0;
            int ch;
            while ((ch = reader.read()) != -1) {
                writer.write(ch);
                charsCopied++;
            }

            System.out.println("  Скопировано символов: " + charsCopied);
            System.out.println("  Оба ресурса будут автоматически закрыты");

        } catch (IOException e) {
            System.out.println("  Ошибка при копировании: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует работу с тремя ресурсами разных типов
     */
    public static void demonstrateThreeDifferentResources() {
        System.out.println("2. Три ресурса разных типов:");

        createSourceFile("data.txt", "Java\nPython\nJavaScript\nC++\nGo");

        try (FileInputStream fis = new FileInputStream("data.txt");
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
             BufferedReader reader = new BufferedReader(isr)) {

            System.out.println("  Открыты: FileInputStream, InputStreamReader, BufferedReader");

            System.out.println("  Содержимое файла:");
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("    " + lineNumber + ": " + line);
                lineNumber++;
            }

            System.out.println("  Все три ресурса закроются автоматически");

        } catch (IOException e) {
            System.out.println("  Ошибка: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует эффективное копирование с буферизацией
     */
    public static void demonstrateBufferedCopy() {
        System.out.println("3. Эффективное копирование с буферизацией:");

        createSourceFile("large.txt", "Это содержимое большого файла\n".repeat(10));

        try (FileInputStream input = new FileInputStream("large.txt");
             BufferedInputStream bufferedInput = new BufferedInputStream(input);
             FileOutputStream output = new FileOutputStream("large_copy.txt");
             BufferedOutputStream bufferedOutput = new BufferedOutputStream(output)) {

            System.out.println("  Открыты: 4 ресурса (входной/выходной потоки с буферизацией)");

            byte[] buffer = new byte[1024];
            int bytesRead;
            int totalBytes = 0;

            while ((bytesRead = bufferedInput.read(buffer)) != -1) {
                bufferedOutput.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;
            }

            // Не забываем сбросить буфер
            bufferedOutput.flush();

            System.out.println("  Скопировано байт: " + totalBytes);
            System.out.println("  Все 4 ресурса будут закрыты автоматически");

        } catch (IOException e) {
            System.out.println("  Ошибка при копировании: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует порядок закрытия ресурсов
     */
    public static void demonstrateClosingOrder() {
        System.out.println("4. Порядок закрытия ресурсов:");

        try (TestResource resource1 = new TestResource("Ресурс 1");
             TestResource resource2 = new TestResource("Ресурс 2");
             TestResource resource3 = new TestResource("Ресурс 3")) {

            System.out.println("  Все ресурсы созданы, работаем...");
            System.out.println("  При выходе из блока ресурсы закроются в ОБРАТНОМ порядке:");

        } catch (Exception e) {
            System.out.println("  Ошибка: " + e.getMessage());
        }
    }

    /**
     * Вспомогательный метод для создания тестовых файлов
     */
    private static void createSourceFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Тестовый ресурс для демонстрации порядка закрытия
     */
    static class TestResource implements AutoCloseable {
        private String name;

        public TestResource(String name) {
            this.name = name;
            System.out.println("  Создан: " + name);
        }

        @Override
        public void close() throws Exception {
            System.out.println("  Закрыт: " + name);
        }
    }
}