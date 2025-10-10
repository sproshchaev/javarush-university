package com.javarush;

/**
 * JavaRush-University
 */
public class Main02 {

    // Главный статический метод - точка входа
    public static void main(String[] args) {

        // (1) Стрктура метода
        //        (1)          (2)           (3)        (4)
        // [модификаторы] тип_результата имя_метода(параметр_тип1 параметр_имя1, параметр_тип2 параметр_имя2, ...) {
        //    тело метода
        // }
        // (2) Метод - выполняет действие и не возвращает результат - void
        // (3) Функция - выполняет действие и возвращает результат

        int a = 10;
        int b = 15;
        sum(a, b);

        a = 100;
        b = 150;
        multiplay(a, b);

        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        sumArray(array);


    } // main

    public static void sumArray(int[] array) {
        int val1 = array[0];
        int val2 = array[1];

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        System.out.println("Сумма массива = " + sum);
    }

    //     (1)     (2) (3)      (4)
    public static void sum(int value1, int value2) {
        int a = 10;
        int b = 5;
        System.out.println("Сумма a+b= " + (value1 + value2));
    }

    private static void multiplay(int a, int b) {
//        int a = 10;
//        int b = 5;
        System.out.println("Умножение a*b= " + (a * b));
    }


}
