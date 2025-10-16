package com.javarush.example;

public class Slide03_PrimitiveTypes {

    // Поля класса - получат значения по умолчанию
    static byte defaultByte;
    static short defaultShort;
    static int defaultInt;
    static long defaultLong;
    static float defaultFloat;
    static double defaultDouble;
    static boolean defaultBoolean;
    static char defaultChar;

    public static void main(String[] args) {
        System.out.println("=== Слайд 03: Примитивные типы данных ===\n");

        // 1. Целочисленные типы
        System.out.println("1. Целочисленные типы:");
        byte b = 100; // Диапазон: -128 до 127
        short s = 30000; // Диапазон: -32,768 до 32,767
        int i = 1_000_000; // Диапазон: ~ -2 млрд до +2 млрд (по умолчанию)
        long l = 10000000000L; // Диапазон: очень большой, суффикс 'L'

        System.out.printf("byte: %d (размер: %d байта)\n", b, Byte.BYTES);
        System.out.printf("short: %d (размер: %d байта)\n", s, Short.BYTES);
        System.out.printf("int: %d (размер: %d байта)\n", i, Integer.BYTES);
        System.out.printf("long: %d (размер: %d байта)\n", l, Long.BYTES);
        System.out.println();

        // 2. Вещественные типы (плавающая точка)
        System.out.println("2. Вещественные типы:");
        float f = 3.14f; // Суффикс 'f' для float
        double d = 3.141592653589793; // Без суффикса - double по умолчанию

        System.out.printf("float: %.6f (размер: %d байта)\n", f, Float.BYTES);
        System.out.printf("double: %.15f (размер: %d байта)\n", d, Double.BYTES);
        System.out.println();

        // 3. Логический тип
        System.out.println("3. Логический тип:");
        boolean flag = true;
        System.out.printf("boolean: %b\n", flag);
        System.out.println();

        // 4. Символьный тип
        System.out.println("4. Символьный тип:");
        char letter = 'A';
        char unicodeChar = '\u0041'; // Юникод для 'A'
        System.out.printf("char: '%c' (Unicode: %s)\n", letter, Integer.toHexString(letter));
        System.out.printf("char (Unicode): '%c'\n", unicodeChar);
        System.out.println();

        // 5. Значения по умолчанию (для полей класса)
        System.out.println("5. Значения по умолчанию (поля класса):");
        System.out.printf("byte: %d\n", defaultByte);
        System.out.printf("short: %d\n", defaultShort);
        System.out.printf("int: %d\n", defaultInt);
        System.out.printf("long: %d\n", defaultLong);
        System.out.printf("float: %.2f\n", defaultFloat);
        System.out.printf("double: %.2f\n", defaultDouble);
        System.out.printf("boolean: %b\n", defaultBoolean);
        System.out.printf("char: '%c' (код: \\u%04X)\n", defaultChar, (int) defaultChar);

        System.out.println("\n--- Примечание ---");
        System.out.println("Примитивы хранят значение НЕПОСРЕДСТВЕННО в переменной.");
        System.out.println("Они НЕ являются объектами и не имеют методов (кроме оберток).");
    }
}