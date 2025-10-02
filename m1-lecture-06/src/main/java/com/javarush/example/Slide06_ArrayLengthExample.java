package com.javarush.example;

public class Slide06_ArrayLengthExample {

    public static void main(String[] args) {
        System.out.println("=== Длина массива ===\n");

        // Создаём массив из 10 элементов
        int[] array = new int[10];

        // Выводим длину массива
        System.out.println("Длина массива: " + array.length);

        // Пример условия: если длина меньше 10 — создаём новый
        if (array.length < 10) {
            array = new int[10];
            System.out.println("Создан массив из 10 элементов.");
        } else {
            array = new int[20];
            System.out.println("Создан массив из 20 элементов.");
        }

        System.out.println("Новая длина массива: " + array.length + "\n");

        // Заполняем массив значениями
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 2; // записываем чётные числа
        }

        // Выводим все элементы с помощью length
        System.out.println("Элементы массива:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("\n");

        // Пример с разными типами
        String[] names = {"Анна", "Борис", "Виктория"};
        System.out.println("Массив имён: длина = " + names.length);
        for (int i = 0; i < names.length; i++) {
            System.out.println("Имя #" + (i + 1) + ": " + names[i]);
        }

        // Попытка изменить length — вызовет ошибку компиляции!
        // array.length = 15; // ❌ Так нельзя — length только для чтения!

        System.out.println("\n--- Сравнение с String.length() ---");
        String text = "Hello";
        System.out.println("Длина строки: " + text.length()); // Метод!
        System.out.println("Длина массива: " + array.length); // Поле!
    }
}

