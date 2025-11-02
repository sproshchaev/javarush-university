package com.javarush.example;

public class Slide03_DaysOfWeekExample {

    public static void main(String[] args) {
        // Создание переменной типа enum
        DayOfWeek сегодня = DayOfWeek.ЧЕТВЕРГ;

        // Вывод значения
        System.out.println("Сегодня: " + сегодня); // ЧЕТВЕРГ

        // Получение русского названия
        System.out.println("По-русски: " + сегодня.getRussianName());

        // Проверка, рабочий ли день
        System.out.println("Рабочий день? " + сегодня.isWorkingDay());

        // Перебор всех значений enum
        System.out.println("\nВсе дни недели:");
        for (DayOfWeek day : DayOfWeek.values()) {
            System.out.println(day + " — " + day.getRussianName() +
                    " (рабочий: " + day.isWorkingDay() + ")");
        }
    }
}

