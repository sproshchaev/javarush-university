package com.javarush.demo;

public class CurrentStackTraceDemo {

    public static void main(String[] args) {
        first();
    }

    private static void first() {
        second();
    }

    private static void second() {
        third();
    }

    private static void third() {
        Thread current = Thread.currentThread();
        StackTraceElement[] methods = current.getStackTrace();

        System.out.println("Элементов в стек-трейсе: " + methods.length);
        for (int i = 0; i < methods.length; i++) {
            System.out.println(i + ": " + methods[i].getMethodName());
        }
    }


}
