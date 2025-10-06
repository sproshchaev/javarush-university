package com.javarush.example;

public class Slide08_QuickInitArrayExample {
    public static void main(String[] args) {
        int[][] months = {
                {31, 28, 31},
                {30, 31, 30},
                {31, 31, 30},
                {31, 30, 31}
        };

        for (int[] row : months) {
            for (int day : row) {
                System.out.print(day + " ");
            }
            System.out.println();
        }
    }
}
