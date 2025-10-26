package com.javarush.example;

/**
 * Детальное рассмотрение класса Integer.
 * Показывает константы, методы преобразования, парсинг и кэширование.
 */
public class Slide05_IntegerDetailsExample {

    public static void main(String[] args) {
        demonstrateConstants();
        demonstrateConversionMethods();
        demonstrateParsing();
        demonstrateValueOfAndCaching();
        demonstrateErrorHandling();
    }

    private static void demonstrateConstants() {
        System.out.println("=== Константы класса Integer ===");
        System.out.println("Максимальное значение int: " + Integer.MAX_VALUE);
        System.out.println("Минимальное значение int: " + Integer.MIN_VALUE);
        System.out.println("Размер int в битах: " + Integer.SIZE);
        System.out.println("Размер int в байтах: " + Integer.BYTES);

        // Пример использования в проверке
        int value = 1_000_000_000;
        if (value > Integer.MAX_VALUE) {
            System.out.println("⚠️ Значение превышает максимально возможное!");
        } else {
            System.out.println("✅ Значение в допустимом диапазоне.");
        }
    }

    private static void demonstrateConversionMethods() {
        System.out.println("\n=== Методы преобразования в строки ===");

        int number = 255;

        System.out.println("Число: " + number);
        System.out.println("Шестнадцатеричное: " + Integer.toHexString(number));
        System.out.println("Двоичное: " + Integer.toBinaryString(number));
        System.out.println("Восьмеричное: " + Integer.toOctalString(number));

        // С заглавными буквами
        System.out.println("HEX (заглавные): " + Integer.toString(number, 16).toUpperCase());

        // Использование toString с основанием
        System.out.println("В 3-й системе: " + Integer.toString(number, 3));
    }

    private static void demonstrateParsing() {
        System.out.println("\n=== Парсинг строк в int ===");

        String validStr = "12345";
        String invalidStr = "abc";

        try {
            int parsed = Integer.parseInt(validStr);
            System.out.println("Успешно распарсено: " + parsed);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка парсинга: " + e.getMessage());
        }

        try {
            int parsedInvalid = Integer.parseInt(invalidStr);
            System.out.println("Это не выведется: " + parsedInvalid);
        } catch (NumberFormatException e) {
            System.out.println("❌ Невозможно распарсить '" + invalidStr + "'");
        }
    }

    private static void demonstrateValueOfAndCaching() {
        System.out.println("\n=== Метод valueOf и кэширование ===");

        // Создание через valueOf (рекомендуется)
        Integer a = Integer.valueOf(100);
        Integer b = Integer.valueOf(100);
        Integer c = Integer.valueOf(200);
        Integer d = Integer.valueOf(200);

        System.out.println("a == b (100): " + (a == b)); // true — кэш!
        System.out.println("c == d (200): " + (c == d)); // false — нет кэша!

        // Сравнение по значению — всегда безопасно
        System.out.println("a.equals(b): " + a.equals(b)); // true
        System.out.println("c.equals(d): " + c.equals(d)); // true

        // Создание через new — всегда новый объект
        Integer e = new Integer(100);
        Integer f = new Integer(100);
        System.out.println("e == f (new): " + (e == f)); // false — разные объекты!
    }

    private static void demonstrateErrorHandling() {
        System.out.println("\n=== Безопасный парсинг ===");

        String input = "  42  "; // с пробелами

        try {
            // trim() уберёт пробелы
            int safeParse = Integer.parseInt(input.trim());
            System.out.println("Безопасно распарсено: " + safeParse);
        } catch (NumberFormatException e) {
            System.out.println("Невозможно распарсить: '" + input + "'");
        }

        // Альтернатива — использовать try-catch или Optional
        int defaultValue = parseSafe("not_a_number", -1);
        System.out.println("Значение по умолчанию: " + defaultValue);
    }

    private static int parseSafe(String str, int defaultValue) {
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
