package com.javarush;

public class Fly {

    // (1) константы класса - публичные не изменяемые поля
    public static final int COUNT = 100;

    // (2) внутренние константы (private)
    private static final int COUNT2 = 200;

    // (3) статические поля (не константы)
    private static int COUNT3 = 300;

    // (4) поля класса
    private int count4;

    // (5) конструкторы
    public Fly(int count4) {
        this.count4 = count4;
    }

    // (6) публичные методы (public)
    public void run() {
        System.out.println("run");
    }

    // (7) защищенные методы (protected)
    protected void run2() {
        System.out.println("run");
    }

    // (8) приватные методы (private)
    private void run3() {
        System.out.println("run");
    }

}
