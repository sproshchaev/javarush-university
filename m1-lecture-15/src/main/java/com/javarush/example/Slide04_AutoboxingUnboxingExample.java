package com.javarush.example;

/**
 * Примеры autoboxing и unboxing в Java.
 * Демонстрирует механизмы, особенности и ловушки.
 */
public class Slide04_AutoboxingUnboxingExample {

    public static void main(String[] args) {
        demonstrateBasicOperations();
        demonstrateInExpressions();
        demonstrateNullSafety();
        demonstratePerformancePitfall();
        demonstrateCompilerTranslation();
    }

    private static void demonstrateBasicOperations() {
        System.out.println("=== Базовые операции ===");

        // Autoboxing
        Integer boxedInt = 42; // Integer.valueOf(42)
        Boolean boxedBool = true; // Boolean.valueOf(true)
        Double boxedDouble = 3.14; // Double.valueOf(3.14)

        System.out.println("Boxed int: " + boxedInt);
        System.out.println("Boxed bool: " + boxedBool);
        System.out.println("Boxed double: " + boxedDouble);

        // Unboxing
        int unboxedInt = boxedInt; // boxedInt.intValue()
        boolean unboxedBool = boxedBool; // boxedBool.booleanValue()
        double unboxedDouble = boxedDouble; // boxedDouble.doubleValue()

        System.out.println("Unboxed int: " + unboxedInt);
        System.out.println("Unboxed bool: " + unboxedBool);
        System.out.println("Unboxed double: " + unboxedDouble);
    }

    private static void demonstrateInExpressions() {
        System.out.println("\n=== В выражениях ===");

        Integer x = 10;
        int y = 5;

        // Unboxing x → int, затем сложение → результат int
        int sum = x + y;
        System.out.println("x + y = " + sum); // 15

        // Autoboxing результата (если нужно)
        Integer result = sum; // autoboxing
        System.out.println("Результат как Integer: " + result);

        // Комплексное выражение
        Integer z = 3;
        Integer complex = x + y * z; // unbox x,y,z → вычисление → autobox результата
        System.out.println("x + y * z = " + complex);
    }

    private static void demonstrateNullSafety() {
        System.out.println("\n=== Опасность null ===");

        Integer nullable = null;

        try {
            int dangerous = nullable; // unboxing null → NPE!
            System.out.println(dangerous);
        } catch (NullPointerException e) {
            System.out.println("⚠️ Попытка распаковать null вызвала NullPointerException!");
        }

        // Безопасный способ
        int safeValue = (nullable != null) ? nullable : 0;
        System.out.println("Безопасное значение: " + safeValue);
    }

    private static void demonstratePerformancePitfall() {
        System.out.println("\n=== Производительность ===");

        long start = System.currentTimeMillis();

        // Autoboxing в цикле — создает множество объектов!
        for (int i = 0; i < 100_000; i++) {
            Integer obj = i; // autoboxing
        }

        long end = System.currentTimeMillis();
        System.out.println("Цикл с autoboxing занял: " + (end - start) + " мс");

        // Лучше использовать примитивы, если не нужны объекты
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            int primitive = i; // нет объектов
        }
        end = System.currentTimeMillis();
        System.out.println("Цикл с примитивами занял: " + (end - start) + " мс");
    }

    private static void demonstrateCompilerTranslation() {
        System.out.println("\n=== Как видит компилятор ===");

        // Ваш код:
        Integer a = 10;
        int b = a;
        Integer c = a + b;

        // Что делает компилятор:
        Integer aCompiled = Integer.valueOf(10);
        int bCompiled = aCompiled.intValue();
        Integer cCompiled = Integer.valueOf(aCompiled.intValue() + bCompiled);

        System.out.println("Компилятор заменяет:");
        System.out.println("  Integer a = 10; → Integer.valueOf(10)");
        System.out.println("  int b = a; → a.intValue()");
        System.out.println("  Integer c = a + b; → Integer.valueOf(a.intValue() + b)");

        System.out.println("Результат: " + cCompiled);
    }
}
