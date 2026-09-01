package com.javarush.demo;

public class JaggedArrayDemo {

    public static void main(String[] args) {

        int[][] matrix = new int[3][];
        matrix[0] = new int[] {1, 2, 3, 4, 5, 6};
        matrix[1] = new int[] {1, 2, 3};
        matrix[2] = new int[] {1, 3, 7, 9};

        System.out.println("Строк: " + matrix.length);
        System.out.println("Длина matrix[0]: " + matrix[0].length);
        System.out.println("Длина matrix[1]: " + matrix[1].length);
        System.out.println("Длина matrix[2]: " + matrix[2].length);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

}
