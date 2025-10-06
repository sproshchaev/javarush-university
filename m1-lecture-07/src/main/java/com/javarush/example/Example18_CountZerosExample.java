package com.javarush.example;

public class Example18_CountZerosExample {
    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 3}, {0, 5, 0}, {7, 0, 9}};
        int count = 0;

        for (int[] row : matrix) {
            for (int cell : row) {
                if (cell == 0) {
                    count++;
                }
            }
        }

        System.out.println("Количество нулей: " + count);
    }
}