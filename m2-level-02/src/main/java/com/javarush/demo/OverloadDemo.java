package com.javarush.demo;

public class OverloadDemo {
    public static void main(String[] args) {
        print(42);
        print(3.5);
        print("привет");
        print(2, 3);
    }

    static void print(int value) {
        System.out.println("int: " + value);
    }

    static void print(double value) {
        System.out.println("double: " + value);
    }

    static void print(String value) {
        System.out.println("String: " + value);
    }

    static void print(int a, int b) {
        System.out.println("два int: " + a + " и " + b);
    }

    // static void print(int number) {
    //     System.out.println("другое имя параметра");
    // }
}
