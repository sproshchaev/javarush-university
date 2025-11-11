package com.javarush;

import java.io.IOException;

/**
 * Демо throws
 */
public class ThrowsDemo {

    public static void main(String[] args) { // throws IOException { // пробросить наверх 2-ой вариант

        try { // <- обработка по месту 1-ый вариант
            readFile("Имя_фала.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Метод объявляет что может выбросить checked-исключение
    static void readFile(String fileName) throws IOException { // пробросить наверх 2-ой вариант
        if (!fileName.equals("data.txt")) {
            // try { // <- обработка по месту 1-ый вариант
                throw new IOException("Файл " + fileName + " не найден!"); // есть checked-исключение IOException
            // }  catch (IOException e) {
                // System.out.println("Ошибка присутствия файл");
            // }
        }
        System.out.println("Файл " + fileName + " успешно прочитан!");
    }

}
