package com.javarush.example;

public class Demo07_ForFactorial {
    public static void main(String[] args) {
        System.out.println("=== Факториал числа 5 ===");

        long result = 1;

        for (int i = 1; i <= 5; i++) {
            result = result * i;
            System.out.println("Умножили на " + i + ", результат: " + result);
        }

        System.out.println("Факториал 5 равен " + result);
    }
}
