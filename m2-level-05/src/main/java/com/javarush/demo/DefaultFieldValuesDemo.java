package com.javarush.demo;

public class DefaultFieldValuesDemo {

    public static void main(String[] args) {
        Cat cat = new Cat();
        System.out.println("После создания Cat: a = " + cat.a
                + ", b = " + cat.b + ", c = " + cat.c);
        CatWithValues ready = new CatWithValues(5, 6, 30);
        System.out.println("Сумма заданных значений = " + ready.getSum());
    }

    private static class Cat {
        private int a = getSum();
        private int b = getSum() - a;
        private int c = getSum() - a - b;

        private int getSum() {
            System.out.println("getSum(): a = " + a + ", b = " + b
                    + ", c = " + c);
            return a + b + c;
        }
    }

    private static class CatWithValues {
        private final int a;
        private final int b;
        private final int c;

        CatWithValues(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        private int getSum() {
            return a + b + c;
        }

    }

}
