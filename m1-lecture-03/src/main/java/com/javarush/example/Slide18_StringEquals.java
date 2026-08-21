package com.javarush.example;

/**
 * Слайд 18. Сравнение строк по содержанию — метод equals.
 */
public class Slide18_StringEquals {
    public static void main(String[] args) {

        String s1 = "Привет";        // Привет
        String s2 = "ПРИВЕТ";        // ПРИВЕТ
        String s3 = s1.toUpperCase();// ПРИВЕТ

        System.out.println(s1.equals(s2)); // false, разные
        System.out.println(s1.equals(s3)); // false, разные
        System.out.println(s2.equals(s3)); // true, одинаковые, хотя адреса разные
    }
}
