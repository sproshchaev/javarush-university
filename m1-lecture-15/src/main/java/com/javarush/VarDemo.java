package com.javarush;

import java.util.ArrayList;

public class VarDemo {

    public static void main(String[] args) {

        // Простые тип
     // int number = 42;
        var number = 42;    // int
        var num2 = 2L;      // long
        var pi = 3.14;      // double
        var name = "Java";  // String

        System.out.println("number = " + number);
        System.out.println("pi = " + pi);
        System.out.println("name = " + name);

        var list = new ArrayList<String>();
        list.add("Hello");
        list.add("World");
        list.add("Java");
        System.out.println("list = " + list); // list = [Hello, World, Java]

    }

}
