package com.javarush.method;

public class GenericDemoMethod {

    // Generic-метод
    static <T> T getMiddle(T[] array) {
        return array[array.length / 2];
    }

    // Generic-метод с ограничением
    static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static void main(String[] args) {
        String[] words = {"Java", "Generic", "Demo"};
        System.out.println("Середина массива: " + getMiddle(words));

        System.out.println("Большее из 10 и 20: " + max(10, 20));
        System.out.println("Большее из 'A' и 'B': " + max("A", "B"));
    }

}
