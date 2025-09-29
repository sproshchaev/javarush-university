package com.javarush.example;

public class Slide08_NestedLoopsExample {
    public static void main(String[] args) {
        System.out.println("=== Пример вложенных циклов ===\n");

        int n = 0;
        while (n < 4) { // внешний цикл — 4 строки
            int m = 0;
            while (m < 5) { // внутренний цикл — 5 символов в строке
                System.out.print("A");
                m++;
            }
            System.out.println(); // переход на новую строку
            n++;
        }

        System.out.println("\n---\n");

        // 🧩 Дополнительно: покажем, как работает "перенос курсора"
        System.out.println("✅ Как работает перенос курсора:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("*");
            }
            System.out.println(); // перенос на новую строку после внутреннего цикла
        }
    }
}
