package com.javarush.example;

public class Slide11_ExpressionsAndStatements {
    public static void main(String[] args) {
        int i = 3;
        int result = i++;
        System.out.println("i++ = " + result); // 3
        System.out.println("i = " + i); // 4

        int x = 5;
        if (x > 0) {
            System.out.println("x > 0");
        }
    }
}