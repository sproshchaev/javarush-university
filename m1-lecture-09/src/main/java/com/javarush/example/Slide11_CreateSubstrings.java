package com.javarush.example;

/**
 * Примеры создания подстрок и модификации строк
 */
public class Slide11_CreateSubstrings {

    public static void main(String[] args) {
        System.out.println("=== Создание подстрок ===\n");

        String original = "  Привет, мир!  ";
        String text = "JavaRush - лучший курс по Java!";
        String email = "USER@EXAMPLE.COM";

        // 1. substring(int beginIndex, int endIndex)
        System.out.println("Оригинал: \"" + original + "\"");
        String trimmed = original.trim(); // Убираем пробелы
        System.out.println("После trim(): \"" + trimmed + "\"");

        String part = trimmed.substring(0, 7); // "Привет,"
        System.out.println("substring(0, 7): \"" + part + "\"");

        String rest = trimmed.substring(8); // "мир!"
        System.out.println("substring(8): \"" + rest + "\"");

        // 2. repeat(int count)
        String line = "-".repeat(30);
        System.out.println("\nЛиния из 30 тире: " + line);

        String greeting = "Привет! ".repeat(3);
        System.out.println("Повторение: \"" + greeting + "\"");

        // 3. replace(char oldChar, char newChar)
        String replaced = text.replace('a', 'A');
        System.out.println("\nreplace('a', 'A'): \"" + replaced + "\"");

        // 4. replaceAll(String regex, String replacement)
        String cleaned = text.replaceAll("[^a-zA-Z\\s]", ""); // оставляем только буквы и пробелы
        System.out.println("replaceAll(не-буквы): \"" + cleaned + "\"");

        // 5. replaceFirst(String regex, String replacement)
        String firstReplaced = text.replaceFirst("Java", "Python");
        System.out.println("replaceFirst(\"Java\", \"Python\"): \"" + firstReplaced + "\"");

        // 6. toLowerCase() / toUpperCase()
        System.out.println("\ntoLowerCase(): \"" + email.toLowerCase() + "\"");
        System.out.println("toUpperCase(): \"" + email.toUpperCase() + "\"");

        // 7. trim() — уже использовали выше
        System.out.println("Исходная строка с пробелами: \"" + original + "\"");
        System.out.println("После trim(): \"" + original.trim() + "\"");

        // 8. Комбинируем методы — реальный пример
        System.out.println("\n--- Реальный пример: обработка email ---");
        String rawEmail = "   USER@EXAMPLE.COM   ";
        String normalizedEmail = rawEmail.trim().toLowerCase();
        System.out.println("Исходный email: \"" + rawEmail + "\"");
        System.out.println("Нормализованный: \"" + normalizedEmail + "\"");

        // 9. Важный вывод: строки неизменяемы!
        System.out.println("\n--- Строки неизменяемы ---");
        String s = "hello";
        String upper = s.toUpperCase(); // Новая строка
        System.out.println("Оригинал: " + s);     // hello
        System.out.println("Верхний регистр: " + upper); // HELLO
        System.out.println("s == upper? " + (s == upper)); // false — разные объекты
    }
}