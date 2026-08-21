package com.javarush.example;

/**
 * Слайд 17. Сравнение строк оператором == (сравниваются адреса).
 */
public class Slide17_StringComparison {
    public static void main(String[] args) {

        String text = "Привет";
        String message = text;
        // адреса равны
        System.out.println(text == message);
    }
}
