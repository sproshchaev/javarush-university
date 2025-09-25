package com.javarush.example;

public class Slide06_IfElseIf {
    public static void main(String[] args) {
        int temperature = 9;

        if (temperature > 20) {
            System.out.println("Надеть рубашку");
        } else if (temperature > 10) {
            System.out.println("Надеть свитер");
        } else if (temperature > 0) {
            System.out.println("Надеть плащ");
        } else {
            System.out.println("Надеть пальто");
        }
    }
}