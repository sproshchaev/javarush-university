package com.javarush.demo;

public class StaticMethodDemo {

    public static void main(String[] args) {
        int square = MathUtils.square(7);
        int max = MathUtils.max(12, 25);

        System.out.println("square(7)=" + square);
        System.out.println("max(12, 25)=" + max);

        // стандартный Math
        System.out.println("Math.sqrt(7)=" + Math.sqrt(7));
        System.out.println("Число Пи=" + Math.PI);

    }

}

class MathUtils {

    static int square(int x) {
        return x * x;
    }

    static int max(int a, int b) {
        return a > b ? a : b;
    }

}