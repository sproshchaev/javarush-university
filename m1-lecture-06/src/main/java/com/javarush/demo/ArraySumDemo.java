package com.javarush.demo;

public class ArraySumDemo {

    public static void main(String[] args) {

        int[] temperatures = {12, 18, 7, 25, 19, 3};

        int sum = 0; // сумма всех элементов
        int max = Integer.MIN_VALUE; // самое маленькое значение из int
        int min = Integer.MAX_VALUE; // самое большое значение из int
        // int max = temperatures[0];

        for (int i = 0; i < temperatures.length; i++) {
            sum = sum + temperatures[i];

            if (temperatures[i] > max) {
                max = temperatures[i];
            }

            if (temperatures[i] < min) {
                min = temperatures[i];
            }

        }

        System.out.println("Кол-во значений: " + temperatures.length);
        System.out.println("Сумма всех элементов: " + sum);
        System.out.println("Максимум: " + max);
        System.out.println("Минимум: " + min);
        System.out.println("Среднее: " + (sum / temperatures.length));

    }

}
