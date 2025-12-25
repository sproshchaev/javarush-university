package com.javarush;

import java.util.*;

public class StandardSorting {

    public static void main(String[] args) {

        // 1) Сортировка по алфавиту
        List<String> names = new ArrayList<>(Arrays.asList("Мария", "Анна", "Иван", "Петр"));
        System.out.println("До сортировки: " + names);

        Collections.sort(names);
        System.out.println("После сортировки: " + names);

        //
        List<Integer> numbers = new ArrayList<>(Arrays.asList(10, 2, 8, 15, 1));
        System.out.println("До сортировки: " + numbers);

        Collections.sort(numbers);
        System.out.println("После сортировки: " + numbers);

    }

}

