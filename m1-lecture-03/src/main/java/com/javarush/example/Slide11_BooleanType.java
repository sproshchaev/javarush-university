package com.javarush.example;

/**
 * Слайд 11. Для чего нужен тип boolean.
 */
public class Slide11_BooleanType {
    public static void main(String[] args) {

        boolean isOK = true;
        System.out.println("isOK = " + isOK);

        boolean hasError = false;
        System.out.println("hasError = " + hasError);

        int age = 70;
        boolean isSenior = (age > 65);
        System.out.println("isSenior = " + isSenior);

        int record = 612;
        int value = 615;
        boolean hasNewRecord = (value > record);
        System.out.println("hasNewRecord = " + hasNewRecord);
    }
}
