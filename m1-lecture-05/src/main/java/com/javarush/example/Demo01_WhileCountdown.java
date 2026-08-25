package com.javarush.example;

public class Demo01_WhileCountdown {
    public static void main(String[] args) {
        System.out.println("=== Обратный отсчёт ===");

        int n = 5;
        while (n > 0) {
            System.out.println(n);
            n--;
        }

        System.out.println("Цикл завершён, n = " + n);
    }
}
