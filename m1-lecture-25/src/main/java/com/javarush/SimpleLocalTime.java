package com.javarush;

import java.time.LocalDate;
import java.time.LocalTime;

public class SimpleLocalTime {

    public static void main(String[] args) {

        LocalTime now = LocalTime.now();
        System.out.println("Сейчас: " + now); // 21:14:25.830613

        LocalTime meetingTime = LocalTime.of(14, 30);
        System.out.println("Встреча в " + meetingTime); // Встреча в 14:30

        LocalTime precise = LocalTime.of(14, 30, 14, 44444444);
        System.out.println("Точное время: " + precise); // 14:30:14.044444444

        // Извлечение
        System.out.println("Часы: " + now.getHour());
        System.out.println("Минуты: " + now.getMinute());
        System.out.println("Секунды: " + now.getSecond());
        System.out.println("Наносек: " + now.getNano());

        // Задание времени
        LocalTime time = LocalTime.of(9, 0);
        LocalTime meetingTime2 = time.plusHours(1).minusMinutes(30);
        System.out.println(meetingTime2); // 09:30

        // todo сформировать LocalTime используя ZoneId для своего места жительства :)


    }

}
