package com.javarush.demo;

import java.util.Scanner;

public class ArrayIndexDemo {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int size = scan.nextInt();

        int[] numbers = new int[size]; // 10 ячеек

        numbers[0] = (1 * 100); // первая ячейка
        numbers[size - 1] =  ((size - 1) * 100); // последняя ячейка

        System.out.println("Первая ячейка [0]: " + numbers[0]);
        System.out.println("Последняя ячейка [" + (size - 1) + "]: " + numbers[size - 1]);
        System.out.println("Длина массива numbers = " + numbers.length);

        // numbers[10] = 300; // Ошибка ArrayIndexOutOfBoundsException

    }

}
