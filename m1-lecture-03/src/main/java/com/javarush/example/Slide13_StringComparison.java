package com.javarush.example;

public class Slide13_StringComparison {
    public static void main(String[] args) {
        String text = new String("Привет");
        String message = new String("Привет");

        System.out.println(text == message); // false
        System.out.println(text.equals(message)); // true
    }
}