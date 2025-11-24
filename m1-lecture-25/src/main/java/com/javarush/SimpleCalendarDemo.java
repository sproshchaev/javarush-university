package com.javarush;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SimpleCalendarDemo {

    public static void main(String[] args) {

        // Получение среза времени
        Calendar calendar = Calendar.getInstance();
        System.out.println("Текущая дата: " + calendar.getTime()); // Текущая дата: Mon Nov 24 20:42:38 YEKT 2025

        System.out.println("Тип календаря: " + calendar.getCalendarType()); // gregory

        // Создание конкретной даты                           // 11 - декабрь
        Calendar calendar2 = new GregorianCalendar(2025, 11, 25, 14, 20, 15);
        System.out.println("calendar2: " + calendar2.getTime()); // Thu Dec 25 14:20:15 YEKT 2025

        // Получение частей
        System.out.println("Год: " + calendar.get(Calendar.YEAR));          // Год: 2025
        System.out.println("Месяц: " + calendar.get(Calendar.MONTH));       // Месяц: 10 (+1)
        System.out.println("День: " + calendar.get(Calendar.DAY_OF_MONTH)); // День: 24
        System.out.println("Часы: " + calendar.get(Calendar.HOUR_OF_DAY));  // Часы: 20

    }

}
