package com.javarush;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo {

    public static void main(String[] args) {

        // Как воспользоваться форматером?
        // (1) определяем дату
        Date date = new Date();
        // (2) задаем форматер
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        // (3) в аргумент метода .format передать нашу дату
        System.out.println("Форматированная дата: " + simpleDateFormat.format(date)); // Форматированная дата: 24.11.2025 20:37:31

        // Еще вариант
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEEE, d MMMM"); // понедельник, 24 ноября
        System.out.println(simpleDateFormat2.format(date));

    }

}
