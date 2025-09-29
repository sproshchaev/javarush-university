package com.javarush;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {

        String text = "Привет!";
        String mes = text;

        System.out.println(text == mes);

        String a = new String("Привет");
        String b = new String("Привет");

        System.out.println(a == b);

        System.out.println(a.equals(b));

        String name = null;
        if (name != null) {
            System.out.println("Не null");
        }

    }

}
