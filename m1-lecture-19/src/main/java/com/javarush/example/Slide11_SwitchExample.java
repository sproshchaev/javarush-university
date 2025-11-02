package com.javarush.example;

/**
 * Пример использования оператора switch в Java.
 * Демонстрирует:
 * - базовый синтаксис switch
 * - важность оператора break (и эффект fall-through)
 * - использование блока default как аналога else
 */
public class Slide11_SwitchExample {

    public static void main(String[] args) {
        System.out.println("=== Пример 1: Базовый switch ===");
        int dayNumber = 4;
        switch (dayNumber) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
                System.out.println("Вторник");
                break;
            case 3:
                System.out.println("Среда");
                break;
            case 4:
                System.out.println("Четверг");
                break;
            case 5:
                System.out.println("Пятница");
                break;
            case 6:
                System.out.println("Суббота");
                break;
            case 7:
                System.out.println("Воскресенье");
                break;
            default:
                System.out.println("Неверный номер дня: " + dayNumber);
        }

        System.out.println("\n=== Пример 2: Эффект fall-through (без break) ===");
        int level = 2;
        switch (level) {
            case 1:
                System.out.println("Уровень 1: Базовые навыки");
            case 2:
                System.out.println("Уровень 2: Средний уровень");
            case 3:
                System.out.println("Уровень 3: Профессионал");
                break;
            default:
                System.out.println("Неизвестный уровень");
        }
        // Вывод: Уровень 2, Уровень 3 — потому что нет break в case 1 и 2

        System.out.println("\n=== Пример 3: Использование default ===");
        String fruit = "ананас";
        switch (fruit) {
            case "яблоко":
                System.out.println("Это яблоко — сладкое и хрустящее.");
                break;
            case "банан":
                System.out.println("Это банан — мягкий и сладкий.");
                break;
            case "апельсин":
                System.out.println("Это апельсин — кисло-сладкий.");
                break;
            default:
                System.out.println("Неизвестный фрукт: " + fruit + ". Попробуйте другой.");
        }

        System.out.println("\n=== Пример 4: switch с enum ===");
        DayOfWeek today = DayOfWeek.WEDNESDAY;
        switch (today) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                System.out.println("Рабочий день: " + today);
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("Выходной день: " + today);
                break;
        }
    }

    // Вспомогательный enum для примера 4
    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }
}