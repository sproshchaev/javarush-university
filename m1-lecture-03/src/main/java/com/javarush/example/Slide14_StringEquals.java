package com.javarush.example;

public class Slide14_StringEquals {
    public static void main(String[] args) {
        String s1 = "Привет";
        String s2 = "ПРИВЕТ";
        String s3 = s1.toUpperCase(); // "ПРИВЕТ"

        System.out.println(s1.equals(s2)); // false
        System.out.println(s1.equals(s3)); // false
        System.out.println(s2.equals(s3)); // true
    }
}