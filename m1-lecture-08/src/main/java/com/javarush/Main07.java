package com.javarush;

/**
 * JavaRush-University
 */
public class Main07 {

    // Главный статический метод - точка входа
    public static void main(String[] args) {

        // (1) Стрктура функции
        //        (1)          (2)           (3)        (4)
        // [модификаторы] тип_результата имя_метода(параметр_тип1 параметр_имя1, параметр_тип2 параметр_имя2, ...) {
        //    тело метода
        // }
        // (2) Метод - выполняет действие и не возвращает результат - void = пусто, ничего
        // (3) Функция - выполняет действие и возвращает результат (!)
        
        int a = 100;
        int b = 200;

        sumMethod(a, b);
        System.out.println("sumFunc(a, b) =" + sumFunc(a, b));

        int[] array = new int[3];
        array[0] = sumFunc(100, 200);
        // array[1] = sumMethod(a, b); - не работает!

        int[] array2 = new int[sumFunc(100, 200)];

        sumMethod(sumFunc(100, 200), 100);

    } // main

    // Метод
    public static void sumMethod(int a, int b) {
        System.out.println(a + b); // сложение + печать = 2 ответственности
    }

    // Функция
    public static int sumFunc(int a, int b) {
        int result = a + b;
        // System.out.println(result); // не надо! Единственная ответственность - сложение
        return result;
    }



}
