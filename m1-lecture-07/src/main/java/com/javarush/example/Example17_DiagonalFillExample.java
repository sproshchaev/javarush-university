package com.javarush.example;

public class Example17_DiagonalFillExample {
    public static void main(String[] args) {
        int n = 4;
        int[][] matrix = new int[n][n];
        int value = 1;

        for (int i = 0; i < n; i++) {
            matrix[i][i] = value++;
        }

        System.out.println("Диагональ заполнена:");
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
