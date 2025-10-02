package com.javarush.example;

public class Slide07_StringArrayExample {

    public static void main(String[] args) {
        System.out.println("=== Массив типа String ===\n");

        // Создаём массив строк из 3 элементов
        String[] names = new String[3];

        // Выводим значения по умолчанию
        System.out.println("Значения по умолчанию (все null):");
        for (int i = 0; i < names.length; i++) {
            System.out.println("names[" + i + "] = " + names[i]);
        }
        System.out.println();

        // Попытка вызвать метод на null — вызовет ошибку!
        try {
            System.out.println("Длина первого элемента: " + names[0].length());
        } catch (NullPointerException e) {
            System.out.println("⚠️ Ошибка: попытка вызвать метод на null!");
        }

        // Присваиваем значения
        names[0] = "Анна";
        names[1] = "Борис";
        names[2] = "Виктория";

        System.out.println("\nПосле присваивания:");
        for (int i = 0; i < names.length; i++) {
            System.out.println("names[" + i + "] = " + names[i] + " (длина: " + names[i].length() + ")");
        }
        System.out.println();

        // Инициализация при объявлении
        String[] colors = {"Красный", "Зелёный", "Синий"};
        System.out.println("Массив цветов:");
        for (int i = 0; i < colors.length; i++) {
            System.out.println("Цвет #" + (i + 1) + ": " + colors[i]);
        }
        System.out.println();

        // Пример: проверка на null перед использованием
        System.out.println("Безопасный вывод (проверка на null):");
        for (int i = 0; i < names.length; i++) {
            if (names[i] != null) {
                System.out.println("names[" + i + "] = " + names[i]);
            } else {
                System.out.println("names[" + i + "] = null (пусто)");
            }
        }
    }
}