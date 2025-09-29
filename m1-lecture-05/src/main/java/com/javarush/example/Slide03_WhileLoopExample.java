package com.javarush.example;

public class Slide03_WhileLoopExample {
    public static void main(String[] args) {
        System.out.println("=== Пример цикла while ===\n");

        int n = 5;
        while (n > 0) {
            System.out.println(n);
            n--;
        }

        System.out.println("\nЦикл завершён, когда n стало равно 0.");
    }
}
