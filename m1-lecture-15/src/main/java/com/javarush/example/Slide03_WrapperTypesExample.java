package com.javarush.example;

/**
 * Пример использования типов-оберток в Java.
 * Демонстрирует создание, преобразование, методы и особенности.
 */
public class Slide03_WrapperTypesExample {

    public static void main(String[] args) {
        demonstrateCreation();
        demonstrateMethods();
        demonstrateAutoboxingUnboxing();
        demonstrateNullHandling();
        demonstrateComparisonPitfall();
    }

    private static void demonstrateCreation() {
        System.out.println("=== Создание оберток ===");
        Integer i1 = new Integer(10);      // Устаревший способ (Java < 9)
        Integer i2 = Integer.valueOf(10);  // Рекомендуемый способ
        Integer i3 = 10;                   // Автоупаковка

        System.out.println("i1: " + i1);
        System.out.println("i2: " + i2);
        System.out.println("i3: " + i3);
    }

    private static void demonstrateMethods() {
        System.out.println("\n=== Методы оберток ===");
        String numberStr = "123";
        int primitiveInt = Integer.parseInt(numberStr);
        Integer wrapperInt = Integer.valueOf(numberStr);

        System.out.println("Из строки в int: " + primitiveInt);
        System.out.println("Из строки в Integer: " + wrapperInt);

        System.out.println("Максимальное значение int: " + Integer.MAX_VALUE);
        System.out.println("Минимальное значение int: " + Integer.MIN_VALUE);
    }

    private static void demonstrateAutoboxingUnboxing() {
        System.out.println("\n=== Автоупаковка и автораспаковка ===");
        int primitive = 5;
        Integer wrapper = primitive; // автоупаковка

        int backToPrimitive = wrapper; // автораспаковка

        System.out.println("Примитив -> Обертка -> Примитив: " + backToPrimitive);
    }

    private static void demonstrateNullHandling() {
        System.out.println("\n=== Работа с null ===");
        Integer nullable = null;

        // Безопасное использование
        if (nullable != null) {
            System.out.println("Значение: " + nullable);
        } else {
            System.out.println("Значение null — можно обработать!");
        }

        // Опасно! Вызовет NullPointerException
        try {
            int dangerous = nullable; // автораспаковка null → NPE
        } catch (NullPointerException e) {
            System.out.println("⚠️ Попытка распаковать null вызвала исключение!");
        }
    }

    private static void demonstrateComparisonPitfall() {
        System.out.println("\n=== Опасность сравнения через == ===");
        Integer a = 100;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;

        System.out.println("a == b: " + (a == b)); // true (кэширование от -128 до 127)
        System.out.println("c == d: " + (c == d)); // false (вне кэша — разные объекты!)

        System.out.println("a.equals(b): " + a.equals(b)); // true — правильно!
        System.out.println("c.equals(d): " + c.equals(d)); // true — правильно!
    }
}
