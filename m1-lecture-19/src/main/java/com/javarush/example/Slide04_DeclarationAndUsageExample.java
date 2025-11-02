package com.javarush.example;

public class Slide04_DeclarationAndUsageExample {

    public static void main(String[] args) {
        // Объявление переменной типа enum и присваивание значения
        Day today = Day.FRIDAY;
        System.out.println("Сегодня: " + today); // Пятница (благодаря переопределённому toString)

        // Присваиваем другое значение
        today = Day.SATURDAY;
        System.out.println("А завтра: " + today);

        // Вывод значения по умолчанию (имя константы)
        System.out.println("Имя константы: " + today.name()); // SATURDAY

        // Перебор всех значений enum
        System.out.println("\nВсе дни недели:");
        for (Day day : Day.values()) {
            System.out.println(day.name() + " → " + day);
        }

        // Сравнение значений enum
        if (today == Day.SATURDAY) {
            System.out.println("\nУра, выходной!");
        }
    }
}