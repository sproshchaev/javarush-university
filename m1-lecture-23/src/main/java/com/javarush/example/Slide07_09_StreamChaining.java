package com.javarush.example;

import java.io.*;

/**
 * Слайды 7-9: Демонстрация цепочек потоков, InputStreamReader и BufferedReader
 */
public class Slide07_09_StreamChaining {

    public static void main(String[] args) {
        System.out.println("=== ЦЕПОЧКИ ПОТОКОВ (Слайд 7) ===");
        demonstrateStreamChaining();

        System.out.println("\n\n=== INPUTSTREAMREADER (Слайд 8) ===");
        demonstrateInputStreamReader();

        System.out.println("\n\n=== BUFFEREDREADER (Слайд 9) ===");
        demonstrateBufferedReader();
    }

    /**
     * Слайд 7: Демонстрация цепочек потоков
     */
    private static void demonstrateStreamChaining() {
        System.out.println("Создаем цепочку потоков для чтения файла:");

        // Имитируем файловые данные в памяти для демонстрации
        String fileContent = "Данные из файла:\nСтрока 1\nСтрока 2\nСтрока 3";
        byte[] fileData = fileContent.getBytes();

        try (InputStream fileStream = new ByteArrayInputStream(fileData);
             BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
             InputStreamReader streamReader = new InputStreamReader(bufferedStream);
             BufferedReader reader = new BufferedReader(streamReader)) {

            System.out.println("Цепочка: ByteArrayInputStream -> BufferedInputStream -> InputStreamReader -> BufferedReader");

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("> " + line);
            }

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Слайд 8: Демонстрация InputStreamReader как моста между потоками
     */
    private static void demonstrateInputStreamReader() {
        System.out.println("InputStreamReader - мост от байтов к символам:");

        String textWithUnicode = "Русский: Привет! Chinese: 你好! Emoji: 🚀";
        byte[] bytes = textWithUnicode.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        try (InputStream byteStream = new ByteArrayInputStream(bytes);
             Reader reader = new InputStreamReader(byteStream, "UTF-8")) {

            System.out.println("Исходные байты: " + bytes.length + " байт");
            System.out.println("Чтение через InputStreamReader:");

            char[] buffer = new char[100];
            int charsRead = reader.read(buffer);
            System.out.println("Результат: " + new String(buffer, 0, charsRead));

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Слайд 9: Демонстрация BufferedReader и его преимуществ
     */
    private static void demonstrateBufferedReader() {
        System.out.println("BufferedReader - буферизация и удобные методы:");

        String multiLineText = "Первая строка текста\nВторая строка текста\nТретья строка текста";

        // Демонстрация readLine()
        try (Reader stringReader = new StringReader(multiLineText);
             BufferedReader bufferedReader = new BufferedReader(stringReader)) {

            System.out.println("Чтение построчно с помощью readLine():");
            String line;
            int lineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Демонстрация производительности
        System.out.println("\nСравнение производительности:");
        try {
            compareReadingPerformance();
        } catch (IOException e) {
            System.err.println("Ошибка при сравнении: " + e.getMessage());
        }
    }

    /**
     * Сравнение скорости чтения с BufferedReader и без него
     */
    private static void compareReadingPerformance() throws IOException {
        // Создаем большой текст для теста
        StringBuilder bigText = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            bigText.append("Строка номер ").append(i).append("\n");
        }

        String text = bigText.toString();

        // Чтение БЕЗ BufferedReader
        long startTime = System.currentTimeMillis();
        try (StringReader reader = new StringReader(text)) {
            int data;
            while ((data = reader.read()) != -1) {
                // Просто читаем все символы
            }
        }
        long timeWithoutBuffer = System.currentTimeMillis() - startTime;

        // Чтение С BufferedReader
        startTime = System.currentTimeMillis();
        try (StringReader reader = new StringReader(text);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            int data;
            while ((data = bufferedReader.read()) != -1) {
                // Читаем через буфер
            }
        }
        long timeWithBuffer = System.currentTimeMillis() - startTime;

        System.out.println("Время чтения без BufferedReader: " + timeWithoutBuffer + " мс");
        System.out.println("Время чтения с BufferedReader: " + timeWithBuffer + " мс");
        System.out.println("Выигрыш в производительности: " + (timeWithoutBuffer - timeWithBuffer) + " мс");
    }
}