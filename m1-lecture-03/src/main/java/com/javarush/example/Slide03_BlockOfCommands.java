package com.javarush.example;

public class Slide03_BlockOfCommands {
    public static void main(String[] args) {
        int temperature = 25;

        if (temperature > 20) {
            System.out.println("На улице тепло!");
            System.out.println("Можно гулять без шапки.");
            int comfortLevel = 10;
            System.out.println("Уровень комфорта: " + comfortLevel);
        } else {
            System.out.println("На улице холодно.");
            System.out.println("Надень шапку и шарф.");
        }
    }
}
