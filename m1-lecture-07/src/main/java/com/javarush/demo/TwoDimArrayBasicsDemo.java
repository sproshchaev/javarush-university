package com.javarush.demo;

public class TwoDimArrayBasicsDemo {

    public static void main(String[] args) {

        int[][] data = new int[2][5];

        System.out.println("Длина внешнего массива data.lentgh: " + data.length); // 2
        System.out.println("Длина строки data[0].length: " + data[0].length); // 5
        System.out.println("Значение по умолчанию data[0][0]: " + data[0][0]); // 0

        data[1][1] = 5;

        System.out.println("После записи data[1][1]: " + data[1][1]); // 5




    }

}
