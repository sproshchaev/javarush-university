package com.javarush.example;

public class Demo03_InfiniteLoop {
    public static void main(String[] args) {
        System.out.println("=== Осторожно: бесконечный цикл ===");

        int n = 5;
        while (n > 0) {
            System.out.println(n);
            // n--;   <-- строка закомментирована: счётчик не меняется
        }

        System.out.println("Эта строка никогда не выполнится");
    }
}
