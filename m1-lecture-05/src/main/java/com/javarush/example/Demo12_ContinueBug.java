package com.javarush.example;

public class Demo12_ContinueBug {
    public static void main(String[] args) {
        System.out.println("=== Замысел: числа от 1 до 20, кроме кратных 7 ===");

        int i = 1;
        while (i <= 20) {
            if (i % 7 == 0) {
                continue;
            }
            System.out.println(i);
            i++;
        }

        System.out.println("Эта строка не выполнится");
    }
}
