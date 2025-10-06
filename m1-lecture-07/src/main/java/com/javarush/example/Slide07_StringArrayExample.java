package com.javarush.example;

public class Slide07_StringArrayExample {
    public static void main(String[] args) {
        String[][] names = {{"Иван", "Петр"}, {"Анна", "Мария"}};
        for (String[] row : names) {
            for (String name : row) {
                System.out.print(name + " ");
            }
            System.out.println();
        }
    }
}