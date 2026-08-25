package com.javarush.example;

public class Demo11_BreakSearch {
    public static void main(String[] args) {
        System.out.println("=== Ищем первое число больше 100, кратное 13 ===");

        for (int i = 101; i <= 200; i++) {
            System.out.println("Проверяем " + i);

            if (i % 13 == 0) {
                System.out.println("Нашли: " + i);
                break;
            }
        }

        System.out.println("Поиск закончен");
    }
}
