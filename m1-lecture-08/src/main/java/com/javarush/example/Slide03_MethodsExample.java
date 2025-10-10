package com.javarush.example;

/**
 * Пример из слайда 3: "Методы в Java"
 *
 * Метод — это группа команд, у которой есть уникальное имя.
 * Использование методов позволяет:
 * - Упростить восприятие кода (лучше иметь много маленьких методов, чем один большой).
 * - Избежать дублирования кода.
 * - Повысить читаемость и поддерживаемость программы.
 */
public class Slide03_MethodsExample {

    public static void main(String[] args) {
        // ❌ Без метода — дублирование кода
        System.out.print("Wi-");
        System.out.println("Fi");
        System.out.print("Wi-");
        System.out.println("Fi");

        System.out.print("Wi-");
        System.out.println("Fi");

        System.out.println(); // Разделитель

        // ✅ С методом — повторное использование
        printWiFi();
        printWiFi();
        printWiFi();
    }

    /**
     * Метод printWiFi() — выводит строку "Wi-Fi" в консоль.
     * Вызывается несколько раз в main(), вместо копирования кода.
     */
    public static void printWiFi() {
        System.out.print("Wi-");
        System.out.println("Fi");
    }
}