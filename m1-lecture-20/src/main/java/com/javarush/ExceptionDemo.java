package com.javarush;

import java.io.IOException;

public class ExceptionDemo {

    public static void main(String[] args) {

        // NullPointerException (NPE) - ошибка нулевой ссылки
        try {
            String str = null; // "строка";
            System.out.println(str.length()); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Перехватили NPE");
        }

        // IOException - ошибка ввода вывода
        try {
            throw new IOException("Демо IOException");
        } catch (IOException e) {
            System.out.println("Перехватили IOException");
        }

        System.out.println("Точка завершения нашего приложения");
    }

}
