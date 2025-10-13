package com.javarush;

import java.util.Arrays;

/**
 * JavaRush-University
 */
public class Main05 {

    // Сравнение String
    public static void main(String[] args) {

        // (1) equals
        String a = "Hello!";
        String b = "Hello!";
        String c = new String("Hello!");

        System.out.println(a.equals(b)); // true
        System.out.println(a.equals(c)); // true

        // (2) equalsIgnoreCase
        String d = "HELLO!";
        System.out.println(a.equalsIgnoreCase(d)); // true

        // (3) comareTo - как в словаре
        String str1 = "apple";
        String str2 = "banana";

        System.out.println(str1.compareTo(str2)); // -1  (<0) "apple" < "banana"
        System.out.println(str2.compareTo(str1)); //  1  (>0) "banana" > "apple"
        System.out.println(str1.compareTo(str1)); //  0  (=0) "apple" = "banana"

        // (4) startsWith
        String s4 = "Java";
        System.out.println(s4.startsWith("Ja")); // true

        // (5) endWith
        String s5 = "Java";
        System.out.println(s5.endsWith("va")); // true


    }

}
