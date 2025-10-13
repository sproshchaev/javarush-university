package com.javarush.example;

/**
 * Пример использования основных методов класса String
 */
public class Slide08_StringMethods {

    public static void main(String[] args) {
        System.out.println("=== Методы класса String ===\n");

        // Пример строки
        String text = "  Привет, мир!  ";
        String empty = "";
        String blank = "   \t\n  ";
        String numbers = "123,456,789";
        String[] parts = {"Java", "Rush", "Webinar"};

        // 1. length() — количество символов
        System.out.println("Длина строки \"" + text + "\": " + text.length());

        // 2. isEmpty() — проверка на пустую строку
        System.out.println("Строка \"" + empty + "\" пустая? " + empty.isEmpty());
        System.out.println("Строка \"" + text + "\" пустая? " + text.isEmpty());

        // 3. isBlank() — проверка на "пустую" строку (только пробелы, табы, переносы)
        System.out.println("Строка \"" + blank + "\" blank? " + blank.isBlank());
        System.out.println("Строка \"" + text + "\" blank? " + text.isBlank());

        // 4. charAt(int index) — символ по индексу
        System.out.println("Первый символ строки \"" + text + "\": '" + text.charAt(0) + "'");
        System.out.println("Последний символ: '" + text.charAt(text.length() - 1) + "'");

        // 5. toCharArray() — преобразование в массив символов
        char[] chars = text.toCharArray();
        System.out.print("Массив символов: ");
        for (char c : chars) {
            System.out.print("'" + c + "' ");
        }
        System.out.println();

        // 6. getBytes() — преобразование в массив байт
        byte[] bytes = text.getBytes();
        System.out.print("Массив байт: ");
        for (byte b : bytes) {
            System.out.print(b + " ");
        }
        System.out.println("\n");

        // 7. split(String regex) — разбиение строки
        String[] splitResult = numbers.split(",");
        System.out.println("Разделённая строка \"" + numbers + "\":");
        for (String part : splitResult) {
            System.out.println("  -> " + part);
        }

        // 8. join(CharSequence delimiter, elements) — склейка строк
        String joined = String.join(" | ", parts);
        System.out.println("\nСклеенные строки: " + joined);

        // 9. intern() — помещение строки в String Pool
        String interned = text.intern();
        System.out.println("Интернированная строка: " + interned);
        System.out.println("text == interned? " + (text == interned)); // ❌ false — text имеет пробелы, interned — тоже, но объекты разные
        // Но если бы мы взяли литерал — было бы true

        // Показываем, что строки неизменяемы
        System.out.println("\n--- Строки неизменяемы ---");
        String original = "Hello";
        String modified = original.toUpperCase(); // возвращает НОВУЮ строку
        System.out.println("Оригинал: " + original);     // Hello
        System.out.println("Изменённая: " + modified);   // HELLO
    }
}