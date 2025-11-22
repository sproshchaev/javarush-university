package com.javarush.example;

import java.time.LocalTime;
import java.time.LocalDateTime;

public class Slide14_ModernTimeAndDateTime {
    public static void main(String[] args) {
        System.out.println("=== 1. Класс LocalTime - работа со временем (Слайд 14) ===");

        // Текущее время
        LocalTime currentTime = LocalTime.now();
        System.out.println("Сейчас время: " + currentTime);

        // Создание конкретного времени
        LocalTime meetingTime = LocalTime.of(14, 30); // 14:30
        System.out.println("Время встречи: " + meetingTime);

        LocalTime preciseTime = LocalTime.of(9, 15, 30, 500000000);
        System.out.println("Точное время: " + preciseTime);

        System.out.println("\n=== 2. Получение фрагментов времени (Слайд 15) ===");
        System.out.println("Анализ времени: " + preciseTime);
        System.out.println("Часы: " + preciseTime.getHour());
        System.out.println("Минуты: " + preciseTime.getMinute());
        System.out.println("Секунды: " + preciseTime.getSecond());
        System.out.println("Наносекунды: " + preciseTime.getNano());

        System.out.println("\n=== 3. Изменение времени (Слайд 16) ===");
        LocalTime startTime = LocalTime.of(9, 0);
        System.out.println("Начальное время: " + startTime);

        // Plus операции
        LocalTime plusHours = startTime.plusHours(2);
        System.out.println("+2 часа: " + plusHours);

        LocalTime plusComplex = startTime.plusHours(1).plusMinutes(30).plusSeconds(15);
        System.out.println("+1 час 30 мин 15 сек: " + plusComplex);

        // Minus операции
        LocalTime minusTime = startTime.minusMinutes(45);
        System.out.println("-45 минут: " + minusTime);

        // Демонстрация неизменяемости
        System.out.println("Исходное время не изменилось: " + startTime);

        System.out.println("\n=== 4. Класс LocalDateTime - дата и время (Слайд 17) ===");

        // Текущие дата и время
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Текущие дата и время: " + currentDateTime);

        // Создание конкретного момента
        LocalDateTime newYearEvent = LocalDateTime.of(2025, 12, 31, 23, 59, 59);
        System.out.println("Новогодняя ночь: " + newYearEvent);

        LocalDateTime meeting = LocalDateTime.of(2025, 6, 15, 14, 30);
        System.out.println("Встреча 15.06.2025 14:30: " + meeting);

        System.out.println("\n=== 5. Комбинирование LocalDate и LocalTime ===");
        LocalDateTime combined = LocalDateTime.of(2025, 12, 25, 18, 0);
        System.out.println("Комбинированная дата-время: " + combined);

        // Получение частей
        System.out.println("Год: " + combined.getYear());
        System.out.println("Месяц: " + combined.getMonthValue());
        System.out.println("День: " + combined.getDayOfMonth());
        System.out.println("Часы: " + combined.getHour());
        System.out.println("Минуты: " + combined.getMinute());

        System.out.println("\n=== ПРЕИМУЩЕСТВА Modern Time API ===");
        System.out.println("1. Разделение ответственности: LocalDate, LocalTime, LocalDateTime");
        System.out.println("2. Неизменяемость и потокобезопасность");
        System.out.println("3. Интуитивный API без 'магических чисел'");
        System.out.println("4. Поддержка высокоточного времени (наносекунды)");
    }
}