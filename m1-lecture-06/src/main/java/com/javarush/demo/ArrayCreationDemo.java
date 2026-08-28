package com.javarush.demo;

public class ArrayCreationDemo {

    public static void main(String[] args) {

        int[] array;
        array = new int[5];
        System.out.println("Массив создан, ячеек: " + array.length);

        int number = 1;

        int[] numbers = new int[3];

        System.out.println("Второй массив создан, ячеек: " + numbers.length);

    }

}
