package com.javarush.demo;

import java.lang.Integer;

public class AutoboxingDemo {

    public static void main(String[] args) {

        Integer a = 10; // Integer.valueOf(10)

        int b = a; // unboxing

        Integer c  = a + b; // распаковка, сложение, упаковка

        Integer d = Integer.valueOf(10);

        System.out.println("Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE=" + Integer.MIN_VALUE);

    }

}
