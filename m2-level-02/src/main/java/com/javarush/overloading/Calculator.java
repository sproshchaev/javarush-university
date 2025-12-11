package com.javarush.overloading;

// Примеры переопределения
public class Calculator {

    // (1) Перегрузка по кол-ву параметров
    // 2 параметра
    public int add(int a, int b) {
        System.out.println("Adding " + a + " + " + b);
        return a + b;
    }

    // 3 параметра
    public int add(int a, int b, int c) {
        System.out.println("Adding " + a + " + " + b  + " + " + c);
        return a + b + c;
    }

    // (2) Перегрузка по ТИПАМ параметров
    public double add(double a, double b) {
        System.out.println("Adding " + a + " + " + b);
        return a + b;
    }

    // (3) Перегрузка по ПОРЯДКУ типов
    public String add(String a, int b) {
        System.out.println("Adding " + a + " + " + b);
        return a + b;
    }

    public String add(int a, String b) {
        System.out.println("Adding " + a + " + " + b);
        return a + b;
    }

    // Неявное расширение типов при вызове
    public void process(int x) {
        System.out.println("Processing(int): " + x);
    }

    public void process(long x) {
        System.out.println("Processing(long): " + x);
    }

    // Перегрузка с сылочными типами
    public void print(Object object) {
        System.out.println("print (Object):" + object);
    }

    public void print(String str) {
        System.out.println("print (String):" + str);
    }


}
