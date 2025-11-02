package com.javarush.example;

/**
 * Пример использования встроенных методов enum: values() и ordinal().
 * Демонстрирует:
 * - получение массива всех значений через values()
 * - получение порядкового номера через ordinal()
 * - использование в цикле и индексировании
 */
public class Slide05_EnumMethodsExample {

    public static void main(String[] args) {
        System.out.println("=== Использование метода values() ===");
        Day[] allDays = Day.values();
        System.out.println("Всего дней недели: " + allDays.length);

        System.out.println("\nПеребор всех дней:");
        for (Day day : allDays) {
            System.out.println(day + " — порядковый номер: " + day.ordinal());
        }

        System.out.println("\n=== Использование метода ordinal() ===");
        System.out.println("Day.MONDAY.ordinal() = " + Day.MONDAY.ordinal());   // 0
        System.out.println("Day.FRIDAY.ordinal() = " + Day.FRIDAY.ordinal());   // 4
        System.out.println("Day.SUNDAY.ordinal() = " + Day.SUNDAY.ordinal());   // 6

        System.out.println("\n=== Доступ по индексу ===");
        System.out.println("allDays[2] = " + allDays[2]); // WEDNESDAY
        System.out.println("allDays[5] = " + allDays[5]); // SATURDAY

        // Пример: поиск дня по порядковому номеру
        int targetIndex = 3;
        if (targetIndex >= 0 && targetIndex < allDays.length) {
            Day foundDay = allDays[targetIndex];
            System.out.println("\nДень под индексом " + targetIndex + ": " + foundDay);
        }
    }
}