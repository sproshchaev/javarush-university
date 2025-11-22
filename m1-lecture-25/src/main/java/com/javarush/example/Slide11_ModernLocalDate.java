package com.javarush.example;

import java.time.LocalDate;
import java.time.Month;
import java.time.DayOfWeek;

public class Slide11_ModernLocalDate {
    public static void main(String[] args) {
        System.out.println("=== 1. Создание LocalDate (Слайд 11) ===");

        // Текущая дата
        LocalDate today = LocalDate.now();
        System.out.println("Сегодня: " + today);

        // Конкретная дата - месяцы с 1!
        LocalDate christmas = LocalDate.of(2025, 12, 25);
        System.out.println("Рождество 2025: " + christmas);

        // С использованием enum Month
        LocalDate newYear = LocalDate.of(2025, Month.JANUARY, 1);
        System.out.println("Новый год 2025: " + newYear);

        System.out.println("\n=== 2. Получение фрагментов даты (Слайд 12) ===");
        System.out.println("Дата для анализа: " + christmas);

        System.out.println("Год: " + christmas.getYear());
        System.out.println("Месяц (число): " + christmas.getMonthValue());
        System.out.println("Месяц (enum): " + christmas.getMonth());
        System.out.println("День месяца: " + christmas.getDayOfMonth());
        System.out.println("День недели: " + christmas.getDayOfWeek());
        System.out.println("День года: " + christmas.getDayOfYear());
        System.out.println("Эра: " + christmas.getEra());

        System.out.println("\n=== 3. Изменение даты (Слайд 13) ===");
        LocalDate originalDate = LocalDate.of(2025, 6, 15);
        System.out.println("Исходная дата: " + originalDate);

        // Plus методы
        LocalDate plusWeek = originalDate.plusWeeks(1);
        System.out.println("+1 неделя: " + plusWeek);

        LocalDate plusMonth = originalDate.plusMonths(2);
        System.out.println("+2 месяца: " + plusMonth);

        // Minus методы
        LocalDate minusDays = originalDate.minusDays(10);
        System.out.println("-10 дней: " + minusDays);

        // Цепочка вызовов
        LocalDate complexChange = originalDate.plusYears(1).minusMonths(3).plusDays(5);
        System.out.println("+1 год, -3 месяца, +5 дней: " + complexChange);

        // Демонстрация неизменяемости
        System.out.println("\nИсходная дата осталась неизменной: " + originalDate);

        System.out.println("\n=== ПРЕИМУЩЕСТВА LocalDate ===");
        System.out.println("1. Интуитивный API (месяцы с 1!)");
        System.out.println("2. Неизменяемость (immutable)");
        System.out.println("3. Потокобезопасность");
        System.out.println("4. Читаемые enum'ы для месяцев и дней недели");
    }
}