package com.javarush;

import java.time.Instant;

public class SimpleInstant {

    public static void main(String[] args) {

        Instant instant = Instant.now();
        System.out.println("Машинное время: " + instant); // 2025-11-24T16:28:24.045533Z
        System.out.println("Секунды с 1970: " + instant.getEpochSecond()); // 1764001748
        System.out.println("Наносекунды: " + instant.getNano()); // 898109000

        Instant fromMs = Instant.ofEpochMilli(111133333L);
        System.out.println("Из мс: " + fromMs); // 1970-01-02T06:52:13.333Z

        //  todo дописать примеры остальных методов Instant

    }

}
