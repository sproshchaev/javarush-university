package com.javarush.example;

public class Example11_MaxRowSumExample {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {0, 1, 2}};
        int maxSum = Integer.MIN_VALUE;
        int maxRow = 0;

        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxRow = i;
            }
        }

        System.out.println("Строка с максимальной суммой: " + maxRow + " (" + maxSum + ")");
    }
}