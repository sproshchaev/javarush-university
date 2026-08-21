package com.javarush.example;

/**
 * Слайд 14. Логические операторы AND (&&), OR (||), NOT (!).
 */
public class Slide14_LogicalOperators {
    public static void main(String[] args) {

        int a = 50;
        System.out.println("(0 < a) && (a < 100) -> " + ((0 < a) && (a < 100)));

        boolean x = true;
        boolean y = false;

        System.out.println("(!x) && (!y)  -> " + ((!x) && (!y)));
        System.out.println("!(!x || !y)   -> " + (!(!x || !y)));
    }
}
