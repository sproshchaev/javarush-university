package com.javarush;

import java.util.TreeMap;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {

        // Примитив:
        // ---------
        // byte -> Byte
        // short -> Short
        // int -> Integer
        // long -> Long
        int x = 1;
        int x2 = 2;
        System.out.println("Равенство: " + (x == x2 ));

        // Обертка
        Integer y = 1; // Автоупакова

        System.out.println("Примитив: " + x);
        System.out.println("Обертка: " + y);

        // Можно сравнивать
        System.out.println("Равны? " + (x == y)); // Автораспаковки: Integer -> int

        // Класс Integer

        // Константы Integer
        System.out.println("MAX: " + Integer.MAX_VALUE); // 2 147 483 647
        System.out.println("MIN: " + Integer.MIN_VALUE); // -2 147 483 648

        // Методы класса Integer
        int num = 255;
        System.out.println("Hex: " + Integer.toHexString(num)); // Шестнадцатиричное представление 255:   ff
        System.out.println("Binary: " + Integer.toBinaryString(num)); // Двоичное представление 255: 11111111

        System.out.println(Integer.valueOf(num)); // Преобразование примитива в объект: int -> Integer
        Integer intObj = Integer.parseInt("1000"); // String -> Integer
        System.out.println("Объект: " + intObj); // Объект : 1000

        // Integer.valueOf("100w"); // "100" -> Integer: ошибка преобразования NumberFormatException
        System.out.println("Объект: " + Integer.valueOf("100")); // "100" -> Integer: Объект: 100

        // Класс Character
        char с = 'A';
        char d = '5';
        char e = ' ';
        char f = 'f';

        System.out.println("с is letter: " + Character.isLetter(с)); // .isLetter true
        System.out.println("d is letter: " + Character.isLetter(d)); // .isLetter false
        System.out.println("e is whitespace: " + Character.isWhitespace(e)); // .isWhitespace  true

        System.out.println("с toLowerCase: " + Character.toLowerCase(с)); // 'A' -> 'a'
        System.out.println("f toUpperCase: " + Character.toUpperCase(f)); // 'f' -> 'F'

        System.out.println("d isDigit: " + Character.isDigit(d)); // true



    }

}
