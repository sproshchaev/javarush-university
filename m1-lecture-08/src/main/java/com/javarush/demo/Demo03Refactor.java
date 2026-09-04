package com.javarush.demo;

public class Demo03Refactor {

    public static void main(String[] args) {
        print();
    }

    // Метод 10-15
    public static void print() {
        print1();
        print2();
        print3();
    }

    // Приватный метод этого же класса
    private static void print3() {
        System.out.println("Строка 1");
        System.out.println("Строка 1");
        System.out.println("Строка 1");
        System.out.println("Строка 1");
        System.out.println("Строка 1");
        System.out.println("Строка 1");
        System.out.println("Строка 1");
        System.out.println("Строка 1");
    }

    private static void print2() {
        System.out.println("Строка 1");
        System.out.println("Строка 1");
        System.out.println("Строка 1");
    }

    // Только внутри этого класса!
    private static void print1() {
        System.out.println("Строка 1");
        System.out.println("Строка 2");
        System.out.println("Строка 3");
    }

}
