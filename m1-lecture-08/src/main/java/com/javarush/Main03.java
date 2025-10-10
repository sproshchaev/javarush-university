package com.javarush;

import java.util.Arrays;

/**
 * JavaRush-University
 */
public class Main03 {

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

        int[] numbers = {10, 20, 30};

        System.out.println("Массив до вызова метода:");
        printArray(numbers);

        // Передаем массив в метод и там его изменяем
        modifyArray(numbers);

        printArray(numbers);
        System.out.println(Arrays.toString(numbers));

    } // main

    // numbers->  {10, 20, 30} <- array

    public static void modifyArray(int[] array ) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * 2;
        }
        System.out.println("Массив был изменен внутри метода modifyArray()");
    }



    public static void printArray(int[] numbers) {
        for (int num : numbers) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

}
