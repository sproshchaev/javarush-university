package com.javarush.example;

public class Slide15_StringIntern {
    public static void main(String[] args) {
        String text = "Это очень важное сообщение";
        String message = "Это очень важное сообщение";

        System.out.println(text == message); // true
        System.out.println(text.equals(message)); // true

        String dynamic = new String("Это очень важное сообщение");
        System.out.println(text == dynamic); // false
        System.out.println(text.equals(dynamic)); // true

        String interned = dynamic.intern();
        System.out.println(text == interned); // true
    }
}