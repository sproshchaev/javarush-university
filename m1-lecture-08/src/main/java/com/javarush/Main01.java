package com.javarush;

/**
 * JavaRush-University
 */
public class Main01 {

    // поля

    // Главный статический метод - точка входа
    public static void main(String[] args) {

        // (1) Стрктура метода
        //        (1)          (2)           (3)        (4)
        // [модификаторы] тип_результата имя_метода(параметры) {
        //    тело метода
        // }
        // (2) Метод - выполняет действие и не возвращает результат - void
        // (3) Функция - выполняет действие и возвращает результат


        sum();

        multiplay();

    } // main

    private static void multiplay() {
        int a = 10;
        int b = 5;
        System.out.println("Умножение a*b= " + (a * b));
    }

    //     (1)     (2) (3) (4)
    public static void sum() {
        int a = 10;
        int b = 5;
        System.out.println("Сумма a+b= " + (a + b));
    }
}
