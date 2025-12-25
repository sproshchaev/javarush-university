package com.javarush;

import java.util.stream.Stream;

public class CreateStreamsDemo {

    public static void main(String[] args) {

        // 1) Поток из элементов
        Stream<String> stream1 = Stream.of ("a", "b", "c");
        System.out.println("Число элементов потока: " + stream1.count());

        // 2) Generate
        Stream<Double> stream2 = Stream.generate(Math::random).limit(3);
        stream2.forEach(System.out::println);

        // 3) Объединение потоков
        Stream<String> streamA = Stream.of ("a", "b", "c");
        Stream<String> streamB = Stream.of ("1", "2", "3");
        Stream<String> streamC = Stream.concat(streamA, streamB);
        streamC.forEach(System.out::print);

    }

}
