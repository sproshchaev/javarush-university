package com.javarush.live;

import java.util.List;
import java.util.stream.Stream;

public class LazyStreamLive {

    public static void main(String[] args) {

        List<String> names = List.of("анна", "иван", "мария", "петр");

        System.out.println("Собираем цепочку");
        Stream<String> firstTwo = names.stream()
                .peek(name -> System.out.println("Прошел через peek: " + name))
                .limit(2);

        System.out.println("Запускаем forEach");
        firstTwo.forEach(name -> System.out.println("Результат:" + name));
        
    }

}
