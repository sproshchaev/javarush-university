package com.javarush.example;

public class Demo16_NestedMultiplicationTable {
    public static void main(String[] args) {
        System.out.println("=== Таблица умножения ===");

        for (int i = 1; i <= 9; i++) {

            for (int j = 1; j <= 9; j++) {
                System.out.print(i * j + "\t");
            }

            System.out.println();
        }
    }
}
