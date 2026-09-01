package com.javarush.demo;

public class TwoDimArrayFillDemo {

    public static void main(String[] args) {

        int[][] table = new int[3][4];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = i * 10 + j;
            }
        }

        // 0  1  2  3
        // 10 11 12 13
        // 20 21 22 23

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }

    }

}
