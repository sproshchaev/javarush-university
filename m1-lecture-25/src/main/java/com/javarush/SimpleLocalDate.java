package com.javarush;

import java.time.LocalDate;
import java.time.Month;

public class SimpleLocalDate {

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        System.out.println("Сегодня: " + today); // Сегодня: 2025-11-24

        LocalDate date = LocalDate.of(2025, 11, 25);
        System.out.println(date); // 2025-11-25

        LocalDate date2 = LocalDate.of(2025, Month.NOVEMBER, 25); // 2025-11-25
        System.out.println(date2);

        System.out.println("Год: " + date.getYear()); // Год: 2025
        System.out.println("Месяц: " + date.getMonth()); // Месяц: NOVEMBER
        System.out.println("День: " + date.getDayOfMonth()); // День: 25
        System.out.println("День недели: " + date.getDayOfWeek()); // День недели: TUESDAY

        // Задавать даты через диапазон от 2025-11-25
        LocalDate date3 = date.plusDays(7);
        System.out.println(date3); // 2025-12-02

        // Построение цепочки изменения даты
        LocalDate date4 = date3.plusDays(3).minusMonths(1);
        System.out.println(date4); // 2025-11-05




    }

}
