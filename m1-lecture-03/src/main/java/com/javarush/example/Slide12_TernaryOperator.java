package com.javarush.example;

public class Slide12_TernaryOperator {
    public static void main(String[] args) {
        int age = 25;
        int money = age > 30 ? 100 : 50;
        System.out.println("Деньги: " + money);

        int score = 85;
        String grade = score >= 90 ? "A" : "B";
        System.out.println("Оценка: " + grade);
    }
}