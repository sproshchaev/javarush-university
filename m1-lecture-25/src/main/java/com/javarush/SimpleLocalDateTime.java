package com.javarush;

import java.time.LocalDateTime;
import java.time.Month;

public class SimpleLocalDateTime {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime now2 = LocalDateTime.of(2025, Month.DECEMBER, 31, 23, 59, 59);
        System.out.println(now2); // 2025-12-31T23:59:59
    }

}
