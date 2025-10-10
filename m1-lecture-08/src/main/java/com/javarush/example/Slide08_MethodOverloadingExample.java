package com.javarush.example;

/**
 * Пример из слайда 8: "Перегрузка методов"
 *
 * Перегрузка — это объявление нескольких методов с одинаковым именем,
 * но разными сигнатурами (типами и/или количеством параметров).
 *
 * Важно: сигнатура не включает возвращаемый тип!
 */
public class Slide08_MethodOverloadingExample {

    public static void main(String[] args) {
        System.out.println("=== Перегрузка методов ===");

        // 👇 Вызов разных версий метода printInfo()
        printInfo(42);                   // int
        printInfo("Привет");             // String
        printInfo(3.14, 2.71);           // double, double
        printInfo(new int[]{1, 2, 3});   // int[]

        System.out.println("\n--- Демонстрация: сигнатура зависит от типов параметров ---");
        System.out.println("Методы с одинаковыми сигнатурами НЕЛЬЗЯ объявить — компилятор не пропустит.");
    }

    /**
     * Метод printInfo — перегружен для разных типов.
     */

    public static void printInfo(int number) {
        System.out.println("Целое число: " + number);
    }

    public static void printInfo(String text) {
        System.out.println("Строка: \"" + text + "\"");
    }

    public static void printInfo(double a, double b) {
        System.out.println("Два числа с плавающей точкой: " + a + " и " + b);
    }

    public static void printInfo(int[] array) {
        System.out.print("Массив целых чисел: ");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // ❌ Этот метод НЕЛЬЗЯ добавить — сигнатура совпадает с printInfo(int, int)
    // public static void printInfo(int first, int second) { ... }

    // ✅ А вот этот — можно, потому что типы разные:
    public static void printInfo(long number) {
        System.out.println("Длинное целое: " + number);
    }
}