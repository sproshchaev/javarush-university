package com.javarush.example;

public class Demo06_ForStep {
    public static void main(String[] args) {
        System.out.println("=== Чётные числа, шаг 2 ===");
        for (int i = 0; i <= 10; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("=== Обратный отсчёт ===");
        for (int i = 5; i > 0; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
