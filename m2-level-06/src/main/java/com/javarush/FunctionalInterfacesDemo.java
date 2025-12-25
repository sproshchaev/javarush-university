package com.javarush;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfacesDemo {

    public static void main(String[] args) {

        List<String> words = Arrays.asList("Привет", "как", "дела?");

        // Функциональный интерфейс - это интерфейс с одним методом!

        // 1) Consumer через лямбду void accept(T t);
        Consumer<String> printer = s -> System.out.println(s);
        words.forEach(printer);

        // Ссылка на метод ::
        words.forEach(System.out::println);

        // 2) Predicate - boolean test(T t)
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(4)); // true

        // 3) Supplier - T get();
        Supplier<Integer> getInt = () -> 10;
        System.out.println(getInt.get()); // 10

        // Function - R apply(T t);
        Function<String, Integer> lenFunc = s -> s.length();
        System.out.println(lenFunc.apply("Java")); // 4

    }

}
