package com.javarush.example;

public class Example14_SymmetricMatrixExample {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {2, 4, 5}, {3, 5, 6}};
        boolean symmetric = true;
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    symmetric = false;
                    break;
                }
            }
            if (!symmetric) break;
        }

        System.out.println(symmetric ? "Матрица симметрична" : "Матрица не симметрична");
    }
}
