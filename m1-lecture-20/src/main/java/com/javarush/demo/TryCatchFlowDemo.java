package com.javarush.demo;

public class TryCatchFlowDemo {

    public static void main(String[] args) {

        divide(10, 2);
        System.out.println("---");
        divide(10, 0);

    }

    private static void divide(int a, int b) {

        try {
            System.out.println("1. Делим " + a + " на " + b);
            int result = a / b;
            System.out.println("2. Результат: " + result);
        } catch (ArithmeticException ex) {
            System.out.println("catch: " + ex.getMessage());
        }

        System.out.println("3. Выходим из метода");
    }

}
