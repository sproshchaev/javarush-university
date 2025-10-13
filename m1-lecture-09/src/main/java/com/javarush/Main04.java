package com.javarush;

import java.util.Arrays;

/**
 * JavaRush-University
 */
public class Main04 {

    // Методы String
    public static void main(String[] args) {

        // (1) .length()
        String s = "Hello!";
        System.out.println("Длина строки: " + s.length());

        // (2) isEmpty()
        String s1 = "";
        System.out.println(s1.isEmpty()); // true

        // (3) isBlank()
        String s2 = "   ";
        System.out.println(s2.isBlank()); // true

        // (4) charAt()
        String s4 = "Java";
        System.out.println(s4.charAt(2)); // v

        // (5) toCharArray()
        String s5 = "ABC";
        char[] charArray = s5.toCharArray();
        System.out.println(Arrays.toString(charArray)); // [A, B, C]

        // (6) getBytes()
        String s6 = "Hi!";
        byte[] bytes = s6.getBytes();
        System.out.println(Arrays.toString(bytes)); // [72 = H, 105 = i, 33 = !]

        // (7) sprit
        String s7 = "apple-banana-orange";
        String[] arrayString = s7.split("-");
        for (String str : arrayString) {
            System.out.println(str);
        }

        // (8) join
        String s8 = String.join(",", "apple", "banana", "orange");
        System.out.println(s8); // apple,banana,orange

    }

}
