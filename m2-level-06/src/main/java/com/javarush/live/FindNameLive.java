package com.javarush.live;

import java.util.List;
import java.util.Optional;

public class FindNameLive {

    public static void main(String[] args) {

        List<String> names = List.of("анна", "иван", "мария", "петр");

        Optional<String> longerThanFour = names.stream()
                .filter(name -> name.length() > 4)
                .findFirst();

        Optional<String> longerThanTen = names.stream()
                .filter(name -> name.length() > 10)
                .findFirst();

        System.out.println("Длиннее четырех: " + longerThanFour.orElse("совпадений нет"));
        System.out.println("Длиннее десяти: " + longerThanTen.orElse("совпадений нет"));

    }

}
