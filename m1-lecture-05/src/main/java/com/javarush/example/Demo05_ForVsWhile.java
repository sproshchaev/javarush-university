package com.javarush.example;

public class Demo05_ForVsWhile {
    public static void main(String[] args) {
        System.out.println("=== Вариант с while ===");

        int i = 1;
        while (i <= 5) {
            System.out.print(i + " ");
            i++;
        }
        System.out.println();

        System.out.println("=== Вариант с for ===");

        for (int j = 1; j <= 5; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
