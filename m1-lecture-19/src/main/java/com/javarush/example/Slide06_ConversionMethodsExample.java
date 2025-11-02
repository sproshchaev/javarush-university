package com.javarush.example;

/**
 * Пример использования методов преобразования enum:
 * - toString() — объект → строка
 * - valueOf() — строка → объект
 * - ordinal() — объект → число
 * - values()[index] — число → объект
 * Также демонстрирует особенность сравнения через ==.
 */
public class Slide06_ConversionMethodsExample {

    public static void main(String[] args) {
        System.out.println("=== Преобразование в строку ===");
        Day today = Day.WEDNESDAY;
        String dayName = today.toString(); // Используется переопределённый toString()
        System.out.println("Сегодня: " + dayName); // Среда

        // По умолчанию (без переопределения)
        String defaultName = Day.MONDAY.name(); // Всегда возвращает имя константы
        System.out.println("Имя константы MONDAY: " + defaultName); // MONDAY

        System.out.println("\n=== Строка → enum (valueOf) ===");
        try {
            Day parsedDay = Day.valueOf("FRIDAY");
            System.out.println("Распознано: " + parsedDay); // Пятница

            // Ошибка при неверном имени
            Day invalidDay = Day.valueOf("friday"); // Бросит IllegalArgumentException!
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: Неверное имя константы enum!");
        }

        System.out.println("\n=== Число → enum (через values()) ===");
        int index = 3;
        Day dayByIndex = Day.values()[index];
        System.out.println("День под индексом " + index + ": " + dayByIndex); // Четверг

        System.out.println("\n=== Получение порядкового номера (ordinal) ===");
        System.out.println("Day.MONDAY.ordinal() = " + Day.MONDAY.ordinal());   // 0
        System.out.println("Day.SUNDAY.ordinal() = " + Day.SUNDAY.ordinal());   // 6

        System.out.println("\n=== Сравнение объектов enum через == ===");
        Day day1 = Day.MONDAY;
        Day day2 = Day.valueOf("MONDAY");
        Day day3 = Day.values()[0];

        System.out.println("day1 == day2? " + (day1 == day2)); // true — один и тот же объект!
        System.out.println("day1 == day3? " + (day1 == day3)); // true — тоже один и тот же объект!

        // Даже если создать новые переменные — это всё равно ссылки на один объект
        System.out.println("Все три переменные указывают на один объект: " +
                (day1 == day2 && day2 == day3));
    }
}