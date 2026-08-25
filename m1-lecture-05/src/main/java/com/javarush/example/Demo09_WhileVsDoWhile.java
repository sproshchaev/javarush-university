package com.javarush.example;

public class Demo09_WhileVsDoWhile {
    public static void main(String[] args) {
        System.out.println("=== Цикл while, условие ложно сразу ===");

        int a = 10;
        while (a < 5) {
            System.out.println("Тело while выполнилось, a = " + a);
            a++;
        }
        System.out.println("Тело while не выполнилось ни разу");

        System.out.println("=== Цикл do-while, условие то же самое ===");

        int b = 10;
        do {
            System.out.println("Тело do-while выполнилось, b = " + b);
            b++;
        } while (b < 5);
        System.out.println("Тело do-while выполнилось один раз");
    }
}
