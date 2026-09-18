package com.javarush.demo;

public class StaticFieldAccessDemo {

    static String courseName = "Java Syntax"; // статическая переменная

    public static void main(String[] args) {

        System.out.println("Внутри класса " + courseName);
        System.out.println("Внутри класса " + StaticFieldAccessDemo.courseName);

    }

}
