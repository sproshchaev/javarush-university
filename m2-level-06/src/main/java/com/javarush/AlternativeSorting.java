package com.javarush;

import java.util.*;

public class AlternativeSorting {

    public static void main(String[] args) {

        List<String> words = new ArrayList<>(Arrays.asList("яблоко", "кот", "собака", "дом"));
        System.out.println("Слова: " + words);

        // Создадим компаратор
        Comparator<String> lengthComp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // Сравнение по длине строк (обратный порядок)
                return o2.length() - o1.length();
            }
        };

        Collections.sort(words, lengthComp);
        System.out.println("По длине (в обратом): " + words); // По длине (в обратом): [яблоко, собака, кот, дом]

        // 2-ой вариант - короче:
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println("По длине (в прямом): " + words); // По длине (в прямом): [кот, дом, яблоко, собака]


    }

}
