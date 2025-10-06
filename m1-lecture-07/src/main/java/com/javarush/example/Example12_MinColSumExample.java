package com.javarush.example;

public class Example12_MinColSumExample {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int cols = matrix[0].length;
        int minSum = Integer.MAX_VALUE;
        int minCol = 0;

        for (int j = 0; j < cols; j++) {
            int sum = 0;
            for (int i = 0; i < matrix.length; i++) {
                sum += matrix[i][j];
            }
            if (sum < minSum) {
                minSum = sum;
                minCol = j;
            }
        }

        System.out.println("Столбец с минимальной суммой: " + minCol + " (" + minSum + ")");
    }
}
