package com.javarush.example;

public class Slide07_BooleanType {
    public static void main(String[] args) {
        int age = 70;
        boolean isSenior = (age > 65);

        if (isSenior) {
            System.out.println("Вы получаете скидку!");
        }

        int record = 612;
        int value = 615;
        boolean hasNewRecord = (value > record);

        if (hasNewRecord) {
            System.out.println("Новый рекорд установлен!");
        }
    }
}