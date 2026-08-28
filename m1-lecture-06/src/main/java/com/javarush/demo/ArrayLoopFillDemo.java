package com.javarush.demo;

public class ArrayLoopFillDemo {

    public static void main(String[] args) {

        int[] squares = new int[10];

        // Заполнение массива
        for (int i = 0; i < squares.length; i++) {
            squares[i] = i * i;
        }

        // Вывод массива
        for (int i = 0; i <= squares.length - 1; i++) {
            System.out.println("squares[" + i + "]:" + squares[i]);
        }

    }

}
