package com.javarush.example;

public class Demo15_NestedTriangle {
    public static void main(String[] args) {
        System.out.println("=== Треугольник из звёздочек ===");

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}
