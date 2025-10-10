package com.javarush;

/**
 * JavaRush-University
 */
public class Main04 {

    // Главный статический метод - точка входа
    public static void main(String[] args) {

        // (1) Стрктура метода
        //        (1)          (2)           (3)        (4)
        // [модификаторы] тип_результата имя_метода(параметр_тип1 параметр_имя1, параметр_тип2 параметр_имя2, ...) {
        //    тело метода
        // }
        // (2) Метод - выполняет действие и не возвращает результат - void
        // (3) Функция - выполняет действие и возвращает результат

        // Передача ссылок в методы

        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8 ,9}
        };

        System.out.println("Массив до вызова метода: ");
        print2DArray(array);

        //

        // Передаем ссылку на массив в метод fill()
        fillArray(array, 8);

        System.out.println("Массив после вызова метода:");
        print2DArray(array);

    } // main

    public static void fillArray(int[][] data, int value) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = value;
            }
        }
    }

    public static void print2DArray(int[][] arr) {
        for (int[] row : arr) {
            for (int num : row) {
                System.out.print(num + ", ");
            }
        }
        System.out.println();
    }

}
