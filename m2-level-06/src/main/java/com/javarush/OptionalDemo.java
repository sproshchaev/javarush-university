package com.javarush;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {

        Optional<String> emptyOpt = Optional.empty();            // Пустой
        Optional<String> fullOpt = Optional.of("Привет!"); // не null

        Optional<String> nullableOpt = Optional.ofNullable(null); // может быть null

        System.out.println(emptyOpt.isPresent()); // false
        System.out.println(fullOpt.isPresent()); // true
        System.out.println(nullableOpt.isPresent()); // false

        // Получить значение
        if (fullOpt.isPresent()) {
            String value = fullOpt.get();
            System.out.println("Значение: " + value); // Значение: Привет!
        }

        // безопасный способ с значением по умолчанию
        String result = nullableOpt.orElse("Значение по умолчанию");
        System.out.println("Результат: " + result); // Результат: Значение по умолчанию
    }

}
