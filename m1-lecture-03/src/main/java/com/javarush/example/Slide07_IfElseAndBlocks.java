package com.javarush.example;

/**
 * Слайд 7. Сочетание if-else и блока команд.
 */
public class Slide07_IfElseAndBlocks {
    public static void main(String[] args) {

        int temperature = 5;

        if (temperature < 0) {
            System.out.println("На улице мороз");
            System.out.println("Шапку надень");
        }
        else System.out.println("Тепло");

        int age = 21;

        if (age == 18) {
            System.out.println("Явитесь в военкомат");
        }
        else {
        }
    }
}
