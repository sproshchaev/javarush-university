package com.javarush.example;

/**
 * Слайд 19. Нюанс: одинаковые строки из кода компилятор хранит одним объектом.
 */
public class Slide19_StringPool {
    public static void main(String[] args) {

        String text = "Это очень важное сообщение";
        String message = "Это очень важное сообщение";

        System.out.println("text == message      -> " + (text == message));
        System.out.println("text.equals(message) -> " + text.equals(message));

        String s2 = "ПРИВЕТ";
        String s3 = "Привет".toUpperCase();

        System.out.println("s2 == s3             -> " + (s2 == s3));
        System.out.println("s2.equals(s3)        -> " + s2.equals(s3));
    }
}
