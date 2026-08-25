package com.javarush.example;

public class Demo14_NestedRectangle {
    public static void main(String[] args) {
        System.out.println("=== Четыре строки по пять букв ===");

        int n = 0;
        while (n < 4) {

            int m = 0;
            while (m < 5) {
                System.out.print("A");
                m++;
            }

            System.out.println("   <- строка номер " + n);
            n++;
        }

        System.out.println("Букв напечатано: 4 * 5 = 20");
    }
}
