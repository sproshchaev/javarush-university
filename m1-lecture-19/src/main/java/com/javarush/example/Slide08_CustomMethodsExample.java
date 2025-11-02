package com.javarush.example;

import java.util.*;

/**
 * Пример добавления собственных методов в enum.
 * Демонстрирует:
 * - необходимость точки с запятой после списка значений
 * - создание статического метода, возвращающего List
 * - создание экземплярного метода, возвращающего описание
 * - использование методов в коде
 */
enum Season {
    SPRING, SUMMER, AUTUMN, WINTER;

    // Точка с запятой обязательна, если есть методы

    // Статический метод — возвращает все сезоны как List
    public static List<Season> asList() {
        return new ArrayList<>(Arrays.asList(values()));
    }

    // Экземплярный метод — возвращает описание сезона
    public String getDescription() {
        return switch (this) {
            case SPRING -> "Весна — природа просыпается";
            case SUMMER -> "Лето — пора отдыха и солнца";
            case AUTUMN -> "Осень — пора сбора урожая";
            case WINTER -> "Зима — время уютного отдыха";
        };
    }

    // Статический метод — фильтрация тёплых сезонов
    public static List<Season> getWarmSeasons() {
        List<Season> warm = new ArrayList<>();
        for (Season season : values()) {
            if (season == SPRING || season == SUMMER || season == AUTUMN) {
                warm.add(season);
            }
        }
        return warm;
    }
}

public class Slide08_CustomMethodsExample {

    public static void main(String[] args) {
        System.out.println("=== Использование пользовательских методов enum ===");

        // Получаем список всех сезонов
        List<Season> allSeasons = Season.asList();
        System.out.println("Все сезоны:");
        for (Season season : allSeasons) {
            System.out.println(season + " — " + season.getDescription());
        }

        System.out.println("\n=== Тёплые сезоны ===");
        List<Season> warmSeasons = Season.getWarmSeasons();
        for (Season season : warmSeasons) {
            System.out.println(season);
        }

        System.out.println("\n=== Описание текущего сезона ===");
        Season current = Season.SUMMER;
        System.out.println(current + ": " + current.getDescription());

        // Проверка, что методы работают корректно
        System.out.println("\nКоличество тёплых сезонов: " + warmSeasons.size()); // 3
    }
}