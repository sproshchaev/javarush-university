package com.javarush.example;

public class Slide08_BooleanVariables {
    public static void main(String[] args) {
        int age = 70;
        boolean isSenior = (age > 65);

        if (isSenior) {
            System.out.println("Пора на пенсию");
        }
    }
}