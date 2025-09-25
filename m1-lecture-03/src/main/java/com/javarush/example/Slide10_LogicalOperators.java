package com.javarush.example;

public class Slide10_LogicalOperators {
    public static void main(String[] args) {
        int age = 25;
        int height = 175;

        if (age > 18 && height > 170) {
            System.out.println("Допущен к участию!");
        }

        boolean hasPassport = false;
        boolean hasDriverLicense = true;

        if (hasPassport || hasDriverLicense) {
            System.out.println("Пропуск разрешён");
        }

        boolean isStudent = false;

        if (!isStudent) {
            System.out.println("Оплата полной стоимости");
        }

        boolean a = true;
        boolean b = false;

        if (a && !b) {
            System.out.println("Условие истинно");
        } else {
            System.out.println("Условие ложно");
        }
    }
}