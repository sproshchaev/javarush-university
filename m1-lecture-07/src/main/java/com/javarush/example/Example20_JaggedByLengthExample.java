package com.javarush.example;

public class Example20_JaggedByLengthExample {
    public static void main(String[] args) {
        int n = 4;
        int[][] jagged = new int[n][];

        for (int i = 0; i < n; i++) {
            jagged[i] = new int[i + 1];
            for (int j = 0; j < jagged[i].length; j++) {
                jagged[i][j] = i + j;
            }
        }

        System.out.println("Зубчатый массив:");
        for (int[] row : jagged) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}