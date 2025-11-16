package com.javarush.example;

import java.io.*;

/**
 * Слайды 14-19: Демонстрация потоков в памяти - ByteArrayInputStream и ByteArrayOutputStream
 */
public class Slide14_19_InMemoryStreams {

    public static void main(String[] args) {
        System.out.println("=== BYTEARRAYINPUTSTREAM (Слайды 14-16) ===");
        demonstrateByteArrayInputStream();

        System.out.println("\n\n=== BYTEARRAYOUTPUTSTREAM (Слайды 17-19) ===");
        demonstrateByteArrayOutputStream();

        System.out.println("\n\n=== ПРАКТИЧЕСКИЙ ПРИМЕР ===");
        practicalExample();
    }

    /**
     * Слайды 14-16: Демонстрация ByteArrayInputStream
     */
    private static void demonstrateByteArrayInputStream() {
        System.out.println("ByteArrayInputStream - чтение из массива байтов:");

        String text = "Hello JavaRush!";
        byte[] bytes = text.getBytes();

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {

            System.out.println("Доступно байтов: " + inputStream.available());

            // read() - чтение по одному байту
            System.out.print("Прочитано: ");
            int data;
            while ((data = inputStream.read()) != -1) {
                System.out.print((char) data);
            }

            System.out.println("\nОсталось байтов: " + inputStream.available());

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Демонстрация mark() и reset()
        System.out.println("\n--- Демонстрация mark() и reset() ---");
        try (ByteArrayInputStream stream = new ByteArrayInputStream("ABCDEF".getBytes())) {

            stream.read(); // 'A'
            stream.read(); // 'B'

            stream.mark(0); // Ставим метку после 'B'

            System.out.print("После mark(): ");
            System.out.print((char) stream.read()); // 'C'
            System.out.print((char) stream.read()); // 'D'

            stream.reset(); // Возвращаемся к метке

            System.out.print("\nПосле reset(): ");
            System.out.print((char) stream.read()); // 'C'
            System.out.print((char) stream.read()); // 'D'

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Слайды 17-19: Демонстрация ByteArrayOutputStream
     */
    private static void demonstrateByteArrayOutputStream() {
        System.out.println("ByteArrayOutputStream - запись в массив байтов:");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            // write(int b) - запись одного байта
            outputStream.write(72);  // 'H'
            outputStream.write(101); // 'e'
            outputStream.write(108); // 'l'
            outputStream.write(108); // 'l'
            outputStream.write(111); // 'o'

            // write(byte[] b) - запись массива
            outputStream.write(" World".getBytes());

            // Получение данных разными способами
            byte[] byteArray = outputStream.toByteArray();
            String stringResult = outputStream.toString();

            System.out.println("Как массив: " + new String(byteArray));
            System.out.println("Как строка: " + stringResult);
            System.out.println("Размер данных: " + outputStream.size());

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Демонстрация записи части массива
        System.out.println("\n--- Запись части массива ---");
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {

            byte[] fullData = "JavaProgramming".getBytes();
            stream.write(fullData, 0, 4); // Только "Java"

            System.out.println("Результат: " + stream.toString());

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Практический пример использования потоков в памяти
     */
    private static void practicalExample() {
        System.out.println("Практический пример: преобразование данных через память");

        try {
            // Исходные данные - используем латиницу для демонстрации
            String sourceData = "data for processing";
            System.out.println("Исходные данные: " + sourceData);

            // Шаг 1: Записываем данные в ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(sourceData.getBytes());

            // Шаг 2: Получаем массив байтов
            byte[] processedBytes = outputStream.toByteArray();

            // Шаг 3: Читаем данные из ByteArrayInputStream
            ByteArrayInputStream inputStream = new ByteArrayInputStream(processedBytes);

            // Шаг 4: Обрабатываем данные (преобразуем в верхний регистр)
            StringBuilder result = new StringBuilder();
            int data;
            while ((data = inputStream.read()) != -1) {
                result.append(Character.toUpperCase((char) data));
            }

            System.out.println("Обработанные данные: " + result.toString());

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}