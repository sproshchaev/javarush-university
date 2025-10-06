package com.javarush.example;

public class Slide05_ArrayCellsExample {
    public static void main(String[] args) {
        int[][] data = new int[2][5];
        data[1][1] = 5;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
}