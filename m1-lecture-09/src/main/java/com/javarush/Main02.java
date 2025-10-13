package com.javarush;

/**
 * JavaRush-University
 */
public class Main02 {

    // Экранирование
    public static void main(String[] args) {

        // Двойная кавычка внутри строки
        // System.out.println("Он сказал: "Привет!""); // java: ')' expected
        System.out.println("Он сказал: \"Привет!\""); // Он сказал: "Привет!"

        // Новая строка
        // System.out.println("Первая строка");
        // System.out.println("Вторая строка");

        System.out.println("Первая строка\nВторая строка");

        System.out.print("Перевод строки\n"); // без .println

        // Одинарная кавычка '
        char c = '\'';
        System.out.println('\'');

    }

}
