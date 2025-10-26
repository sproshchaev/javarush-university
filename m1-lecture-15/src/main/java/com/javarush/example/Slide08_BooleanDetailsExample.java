package com.javarush.example;

/**
 * Детальное рассмотрение класса Boolean.
 * Показывает константы, методы создания и парсинга, а также особенности сравнения.
 */
public class Slide08_BooleanDetailsExample {

    public static void main(String[] args) {
        demonstrateConstants();
        demonstrateValueOfAndCaching();
        demonstrateParsingMethods();
        demonstrateComparisonPitfalls();
        demonstratePracticalUseCases();
    }

    private static void demonstrateConstants() {
        System.out.println("=== Константы класса Boolean ===");
        System.out.println("Boolean.TRUE: " + Boolean.TRUE);
        System.out.println("Boolean.FALSE: " + Boolean.FALSE);

        // Это те же самые объекты, что и при создании через valueOf
        Boolean a = Boolean.TRUE;
        Boolean b = Boolean.FALSE;
        System.out.println("a == Boolean.TRUE: " + (a == Boolean.TRUE)); // true
        System.out.println("b == Boolean.FALSE: " + (b == Boolean.FALSE)); // true

        // Примитивы
        boolean primitiveTrue = true;
        boolean primitiveFalse = false;
        System.out.println("primitiveTrue: " + primitiveTrue);
        System.out.println("primitiveFalse: " + primitiveFalse);
    }

    private static void demonstrateValueOfAndCaching() {
        System.out.println("\n=== Метод valueOf и кэширование ===");

        // Создание через valueOf — возвращает кэшированные объекты
        Boolean a = Boolean.valueOf(true);
        Boolean b = Boolean.valueOf(true);
        Boolean c = Boolean.valueOf(false);
        Boolean d = Boolean.valueOf(false);

        System.out.println("a == b (true): " + (a == b)); // true — кэш!
        System.out.println("c == d (false): " + (c == d)); // true — кэш!

        // Создание через new — всегда новый объект
        Boolean e = new Boolean(true);
        Boolean f = new Boolean(true);
        System.out.println("e == f (new): " + (e == f)); // false — разные объекты!

        // Сравнение по значению — безопасно
        System.out.println("a.equals(e): " + a.equals(e)); // true
        System.out.println("a == e: " + (a == e)); // false — разные объекты!
    }

    private static void demonstrateParsingMethods() {
        System.out.println("\n=== Парсинг строк в boolean ===");

        // parseBoolean возвращает примитив
        String validTrue1 = "true";
        String validTrue2 = "True";
        String validTrue3 = "TRUE";
        String validFalse = "false";
        String invalid = "maybe";

        System.out.println("parseBoolean('" + validTrue1 + "') = " + Boolean.parseBoolean(validTrue1));
        System.out.println("parseBoolean('" + validTrue2 + "') = " + Boolean.parseBoolean(validTrue2));
        System.out.println("parseBoolean('" + validTrue3 + "') = " + Boolean.parseBoolean(validTrue3));
        System.out.println("parseBoolean('" + validFalse + "') = " + Boolean.parseBoolean(validFalse));
        System.out.println("parseBoolean('" + invalid + "') = " + Boolean.parseBoolean(invalid)); // false!

        // valueOf возвращает объект Boolean
        Boolean objTrue = Boolean.valueOf(validTrue1);
        Boolean objFalse = Boolean.valueOf(validFalse);
        Boolean objInvalid = Boolean.valueOf(invalid);

        System.out.println("valueOf('" + validTrue1 + "') = " + objTrue);
        System.out.println("valueOf('" + invalid + "') = " + objInvalid); // false

        // Регистронезависимость
        System.out.println("valueOf('TrUe') = " + Boolean.valueOf("TrUe")); // true
        System.out.println("valueOf('fAlSe') = " + Boolean.valueOf("fAlSe")); // false
    }

    private static void demonstrateComparisonPitfalls() {
        System.out.println("\n=== Ловушки сравнения ===");

        // Безопасное сравнение через equals
        Boolean a = Boolean.valueOf(true);
        Boolean b = Boolean.valueOf(true);
        Boolean c = new Boolean(true);

        System.out.println("a == b: " + (a == b)); // true — кэш
        System.out.println("a == c: " + (a == c)); // false — разные объекты
        System.out.println("a.equals(c): " + a.equals(c)); // true — значение одинаковое

        // Сравнение с null
        Boolean nullable = null;
        try {
            boolean dangerous = nullable; // unboxing null → NPE!
            System.out.println(dangerous);
        } catch (NullPointerException e) {
            System.out.println("⚠️ Попытка распаковать null вызвала NullPointerException!");
        }

        // Безопасное сравнение с null
        if (nullable != null && nullable) {
            System.out.println("Значение true");
        } else if (nullable != null) {
            System.out.println("Значение false");
        } else {
            System.out.println("Значение null");
        }
    }

    private static void demonstratePracticalUseCases() {
        System.out.println("\n=== Практические примеры ===");

        // Чтение настроек из строки
        String configValue = "TRUE";
        boolean isEnabled = Boolean.parseBoolean(configValue);
        System.out.println("Настройка включена? " + isEnabled);

        // Валидация пользовательского ввода
        String userInput = "Yes"; // или "no", "true", "false"
        boolean userAgrees = Boolean.parseBoolean(userInput.toLowerCase());
        System.out.println("Пользователь согласен? " + userAgrees); // false — только "true" даст true!

        // Альтернатива — более гибкий парсер
        boolean flexibleParse = parseFlexible(userInput);
        System.out.println("Гибкий парсинг: " + flexibleParse);

        // Использование в коллекциях
        java.util.List<Boolean> flags = new java.util.ArrayList<>();
        flags.add(Boolean.TRUE);
        flags.add(Boolean.FALSE);
        flags.add(Boolean.valueOf("true"));
        flags.add(Boolean.valueOf("false"));

        System.out.println("Флаги: " + flags);
    }

    private static boolean parseFlexible(String input) {
        if (input == null) return false;
        String lower = input.trim().toLowerCase();
        return "true".equals(lower) || "yes".equals(lower) || "1".equals(lower);
    }
}
