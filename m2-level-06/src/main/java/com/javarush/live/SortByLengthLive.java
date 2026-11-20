package com.javarush.live;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortByLengthLive {

    public static void main(String[] args) {

        List<String> names = List.of("анна", "иван", "мария", "петр");

        Comparator<String> anonymous = new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                return Integer.compare(first.length(), second.length());
            }
        };

        List<String> firstResult = new ArrayList<>(names);
        firstResult.sort(anonymous);

        Comparator<String> lambda =
                (first, second) -> Integer.compare(first.length(), second.length());

        List<String> secondResult = new ArrayList<>(names);
        secondResult.sort(lambda);

        System.out.println("Анонимный класс: " + firstResult);
        System.out.println("Лямбда: " + secondResult);

        System.out.println("Результаты совпали: " + firstResult.equals(secondResult));

    }

}
