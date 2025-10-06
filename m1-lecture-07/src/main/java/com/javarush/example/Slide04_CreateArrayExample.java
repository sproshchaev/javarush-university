package com.javarush.example;

public class Slide04_CreateArrayExample {
    public static void main(String[] args) {
        int[][] data = new int[2][5];
        data[1][1] = 5;
        System.out.println("data[1][1] = " + data[1][1]);
    }
}
