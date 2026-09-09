package com.javarush.demo;

public class StringPoolDemo {

    public static void main(String[] args) {

        String a = "Привет!";
        String b = "Привет!";
        String c = new String("Привет!");

        System.out.println(a == b); // true
        System.out.println(a == c); // false

        System.out.println(c.equals(a)); // true

        String d = c.intern();
        System.out.println(a == d); // true (из пула)

    }

}
