package com.javarush.example;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class Slide18_InstantAndZonedDateTime {
    public static void main(String[] args) {
        System.out.println("=== 1. Класс Instant - машинное время (Слайд 18) ===");

        // Текущее машинное время
        Instant now = Instant.now();
        System.out.println("Instant сейчас: " + now);
        System.out.println("Секунды с 1970: " + now.getEpochSecond());
        System.out.println("Наносекунды: " + now.getNano());
        System.out.println("Миллисекунды: " + now.toEpochMilli());

        System.out.println("\n=== 2. Создание Instant из эпохи (Слайд 19) ===");

        // Из миллисекунд
        Instant fromMillis = Instant.ofEpochMilli(1734987600000L);
        System.out.println("Из миллисекунд (1734987600000): " + fromMillis);

        // Из секунд
        Instant fromSeconds = Instant.ofEpochSecond(1734987600L);
        System.out.println("Из секунд (1734987600): " + fromSeconds);

        // Из секунд и наносекунд
        Instant precise = Instant.ofEpochSecond(1734987600L, 500000000L);
        System.out.println("С наносекундами: " + precise);

        System.out.println("\n=== 3. Операции с Instant (Слайд 20) ===");

        Instant original = Instant.ofEpochSecond(1734987600L);
        System.out.println("Исходное: " + original);

        // Plus операции
        Instant plusHour = original.plusSeconds(3600); // +1 час
        System.out.println("+1 час: " + plusHour);

        Instant plusDay = original.plusSeconds(24 * 3600); // +1 день
        System.out.println("+1 день: " + plusDay);

        // Minus операции
        Instant minus30min = original.minusSeconds(1800); // -30 минут
        System.out.println("-30 минут: " + minus30min);

        System.out.println("\n=== 4. Часовые пояса (Слайд 21) ===");

        Set<String> zones = ZoneId.getAvailableZoneIds();
        System.out.println("Доступно часовых зон: " + zones.size());

        // Примеры популярных зон
        System.out.println("Europe/Moscow: " + ZoneId.of("Europe/Moscow"));
        System.out.println("America/New_York: " + ZoneId.of("America/New_York"));
        System.out.println("Asia/Tokyo: " + ZoneId.of("Asia/Tokyo"));
        System.out.println("Europe/London: " + ZoneId.of("Europe/London"));

        System.out.println("\n=== 5. Класс ZonedDateTime (Слайд 22) ===");

        // Текущее время в разных зонах
        ZonedDateTime localTime = ZonedDateTime.now();
        System.out.println("Локальная зона: " + localTime);

        ZonedDateTime tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("Токио: " + tokyoTime);

        ZonedDateTime newYorkTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("Нью-Йорк: " + newYorkTime);

        ZonedDateTime londonTime = ZonedDateTime.now(ZoneId.of("Europe/London"));
        System.out.println("Лондон: " + londonTime);

        System.out.println("\n=== 6. Сравнение Instant и ZonedDateTime ===");

        Instant machineTime = Instant.now();
        ZonedDateTime humanTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));

        System.out.println("Машинное время (Instant): " + machineTime);
        System.out.println("Человеческое время (ZonedDateTime): " + humanTime);

        // Конвертация между ними
        Instant fromZoned = humanTime.toInstant();
        ZonedDateTime fromInstant = machineTime.atZone(ZoneId.of("Europe/Moscow"));

        System.out.println("ZonedDateTime -> Instant: " + fromZoned);
        System.out.println("Instant -> ZonedDateTime: " + fromInstant);

        System.out.println("\n=== ПРЕИМУЩЕСТВА ===");
        System.out.println("Instant: точное машинное время для системных задач");
        System.out.println("ZonedDateTime: работа с часовыми поясами для пользователей");
        System.out.println("Полная поддержка глобальных приложений");
    }
}