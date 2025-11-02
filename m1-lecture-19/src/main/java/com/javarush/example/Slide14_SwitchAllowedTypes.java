package com.javarush.example;

/**
 * Пример использования допустимых типов в операторе switch.
 * Демонстрирует:
 * - использование int, char, String, enum в switch
 * - попытку использовать недопустимые типы (с комментариями)
 * - объяснение, почему некоторые типы не поддерживаются
 */
public class Slide14_SwitchAllowedTypes {

    public static void main(String[] args) {
        System.out.println("=== Допустимые типы в switch ===");

        // 1. Целочисленный тип: int
        int age = 18;
        switch (age) {
            case 16: System.out.println("Вам 16 лет — можно получить водительские права (в некоторых странах)."); break;
            case 18: System.out.println("Вам 18 лет — вы взрослый!"); break;
            case 21: System.out.println("Вам 21 год — можно покупать алкоголь (в США)."); break;
            default: System.out.println("Ваш возраст: " + age);
        }

        // 2. Символьный тип: char
        char grade = 'B';
        switch (grade) {
            case 'A': System.out.println("Отлично!"); break;
            case 'B': System.out.println("Хорошо!"); break;
            case 'C': System.out.println("Удовлетворительно."); break;
            case 'D': System.out.println("Плохо."); break;
            case 'F': System.out.println("Неудовлетворительно."); break;
            default: System.out.println("Неизвестная оценка: " + grade);
        }

        // 3. Строковый тип: String (Java 7+)
        String fruit = "apple";
        switch (fruit) {
            case "apple":
                System.out.println("Это яблоко — сладкое и хрустящее.");
                break;
            case "banana":
                System.out.println("Это банан — мягкий и сладкий.");
                break;
            case "orange":
                System.out.println("Это апельсин — кисло-сладкий.");
                break;
            default:
                System.out.println("Неизвестный фрукт: " + fruit);
        }

        // 4. Enum-тип
        Season currentSeason = Season.SUMMER;
        switch (currentSeason) {
            case SPRING:
                System.out.println("Весна — природа просыпается.");
                break;
            case SUMMER:
                System.out.println("Лето — пора отдыха и солнца.");
                break;
            case AUTUMN:
                System.out.println("Осень — пора сбора урожая.");
                break;
            case WINTER:
                System.out.println("Зима — время уютного отдыха.");
                break;
        }

        // ❌ НЕЛЬЗЯ: long, double, boolean
        // long bigNumber = 100L;
        // switch (bigNumber) { } // ОШИБКА КОМПИЛЯЦИИ

        // double price = 9.99;
        // switch (price) { } // ОШИБКА КОМПИЛЯЦИИ

        // boolean flag = true;
        // switch (flag) { } // ОШИБКА КОМПИЛЯЦИИ

        System.out.println("\n✅ Все примеры выше работают корректно.");

        // Дополнительно: показываем, что case должен быть константой
        final int MAX_AGE = 100; // final — константа времени компиляции
        switch (age) {
            case MAX_AGE: System.out.println("Вы достигли максимального возраста."); break;
            default: System.out.println("Ваш возраст меньше 100.");
        }

        // int dynamicValue = 5; // Не final — не константа!
        // case dynamicValue: ... // ОШИБКА — нельзя использовать переменную
    }

    // Вспомогательные enum
    enum Season {
        SPRING, SUMMER, AUTUMN, WINTER;
    }
}