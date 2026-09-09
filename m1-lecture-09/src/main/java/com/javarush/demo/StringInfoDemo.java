package com.javarush.demo;

public class StringInfoDemo {

    public static void main(String[] args) {
        String a = "abcdef";
        System.out.println(a.substring(1, 4)); // bcd

        String a1 = "A";
        String b = "B";
        String c = "C";

        String d = String.join(a1, b, c);
        System.out.println(d);
        System.out.println(a1 + b + c);

    }

}
