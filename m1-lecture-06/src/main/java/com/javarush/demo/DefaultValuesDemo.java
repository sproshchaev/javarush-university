package com.javarush.demo;

public class DefaultValuesDemo {

    public static void main(String[] args) {

        int[] numbers = new int[3];
        boolean[] flags = new boolean[3];
        String[] strings = new String[3];

        System.out.println("int: " + numbers[0] + ", " + numbers[1] + ", " + numbers[2]);
        System.out.println("boolean: " + flags[0] + ", " + flags[1] + ", " + flags[2]);
        System.out.println("string: " + strings[0] + ", " + strings[1] + ", " + strings[2]);

        // настройку шрифта
        boolean[] fontsBold = new boolean[3];
        fontsBold [0] = true;
        fontsBold [1] = false;
        fontsBold [2] = true;

        String[] words = new String[3];
        words [0] = "яблоко";
        words [1] = "груша";
        words [2] = "банан";


        // Обход элементов массива
        for (int i = 0; i < words.length; i++) {

            if (fontsBold [i]) {
                System.out.println("words[" + i + "]: " + words[i] + " (жирный шрифт)");
            } else {
                System.out.println("words[" + i + "]: " + words[i] + " (обычный шрифт)");
            }

        }


    }

}
