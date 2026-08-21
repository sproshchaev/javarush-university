package com.javarush.example;

/**
 * Слайд 12. Использование булевых переменных в условии if.
 */
public class Slide12_BooleanVariables {
    public static void main(String[] args) {

        // Вариант 1: результат сравнения сохранен в переменную
        int age = 70;

        boolean isSenior = (age > 65);
        if (isSenior)
            System.out.println("Пора на пенсию");

        // Вариант 2 (эквивалент): сравнение прямо в условии
        int age2 = 70;

        if (age2 > 65)
            System.out.println("Пора на пенсию");
    }
}
