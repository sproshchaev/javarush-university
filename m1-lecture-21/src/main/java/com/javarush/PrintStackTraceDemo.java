package com.javarush;

public class PrintStackTraceDemo {

    public static void main(String[] args) {
        try {
            methodA();
        } catch (Exception e) {
            System.out.println("Поймали исключение!");
            e.printStackTrace();
        }

    }

    static void methodA() {
        methodB();
    }

    static void methodB() {
        methodC();
    }

    static void methodC() {
        // Выбросим исключение
        throw new RuntimeException("Ошибка в самом глубоком методе!");
    }

}
