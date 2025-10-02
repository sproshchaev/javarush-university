package com.javarush.example;

public class Slide04_CreateArrayExample {

    public static void main(String[] args) {
        System.out.println("=== Создание массива с помощью new ===");

        // Создаём массив из 5 элементов типа int
        int[] numbers = new int[5];

        System.out.println("Размер массива: " + numbers.length);
        System.out.print("Значения по умолчанию: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " "); // Все будут 0
        }
        System.out.println("\n");

        // Создаём массив строк
        String[] names = new String[3];
        System.out.println("Массив строк (по умолчанию):");
        for (int i = 0; i < names.length; i++) {
            System.out.println("names[" + i + "] = " + names[i]); // Все null
        }
        System.out.println();

        // Инициализация при объявлении — без new
        double[] temperatures = {23.5, 25.0, 22.8, 26.1};
        System.out.println("Массив температур (инициализация при объявлении):");
        for (int i = 0; i < temperatures.length; i++) {
            System.out.println("Температура дня " + (i + 1) + ": " + temperatures[i]);
        }
        System.out.println();

        // Пример с boolean
        boolean[] flags = new boolean[4];
        System.out.println("Массив boolean (по умолчанию):");
        for (int i = 0; i < flags.length; i++) {
            System.out.println("flags[" + i + "] = " + flags[i]); // Все false
        }
    }
}