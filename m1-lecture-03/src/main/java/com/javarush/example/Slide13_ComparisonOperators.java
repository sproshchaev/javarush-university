package com.javarush.example;

/**
 * Слайд 13. Операторы сравнения.
 */
public class Slide13_ComparisonOperators {
    public static void main(String[] args) {

        int a = 3;
        int b = 7;
        int speed = 90;
        int max = 60;
        int age = 18;
        int time = 0;

        System.out.println("a < 10       -> " + (a < 10));
        System.out.println("b > a        -> " + (b > a));
        System.out.println("a <= 10      -> " + (a <= 10));
        System.out.println("speed >= max -> " + (speed >= max));
        System.out.println("age == 18    -> " + (age == 18));
        System.out.println("time != 0    -> " + (time != 0));
    }
}
