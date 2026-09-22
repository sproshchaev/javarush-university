package com.javarush.demo;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListBasicsDemo {

    public static void main(String[] args) {

        // 0  1  2 ...                         9 <- индексы
        // [New York] [] [] [] [] [] [] [] [] [] <- capacity
        //
        ArrayList<String> cities = new ArrayList<>();

        System.out.println("cities.size() = " + cities.size());
        cities.add("New York");
        cities.add("Moscow");
        cities.add("Tokio");
        System.out.println("cities.size() = " + cities.size());
        System.out.println(cities); // [New York, Moscow, Tokio]

        for (int i = 0; i < cities.size(); i++) {
            System.out.println(i + ": " + cities.get(i));
        }

        // cities.get(100); // IndexOutOfBoundsException

        System.out.println(cities);

        // [New York, Moscow, Tokio]
        cities.set(0, "Tyumen");

        System.out.println(cities); // [Tyumen, Moscow, Tokio]

        // Удаление remove()
        cities.remove(1);

        System.out.println(cities); // [Tyumen, Tokio]

        cities.remove("Tokio");

        System.out.println(cities); // [Tyumen]

        System.out.println("cities.size() = " + cities.size()); // 1

        // [Tyumen] [] [] [] [] [] [] [] [] [] <- capacity

        System.out.println("MAX INDEX = " + (cities.size() - 1)); // cities.size() = 1
        // cities.set(3, "Tokio"); // IndexOutOfBoundsException

        System.out.println(cities); // [Tyumen]

        System.out.println("Есть ли Tyumen? " + cities.contains("Tyumen")); // Есть ли Tyumen? true

        Object[] arrayString = cities.toArray();
        System.out.println(Arrays.toString(arrayString));

        cities.clear();
        System.out.println(cities.size());



    }

}
