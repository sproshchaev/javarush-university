package com.javarush;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

public class LambdaDemo {

    public static void main(String[] args) {

        // Анономный класс (до лямбды) - длинное:
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };

        // Лямбда-выражение, короче: () -> {}
        Comparator<String> comparator2 = (String o1, String o2) -> {
            return o1.length() - o2.length();
        };

        // Лямбда-выражение, еще короче: () -> {}
        Comparator<String> comparator3 = (a, b) -> {
            return a.length() - b.length();
        };

        // Проверка
        List<String> words = Arrays.asList("я", "кот", "слон");
        words.sort(comparator3);
        System.out.println(words); // [я, кот, слон]

    }

}

