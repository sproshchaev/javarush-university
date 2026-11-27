package com.javarush.demo;

public class FieldInitializationDemo {

    public static void main(String[] args) {
        Cat cat = new Cat();
        System.out.println("a = " + cat.a + ", b = " + cat.b
                + ", c = " + cat.c);
    }

    private static class Cat {
        private int a = 5;
        private int b = a + 1;
        private int c = a * b;
    }

}
