package com.javarush;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleParsing {

    public static void main(String[] args) {

        // Парсинг для создания даты из строки
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate now = LocalDate.parse("01.01.2020", formatter);

        System.out.println(now.format(formatter)); // 01.01.2020

    }

}
