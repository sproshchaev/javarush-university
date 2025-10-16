package com.javarush.example;


/**
 * Слайд 08: Расширение типов.
 * Демонстрация неявного (безопасного) присваивания значений меньших типов переменным больших типов.
 */
public class Slide08_TypeWidening {

    public static void main(String[] args) {
        System.out.println("=== Слайд 08: Расширение типов ===\n");

        // 1. Базовый пример с присваиванием (как в таблице слайда)
        System.out.println("1. Пример из слайда:");
        byte a = 5;
        short b = a; // byte -> short (неявное расширение)
        int c = a + b; // byte и short расширяются до int перед сложением!
        long d = c * c; // int -> long (неявное расширение)

        System.out.printf("byte a = %d;\n", a);
        System.out.printf("short b = a; -> b = %d\n", b);
        System.out.printf("int c = a + b; -> c = %d\n", c); // Результат int
        System.out.printf("long d = c * c; -> d = %d\n", d);
        System.out.println();

        // 2. Демонстрация правил расширения
        System.out.println("2. Демонстрация правил расширения:");
        byte myByte = 10;
        short myShort = 100;
        int myInt = 1000;
        long myLong = 10000L;

        // Присваиваем меньший тип большему (безопасно!)
        short fromByte = myByte; // byte -> short
        int fromShort = myShort; // short -> int
        int fromByteToIn = myByte; // byte -> int (через short или напрямую)
        long fromInt = myInt;     // int -> long
        long fromShortToLong = myShort; // short -> long
        long fromByteToLong = myByte;   // byte -> long

        System.out.printf("byte myByte = %d;\n", myByte);
        System.out.printf("short fromByte = myByte; -> %d\n", fromByte);
        System.out.printf("int fromShort = myShort; -> %d\n", fromShort);
        System.out.printf("long fromInt = myInt; -> %d\n", fromInt);
        System.out.printf("long fromByteToLong = myByte; -> %d\n", fromByteToLong);
        System.out.println();

        // 3. Арифметика и автоматическое расширение до int
        System.out.println("3. Арифметика и автоматическое расширение:");
        byte x = 10;
        byte y = 20;
        // int result = x + y; // Ошибка компиляции! x+y - это int!
        int sum = x + y; // OK: результат выражения x+y - int
        // short sumShort = x + y; // ОШИБКА! Нужно явное приведение
        short sumShort = (short) (x + y); // Явное приведение к short

        System.out.printf("byte x = %d, byte y = %d;\n", x, y);
        System.out.printf("int sum = x + y; -> sum = %d\n", sum);
        System.out.printf("short sumShort = (short)(x + y); -> sumShort = %d\n", sumShort);
        System.out.println();

        // 4. Пример с char
        System.out.println("4. Пример с char:");
        char letter = 'A'; // Код 65
        int letterCode = letter; // char -> int (неявное расширение)
        long letterLong = letterCode; // int -> long (неявное расширение)

        System.out.printf("char letter = 'A'; (код: %d)\n", (int) letter);
        System.out.printf("int letterCode = letter; -> %d\n", letterCode);
        System.out.printf("long letterLong = letterCode; -> %d\n", letterLong);

        System.out.println("\n--- Запомни ---");
        System.out.println("Расширение типов — это безопасное автоматическое присваивание значения меньшего типа переменной большего типа.");
        System.out.println("Компилятор делает это без участия программиста.");
        System.out.println("В арифметических операциях над byte/short/char происходит автоматическое расширение до int!");
    }
}