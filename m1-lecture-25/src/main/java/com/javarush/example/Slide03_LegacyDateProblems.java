package com.javarush.example;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Slide03_LegacyDateProblems {
    public static void main(String[] args) {
        System.out.println("=== 1. Создание текущей даты (Слайд 3-4) ===");
        Date currentDate = new Date();
        System.out.println("Текущая дата (стандартный формат): " + currentDate);
        System.out.println("Внутреннее представление (мс): " + currentDate.getTime());

        System.out.println("\n=== 2. Проблемы создания конкретной даты (Слайд 5) ===");
        // Хотим создать 25 декабря 2025 года
        Date christmas2025 = new Date(125, 11, 25); // 2025-1900=125, декабрь=11
        System.out.println("Дата 25.12.2025 создана как: " + christmas2025);

        // Типичная ошибка - неправильная нумерация месяцев
        Date mistake = new Date(125, 1, 1); // Думаем, что это 1 января
        System.out.println("Ошибочная дата (1,1,1): " + mistake + " <- Это февраль!");

        System.out.println("\n=== 3. Работа с фрагментами даты (Слайд 6) ===");
        Date date = new Date(125, 11, 25, 14, 30, 15);
        System.out.println("Исходная дата: " + date);

        // Получение частей с коррекцией
        System.out.println("Год: " + (date.getYear() + 1900));
        System.out.println("Месяц: " + (date.getMonth() + 1));
        System.out.println("День: " + date.getDate());
        System.out.println("Часы: " + date.getHours());

        // Изменение даты (мутабельность - еще одна проблема)
        date.setYear(126); // 2026 год
        date.setMonth(0);  // январь
        System.out.println("После изменения: " + date);

        System.out.println("\n=== 4. Форматирование даты (Слайд 7) ===");
        SimpleDateFormat technicalFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        SimpleDateFormat humanFormat = new SimpleDateFormat("EEEE, d MMMM yyyy 'года'");
        SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");

        System.out.println("Технический формат: " + technicalFormat.format(currentDate));
        System.out.println("Человеческий формат: " + humanFormat.format(currentDate));
        System.out.println("Полный формат: " + fullFormat.format(currentDate));

        System.out.println("\n=== ВЫВОД: Date устарел из-за:");
        System.out.println("1. Неинтуитивная нумерация (год от 1900, месяцы с 0)");
        System.out.println("2. Мутабельность объектов");
        System.out.println("3. Плохой дизайн API");
    }
}