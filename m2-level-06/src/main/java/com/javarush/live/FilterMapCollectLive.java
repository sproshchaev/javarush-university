package com.javarush.live;

import java.util.List;

public class FilterMapCollectLive {

    public static void main(String[] args) {

        List<String> names = List.of("анна", "иван", "мария", "петр");

        List<String> result = names.stream() // "анна", "иван", "мария", "петр"
                // промежуточные операции:
                .filter(name -> (name.length() > 4)) // "мария"
                .map(name -> name.toUpperCase()) // "МАРИЯ"
                // терминальные операции:
                .toList();

        System.out.println("Результат: " + result);
        System.out.println("Исходные именв: " + names);

    }

}
