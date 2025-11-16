package com.javarush.example;

import java.io.*;

/**
 * Слайды 11-13: Демонстрация потоков вывода и буферизации
 */
public class Slide11_13_OutputAndBuffering {

    public static void main(String[] args) {
        System.out.println("=== OUTPUTSTREAM (Слайд 11) ===");
        demonstrateOutputStream();

        System.out.println("\n\n=== WRITER (Слайд 12) ===");
        demonstrateWriter();

        System.out.println("\n\n=== BUFFEREDWRITER (Слайд 13) ===");
        demonstrateBufferedWriter();
    }

    /**
     * Слайд 11: Демонстрация OutputStream и его методов
     */
    private static void demonstrateOutputStream() {
        System.out.println("ByteArrayOutputStream - запись байтов:");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // write(int b) - запись одного байта
            outputStream.write(72);  // 'H'
            outputStream.write(101); // 'e'
            outputStream.write(108); // 'l'
            outputStream.write(108); // 'l'
            outputStream.write(111); // 'o'

            // write(byte[] buffer) - запись массива байт
            byte[] space = " ".getBytes();
            outputStream.write(space);

            // write(byte[] buffer, off, len) - запись части массива
            byte[] fullWord = "JavaRush!".getBytes();
            outputStream.write(fullWord, 0, 4); // "Java"

            // flush() - гарантируем запись
            outputStream.flush();

            // Получаем результат
            byte[] result = outputStream.toByteArray();
            System.out.println("Результат: " + new String(result));

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Слайд 12: Демонстрация Writer и его удобных методов для текста
     */
    private static void demonstrateWriter() {
        System.out.println("StringWriter - удобная работа с текстом:");

        try (StringWriter writer = new StringWriter()) {

            // write(int c) - запись одного символа
            writer.write('П');
            writer.write('р');
            writer.write('и');

            // write(char[] buffer) - запись массива символов
            char[] chars = {'в', 'е', 'т'};
            writer.write(chars);
            writer.write(' ');

            // write(String str) - самый удобный метод для строк!
            writer.write("JavaRush");

            // write(String str, off, len) - запись части строки
            String exclamation = "! Это круто!";
            writer.write(exclamation, 0, 2); // "! "

            // flush() и получение результата
            writer.flush();
            String result = writer.toString();
            System.out.println("Результат: " + result);

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Слайд 13: Демонстрация BufferedWriter и его преимуществ
     */
    private static void demonstrateBufferedWriter() {
        System.out.println("BufferedWriter - буферизация и удобные методы:");

        // Демонстрация newLine() и удобства
        try (StringWriter stringWriter = new StringWriter();
             BufferedWriter writer = new BufferedWriter(stringWriter)) {

            writer.write("Первая строка текста");
            writer.newLine(); // Платформонезависимый перевод строки!

            writer.write("Вторая строка текста");
            writer.newLine();

            writer.write("Третья строка с числами: ");
            for (int i = 1; i <= 3; i++) {
                writer.write(String.valueOf(i));
                writer.write(" ");
            }

            writer.flush(); // Важно: без flush() данные могут не записаться!

            System.out.println("Результат с newLine():");
            System.out.println(stringWriter.toString());

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Сравнение производительности
        System.out.println("\nСравнение производительности записи:");
        try {
            compareWritingPerformance();
        } catch (IOException e) {
            System.err.println("Ошибка при сравнении: " + e.getMessage());
        }
    }

    /**
     * Сравнение скорости записи с BufferedWriter и без него
     */
    private static void compareWritingPerformance() throws IOException {
        int iterations = 10000;
        String text = "Строка для тестирования производительности ";

        // Запись БЕЗ BufferedWriter
        long startTime = System.currentTimeMillis();
        try (StringWriter stringWriter = new StringWriter()) {
            for (int i = 0; i < iterations; i++) {
                stringWriter.write(text + i + "\n");
            }
        }
        long timeWithoutBuffer = System.currentTimeMillis() - startTime;

        // Запись С BufferedWriter
        startTime = System.currentTimeMillis();
        try (StringWriter stringWriter = new StringWriter();
             BufferedWriter bufferedWriter = new BufferedWriter(stringWriter)) {
            for (int i = 0; i < iterations; i++) {
                bufferedWriter.write(text + i);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush(); // Гарантируем запись всех данных
        }
        long timeWithBuffer = System.currentTimeMillis() - startTime;

        System.out.println("Время записи без BufferedWriter: " + timeWithoutBuffer + " мс");
        System.out.println("Время записи с BufferedWriter: " + timeWithBuffer + " мс");
        System.out.println("BufferedWriter быстрее в " +
                String.format("%.1f", (double)timeWithoutBuffer/timeWithBuffer) + " раз!");
    }
}