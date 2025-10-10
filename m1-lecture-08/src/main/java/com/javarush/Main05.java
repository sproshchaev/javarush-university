package com.javarush;

/**
 * JavaRush-University
 */
public class Main05 {

    // Главный статический метод - точка входа
    public static void main(String[] args) {

        // (1) Стрктура метода
        //        (1)          (2)           (3)        (4)
        // [модификаторы] тип_результата имя_метода(параметр_тип1 параметр_имя1, параметр_тип2 параметр_имя2, ...) {
        //    тело метода
        // }
        // (2) Метод - выполняет действие и не возвращает результат - void
        // (3) Функция - выполняет действие и возвращает результат

        // Сигнатура метода это:
        // - имя_метода
        // - типы параметров
        // - количество параметров

        printInfo(100);
        printInfo("Строка-строка");
        printInfo(10.1, 20.2);
        printInfo(100.21f, 100);
        printInfo(100, 1f);

    } // main

    // printInfo
    public static void printInfo(int number) {
        int a = 100; // локальная переменная
        System.out.println("Целое число: " + number);
    }

    public static void printInfo(String text) {
        int a = 100; // локальная переменная
        System.out.println("Строка: " + text);
    }

    public static void printInfo(double a, double b) {
        System.out.println("Два числа " + a + " " + b);
    }

    public static void printInfo(float number, int var) {
        System.out.println("число: " + number);
    }

    public static void printInfo(int var, float number) {
        System.out.println("число: " + number);
    }


}
