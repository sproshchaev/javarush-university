package com.javarush.example;

public class Slide09_ComparisonOperators {
    public static void main(String[] args) {
        int age = 25;
        double speed = 60.5;
        int maxSpeed = 60;

        if (age < 30) {
            System.out.println("Молодой человек");
        }

        if (speed >= maxSpeed) {
            System.out.println("Превышение скорости!");
        }

        if (age == 18) {
            System.out.println("Поздравляем с совершеннолетием!");
        }

        if (speed != 0) {
            System.out.println("Автомобиль движется");
        }
    }
}