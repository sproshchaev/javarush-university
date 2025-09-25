package com.javarush.example;

public class Slide04_IfElseBlocksExample {
    public static void main(String[] args) {
        int temperature = 5;

        if (temperature < 0) {
            System.out.println("На улице мороз");
            System.out.println("Шапку надень");
        } else {
            System.out.println("Тепло");
        }

        int age = 41;

        if (age == 18) {
            System.out.println("Катись в военкомат");
        } else {
            // пустой блок — ничего не делаем
        }
    }
}
