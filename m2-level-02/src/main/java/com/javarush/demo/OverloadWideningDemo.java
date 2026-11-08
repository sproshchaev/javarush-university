package com.javarush.demo;

public class OverloadWideningDemo {
    public static void main(String[] args) {
        byte small = 7;
        show(small);   // byte расширился до long
        show(5);       // int расширился до long
        show(2.5f);    // float расширился до double

        onlyInt(5);
        // onlyInt(5L);   // ошибка компиляции: long до int не сужается
    }

    static void show(long value) {
        System.out.println("long: " + value);
    }

    static void show(double value) {
        System.out.println("double: " + value);
    }

    static void onlyInt(int value) {
        System.out.println("int: " + value);
    }
}
