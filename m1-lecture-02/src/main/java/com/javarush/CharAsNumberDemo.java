package com.javarush;

public class CharAsNumberDemo {

    public static void main(String[] args) {

        char letter = 'a'; // 97
        System.out.println(letter);

        int code = letter + 1;

        System.out.println(code);

        char fromCode = (char) code;

        System.out.println(fromCode); // b

    }

}
