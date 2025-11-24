package com.javarush;

import java.util.Date;

public class SimpleDateParts {

    public static void main(String[] args) {

        Date date = new Date(); // срез времени

        // Части даты:
        System.out.println("Год: " + (date.getYear() + 1900)); // Год: 2025
        System.out.println("Месяц: " + (date.getMonth() + 1)); // Месяц: 11
        System.out.println("День: " + (date.getDate()));       // День: 24

        // Изменить дату
        date.setYear(126); // 2026
        date.setMonth(0);  // январь
        System.out.println("После изменения: " + date);

        for (int i = 0; i <= 11; i++) {
            date.setMonth(i);
            System.out.println(date);
        }

        // todo Написать метод, который принимает дату 24.11.2025
        //  и мне должно вернуться число дней с начала года

        // todo Второй метод - вернуть число недель с начала года :)

        // todo Третий метод - получить в аргументах 2 даты и вернуть число
        // дней между ними

    }

}
