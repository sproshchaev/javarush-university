package com.javarush.example;

/**
 * Слайд 06: Тип char.
 * Демонстрация различных способов инициализации переменной типа char и работы с ней.
 */
public class Slide06_CharType {

    public static void main(String[] args) {
        System.out.println("=== Слайд 06: Тип char ===\n");

        // 1. Инициализация символом (литерал)
        System.out.println("1. Инициализация символом:");
        char a1 = 'A';
        System.out.printf("char a1 = 'A'; -> a1 = '%c' (код: %d)\n", a1, (int) a1);
        System.out.println();

        // 2. Инициализация целым числом (десятичным)
        System.out.println("2. Инициализация целым числом (десятичным):");
        char a2 = 65; // Код символа 'A' в ASCII/Unicode
        System.out.printf("char a2 = 65; -> a2 = '%c' (код: %d)\n", a2, (int) a2);
        System.out.println();

        // 3. Инициализация шестнадцатеричным числом
        System.out.println("3. Инициализация шестнадцатеричным числом:");
        char a3 = 0x41; // 0x41 в шестнадцатеричной системе = 65 в десятичной
        System.out.printf("char a3 = 0x41; -> a3 = '%c' (код: %d)\n", a3, (int) a3);
        System.out.println();

        // 4. Инициализация Юникод-литералом
        System.out.println("4. Инициализация Юникод-литералом:");
        char a4 = '\u0041'; // Явное указание кода Unicode
        System.out.printf("char a4 = '\\u0041'; -> a4 = '%c' (код: %d)\n", a4, (int) a4);
        System.out.println();

        // 5. Проверка: все переменные содержат одно и то же значение!
        System.out.println("5. Проверка равенства:");
        System.out.printf("a1 == a2? %b\n", a1 == a2); // true
        System.out.printf("a1 == a3? %b\n", a1 == a3); // true
        System.out.printf("a1 == a4? %b\n", a1 == a4); // true
        System.out.printf("a2 == a3? %b\n", a2 == a3); // true
        System.out.printf("a2 == a4? %b\n", a2 == a4); // true
        System.out.printf("a3 == a4? %b\n", a3 == a4); // true
        System.out.println();

        // 6. Арифметика с char (результат - int!)
        System.out.println("6. Арифметика с char:");
        char b = 'B'; // Код 66
        int result = b + 1; // 'B' + 1 = 66 + 1 = 67 -> 'C'
        char c = (char) result; // Явное приведение обратно к char

        System.out.printf("char b = 'B'; (код: %d)\n", (int) b);
        System.out.printf("int result = b + 1; -> result = %d\n", result);
        System.out.printf("char c = (char) result; -> c = '%c' (код: %d)\n", c, (int) c);
        System.out.println();

        // 7. Пример с другими символами (не латиница)
        System.out.println("7. Пример с другими символами (Юникод):");
        char cyrillicA = 'А'; // Кириллическая буква "А"
        // char smiley = '\uD83D\uDE00'; // ОШИБКА: Too many characters in character literal
        String smiley = "\uD83D\uDE00"; // Правильно: используем String

        System.out.printf("char cyrillicA = 'А'; -> '%c' (код: %d)\n", cyrillicA, (int) cyrillicA);
        // Выводим emoji как строку
        System.out.printf("String smiley = \"\\uD83D\\uDE00\"; -> '%s'\n", smiley);

        System.out.println("\n--- Запомни ---");
        System.out.println("Тип char хранит число (от 0 до 65535), которое интерпретируется как код символа Unicode.");
        System.out.println("Инициализировать его можно разными способами: символом, числом, шестнадцатеричным или Юникод-литералом.");
        System.out.println("Некоторые символы, например, emoji, требуют более 16 бит и НЕ могут быть сохранены в одном char.");
        System.out.println("Для них нужно использовать String.");
    }
}