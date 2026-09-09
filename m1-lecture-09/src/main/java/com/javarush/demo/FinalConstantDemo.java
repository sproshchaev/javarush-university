package com.javarush.demo;

import java.util.Arrays;

public class FinalConstantDemo {

    public static final String SOURCE_ROOT = "c:\\project\\my\\";
    public static final int MAX_COUNT = 1000;


    public static void main(String[] args) {

        // Примитив: [содержимое]
        final int maxAttempts = 3; // final = неизменяемость содержимого (для примитивов)
        // maxAttempts = 4; // Cannot assign a value to final variable 'maxAttempts'

        // Объект: [ссылка] -> [содержимое]
        final String[] array = {"a", "b", "c"}; // final = неизменность ссылки (для объектов)
        final String[] array2 = {"a", "b", "c"}; // final = неизменность ссылки (для объектов)

        // array = array2; // Cannot assign a value to final variable 'array'

        System.out.println(Arrays.toString(array));
        array[0] = "A";
        System.out.println(Arrays.toString(array));

        System.out.println("Значение константы SOURCE_ROOT: " + SOURCE_ROOT);
        System.out.println("Значение константы MAX_COUNT: " + MAX_COUNT);

    }

}
