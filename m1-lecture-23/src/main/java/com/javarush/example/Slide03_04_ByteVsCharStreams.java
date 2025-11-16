package com.javarush.example;

import java.io.*;

/**
 * Слайд 3-4: Демонстрация базовых потоков ввода-вывода и различий между байтовыми и символьными потоками.
 */
public class Slide03_04_ByteVsCharStreams {

    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ БАЗОВЫХ ПОТОКОВ (Слайд 3) ===");
        demonstrateBasicStreams();

        System.out.println("\n\n=== БАЙТОВЫЕ VS СИМВОЛЬНЫЕ ПОТОКИ (Слайд 4) ===");
        demonstrateByteVsCharStreams();
    }

    /**
     * Простая демонстрация, показывающая, что System.in - это InputStream (входной поток),
     * а System.out - это PrintStream (наследник OutputStream, выходной поток).
     */
    private static void demonstrateBasicStreams() {
        System.out.println("\n--- Базовые потоки System.in и System.out ---");

        System.out.print("Введите один символ: ");

        try {
            // System.in - ВХОДЯЩИЙ поток. Читаем один БАЙТ.
            int byteFromInput = System.in.read();

            // System.out - ИСХОДЯЩИЙ поток. Выводим результат.
            System.out.println("Код прочитанного байта: " + byteFromInput);
            System.out.println("Прочитанный символ: " + (char) byteFromInput);
            System.out.println("Это был ВХОДЯЩИЙ поток (InputStream) в действии!");

            // "Съедаем" оставшийся в потоке символ новой строки, если он есть
            System.in.skip(System.in.available());

        } catch (IOException e) {
            System.err.println("Ошибка при чтении из консоли: " + e.getMessage());
        }
    }

    /**
     * Демонстрация ключевых различий между байтовыми и символьными потоками.
     * Показывает, что байтовые потоки не подходят для чтения текста с Unicode-символами.
     */
    private static void demonstrateByteVsCharStreams() {
        String russianText = "Привет JavaRush! 🚀";
        System.out.println("Исходный текст: " + russianText);

        // Преобразуем текст в массив байтов в кодировке UTF-8
        byte[] textBytes = russianText.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        System.out.println("\n--- Чтение через ByteArrayInputStream (байтовый поток) ---");
        System.out.println("Читаем побайтово и пытаемся вывести как символы:");

        try (InputStream inputStream = new ByteArrayInputStream(textBytes)) {
            int byteData;
            while ((byteData = inputStream.read()) != -1) {
                // Выводим байт и пытаемся интерпретировать его как символ
                System.out.print("(" + byteData + "->'" + (char) byteData + "') ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\nВидите проблему? Кириллица, иероглифы и эмодзи 'сломались'!");
        System.out.println("Причина: один символ ≠ один байт в UTF-8.");

        System.out.println("\n--- Чтение через InputStreamReader (символьный поток) ---");
        System.out.println("Читаем готовые символы:");

        try (InputStreamReader reader = new InputStreamReader(
                new ByteArrayInputStream(textBytes),
                java.nio.charset.StandardCharsets.UTF_8)) {

            int charData;
            while ((charData = reader.read()) != -1) {
                System.out.print((char) charData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n✅ Все символы сохранились правильно!");
        System.out.println("Reader сам заботится о кодировке и собирает символы из байтов.");
    }
}