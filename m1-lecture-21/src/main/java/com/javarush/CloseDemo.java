package com.javarush;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Пример закрытия внешнего ресурса через метод .close() при возникновении исключений
 */
public class CloseDemo {

    public static void main(String[] args) throws IOException {

        FileWriter fw = null;

        // Откроем файл (захват ресурса)
        try {

            fw = new FileWriter("test.txt");
            fw.write("Hello, World!");

            // Исключение
            throw new IOException();

        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом " + e.getMessage());
        } finally {
            // Выполняется всегда
            if (fw != null) {
                //try {
                    fw.close(); // Закрытие файла-ресурса .close()
                //} catch (IOException e) {}
            }
        }

    }

}
