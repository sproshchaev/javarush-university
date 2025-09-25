package com.javarush.example;

public class Slide01_IfElse {
    public static void main(String[] args) {
        int age = 18;

        if (age >= 18) {
            System.out.println("Вы совершеннолетний — можете голосовать!");
        } else {
            System.out.println("Вы ещё не достигли совершеннолетия.");
        }
    }
}
