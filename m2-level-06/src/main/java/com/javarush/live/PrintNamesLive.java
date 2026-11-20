package com.javarush.live;

import java.util.List;
import java.util.function.Consumer;

public class PrintNamesLive {

    public static void main(String[] args) {

        List<String> names = List.of("анна", "иван", "мария", "петр");

        Consumer<String> printName = name -> {
            System.out.println("---");
            System.out.println("*" + name + "*");
            // System.out.println("---");
        };

        System.out.println("Лямбда: ");
        names.forEach(printName);

        System.out.println("ссылка на метод: ");
        names.forEach(System.out::print);

    }

}
