package com.javarush.example;

/**
 * Слайд 16. Тернарный оператор.
 */
public class Slide16_TernaryOperator {
    public static void main(String[] args) {

        // Вариант 1: if-else
        int age = 25;
        int money;
        if (age > 30)
            money = 100;
        else
            money = 50;
        System.out.println("if-else:  money = " + money);

        // Вариант 2: тернарный оператор
        int age2 = 25;
        int money2 = age2 > 30 ? 100 : 50;
        System.out.println("тернарный: money = " + money2);
    }
}
