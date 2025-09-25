package com.javarush.example;

public class Slide16_StringInternExample {
    public static void main(String[] args) {
        String a = new String("test");
        String b = "test";
        String c = a.intern();

        System.out.println("a == b: " + (a == b)); // false
        System.out.println("b == c: " + (b == c)); // true
    }
}