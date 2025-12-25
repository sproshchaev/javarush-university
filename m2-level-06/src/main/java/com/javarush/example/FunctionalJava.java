package com.javarush.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FunctionalJava {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("анна", "иван", "мария", "петр");

        // Императивный стиль (до Java 8)
        for (String name : names) {
            if(name.length() > 4) {
                System.out.println(name.toUpperCase());
            }
        }

        // Функциональный стиль (Java 8+)
        // Промежуточные: filter, map, sorted - возвращают Stream, можно объединять в цепочки
        // Терминальные: count, collect, forEach

        names.stream()                                // источник данных: "анна", "иван", "мария", "петр"
                .filter(n -> n.length() > 4)    // промежуточная операция  (фильтрация)
                .map(String::toUpperCase)             // промежуточная операция  (преобразование)
                                                      // ...
                .forEach(System.out::println);        // терминальная (конечная операция) операция (действие)
                // .collect(toList());
                // .count();

        // System.out.println(names.stream().filter(n -> n.length() > 4).count());
    }

}
