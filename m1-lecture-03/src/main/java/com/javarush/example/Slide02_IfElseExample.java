package com.javarush.example;

public class Slide02_IfElseExample {
    public static void main(String[] args) {
        int age = 17;

        if (age < 18) {
            System.out.println("Ты ещё ребенок");
            System.out.println("Не спорь со взрослыми");
        } else {
            System.out.println("Вы уже взрослый");
            System.out.println("Ну и молодежь пошла");
        }
    }
}
