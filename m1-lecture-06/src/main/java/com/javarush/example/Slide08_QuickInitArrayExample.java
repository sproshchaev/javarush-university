package com.javarush.example;

public class Slide08_QuickInitArrayExample {

    public static void main(String[] args) {
        System.out.println("=== Быстрая инициализация массива ===\n");

        // Пример: дни в месяцах года
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        System.out.println("Количество дней в каждом месяце:");
        for (int i = 0; i < daysInMonth.length; i++) {
            System.out.println("Месяц " + (i + 1) + ": " + daysInMonth[i] + " дней");
        }
        System.out.println();

        // Пример: названия месяцев
        String[] monthNames = {
                "Январь", "Февраль", "Март", "Апрель",
                "Май", "Июнь", "Июль", "Август",
                "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };

        System.out.println("Названия месяцев:");
        for (int i = 0; i < monthNames.length; i++) {
            System.out.println((i + 1) + ". " + monthNames[i]);
        }
        System.out.println();

        // Пример: булевы значения
        boolean[] flags = {true, false, true, true, false};
        System.out.println("Массив флагов:");
        for (int i = 0; i < flags.length; i++) {
            System.out.println("Флаг " + (i + 1) + ": " + flags[i]);
        }
        System.out.println();

        // Пример: числа с плавающей точкой
        double[] prices = {99.99, 150.50, 29.99, 499.00};
        System.out.println("Цены товаров:");
        for (int i = 0; i < prices.length; i++) {
            System.out.printf("Товар %d: %.2f руб.%n", i + 1, prices[i]);
        }

        // Попытка изменить размер — невозможно!
        // daysInMonth = {30, 30, 30}; // ❌ Ошибка компиляции — нельзя так перезаписать

        System.out.println("\n--- Важно: размер массива фиксирован! ---");
        System.out.println("Размер массива daysInMonth: " + daysInMonth.length);
    }
}