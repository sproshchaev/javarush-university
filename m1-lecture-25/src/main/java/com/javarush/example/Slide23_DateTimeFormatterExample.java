package com.javarush.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Slide23_DateTimeFormatterExample {
    public static void main(String[] args) {
        System.out.println("=== 1. Базовое форматирование (Слайд 23) ===");

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Исходная дата-время: " + now);

        // Простые шаблоны
        DateTimeFormatter basicFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String basicFormatted = now.format(basicFormatter);
        System.out.println("Базовый формат: " + basicFormatted);

        System.out.println("\n=== 2. Различные шаблоны форматирования (Слайды 24-25) ===");

        // Год, месяц, день
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Только дата: " + now.format(dateFormatter));

        // Время
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Только время: " + now.format(timeFormatter));

        // Полный формат с текстом
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy 'года' HH:mm:ss");
        System.out.println("Полный текст: " + now.format(fullFormatter));

        // С миллисекундами
        DateTimeFormatter msFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        System.out.println("С миллисекундами: " + now.format(msFormatter));

        // Разные варианты месяцев
        DateTimeFormatter monthShort = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter monthFull = DateTimeFormatter.ofPattern("MMMM dd yyyy");
        System.out.println("Месяц коротко: " + now.format(monthShort));
        System.out.println("Месяц полностью: " + now.format(monthFull));

        // 12-часовой формат
        DateTimeFormatter amPmFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.println("12-часовой формат: " + now.format(amPmFormatter));

        System.out.println("\n=== 3. Парсинг строк в даты (Слайд 26) ===");

        // Парсинг даты
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedDate = LocalDate.parse("25/12/2025", parser);
        System.out.println("Распарсена дата: " + parsedDate);

        // Парсинг даты-времени
        DateTimeFormatter dateTimeParser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parsedDateTime = LocalDateTime.parse("2025-12-25 14:30", dateTimeParser);
        System.out.println("Распарсена дата-время: " + parsedDateTime);

        // Парсинг времени
        DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime parsedTime = LocalTime.parse("18:45:30", timeParser);
        System.out.println("Распарсено время: " + parsedTime);

        System.out.println("\n=== 4. Сложные случаи парсинга ===");

        // Парсинг с текстовыми месяцами
        DateTimeFormatter textMonthParser = DateTimeFormatter.ofPattern("d MMMM yyyy");
        LocalDate textDate = LocalDate.parse("25 декабря 2025", textMonthParser);
        System.out.println("Текстовый месяц: " + textDate);

        // Парсинг с разными разделителями
        DateTimeFormatter customParser = DateTimeFormatter.ofPattern("yyyy*MM*dd");
        LocalDate customDate = LocalDate.parse("2025*12*25", customParser);
        System.out.println("Кастомные разделители: " + customDate);

        System.out.println("\n=== 5. Работа с разными локалями ===");

        // Форматирование на английском
        DateTimeFormatter englishFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        System.out.println("Английский формат: " + now.format(englishFormatter));

        System.out.println("\n=== ПРЕИМУЩЕСТВА DateTimeFormatter ===");
        System.out.println("1. Потокобезопасность (в отличие от SimpleDateFormat)");
        System.out.println("2. Богатые возможности форматирования");
        System.out.println("3. Поддержка парсинга из строк");
        System.out.println("4. Работа со всеми классами modern Date-Time API");
        System.out.println("5. Гибкие шаблоны для любых форматов дат");
    }
}