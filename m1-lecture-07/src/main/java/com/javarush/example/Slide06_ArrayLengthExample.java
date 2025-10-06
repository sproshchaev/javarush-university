package com.javarush.example;

public class Slide06_ArrayLengthExample {
    public static void main(String[] args) {
        int[][] data = new int[2][5];
        System.out.println("Количество строк: " + data.length);
        System.out.println("Количество столбцов в первой строке: " + data[0].length);
    }
}
