package com.javarush;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Пример закрытия внешнего ресурса через try-with-resources при возникновении исключений
 */
public class TryWithResourcesDemo {

    public static void main(String[] args) throws IOException {

        // Откроем файл (захват ресурса)
        try (FileWriter fw = new FileWriter("test.txt")) {

            fw.write("Hello, World!");

            // Исключение
            throw new IOException();

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом " + e.getMessage());
        }

        // Несколько ресурсов
        try (FileReader fr = new FileReader("source.txt");
             FileWriter fw = new FileWriter("copy.txt")) {

            // Выполняем копирование (тема будущих вебинаров)
            int character;
            while ((character = fr.read()) != -1) {
                fw.write(character);
            }
            System.out.println("Файл скопирован!");
        } //catch () {
            // можем не писать
        // }




    }

}
