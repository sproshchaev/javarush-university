package com.javarush.example;

public class Demo13_ContinueFixed {
    public static void main(String[] args) {
        System.out.println("=== Числа от 1 до 20, кроме кратных 7 ===");

        for (int i = 1; i <= 20; i++) {
            if (i % 7 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println("Пропущены числа 7 и 14");
    }
}
