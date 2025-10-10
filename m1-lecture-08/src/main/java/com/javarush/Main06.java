package com.javarush;

/**
 * JavaRush-University
 */
public class Main06 {

    // Главный статический метод - точка входа
    public static void main(String[] args) {

        // (1) Стрктура метода
        //        (1)          (2)           (3)        (4)
        // [модификаторы] тип_результата имя_метода(параметр_тип1 параметр_имя1, параметр_тип2 параметр_имя2, ...) {
        //    тело метода
        // }
        // (2) Метод - выполняет действие и не возвращает результат - void
        // (3) Функция - выполняет действие и возвращает результат


        // return - завершает работу метода

        int[] array = new int[]{1,2,3};
        sum(array);


    } // main

    private static void sum(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 2) {
                return;
            }
            System.out.println(array[i]);
        }
    }


}
