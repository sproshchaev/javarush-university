package com.javarush;

/**
 * JavaRush-University
 */
public class Main {

    static byte aByte;
    static short aShort;
    static int anInt;
    static long aLong;
    static float aFloat;
    static double aDouble;
    static boolean aBoolean;
    static char aChar;

    public static void main(String[] args) {

        // 1. Целочисленные типы и диапазоны
        byte b = 100;    // -128 до 127
        short s = 30000; // -32 768 до 32 767
        int i = 1000000; // -2 млрд до 2 млрд
        int i2 = 1_000_000;
        System.out.println("i = i2 " + (i == i2));
        long l = 10000000000L; // Обычно исп в id сущностей экземпляров классов

        // 2. Вещественные типы (плавающая точка)
        float f = 3.14f;       // всегда с "f"
        double d = 3.14159265; // без суффикса

        // 3. Логический тип
        boolean flag = true;
        boolean isActive = false;

        // 4. Символьный тип
        char letter = 'A';
        char uniChar = '\u0041'; // Юникод для 'A'

        // 5. Значения по умолчанию (для полей класса)
        System.out.println("5. Значения по умолчанию (поля класса):");
        System.out.printf("byte: %d\n", aByte);
        System.out.printf("short: %d\n", aShort);
        System.out.printf("int: %d\n", anInt); // 0
        System.out.printf("long: %d\n", aLong);
        System.out.printf("float: %.2f\n", aFloat);
        System.out.printf("double: %.2f\n", aDouble);
        System.out.printf("boolean: %b\n", aBoolean);
        System.out.printf("char: '%c'", aChar);

        // 6. Локальные переменные
        int count;
        // System.out.println("count=" + count); // java: variable count might not have been initialized

        int count2 = 100;
        System.out.println("count=" + count2);
    }


}
