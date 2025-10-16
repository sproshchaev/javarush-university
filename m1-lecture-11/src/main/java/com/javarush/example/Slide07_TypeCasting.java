package com.javarush.example;


/**
 * Слайд 07: Приведение типов.
 * Демонстрация неявного (расширяющего) и явного (сужающего) приведения типов.
 */
public class Slide07_TypeCasting {

    public static void main(String[] args) {
        System.out.println("=== Слайд 07: Приведение типов ===\n");

        // 1. Неявное (расширяющее) приведение (без потери)
        System.out.println("1. Неявное приведение (без потери информации):");
        byte b = 10;
        short s = b; // byte -> short (автоматически)
        int i = s;   // short -> int (автоматически)
        long l = i;  // int -> long (автоматически)
        float f = l; // long -> float (автоматически, но может быть потеря точности!)
        double d = f; // float -> double (автоматически)

        System.out.printf("byte b = %d;\n", b);
        System.out.printf("short s = b; -> s = %d\n", s);
        System.out.printf("int i = s; -> i = %d\n", i);
        System.out.printf("long l = i; -> l = %d\n", l);
        System.out.printf("float f = l; -> f = %.2f\n", f); // Обратите внимание на возможную потерю точности
        System.out.printf("double d = f; -> d = %.2f\n", d);
        System.out.println();

        // 2. Явное (сужающее) приведение (может быть потеря!)
        System.out.println("2. Явное приведение (может привести к потере информации):");
        int bigInt = 1000;
        byte smallByte = (byte) bigInt; // int -> byte (явное приведение)
        System.out.printf("int bigInt = %d;\n", bigInt);
        System.out.printf("byte smallByte = (byte) bigInt; -> smallByte = %d\n", smallByte);
        System.out.println("Внимание! Значение 1000 вышло за пределы byte (-128..127), произошло переполнение!");

        double pi = 3.14159;
        int intPi = (int) pi; // double -> int (явное приведение)
        System.out.printf("double pi = %.5f;\n", pi);
        System.out.printf("int intPi = (int) pi; -> intPi = %d\n", intPi);
        System.out.println("Внимание! Дробная часть отброшена (усечена)!");

        // 3. Пример с char (неявное приведение в int)
        System.out.println("3. Пример с char:");
        char letter = 'A'; // Код 65
        int letterCode = letter; // char -> int (неявно)
        System.out.printf("char letter = 'A'; (код: %d)\n", (int) letter);
        System.out.printf("int letterCode = letter; -> letterCode = %d\n", letterCode);
        System.out.println();

        // 4. Опасные операции (потеря точности)
        System.out.println("4. Опасные операции (потеря точности):");
        long veryBigLong = 10000000000L; // Очень большое число
        int truncatedInt = (int) veryBigLong; // long -> int (явно)
        System.out.printf("long veryBigLong = %d;\n", veryBigLong);
        System.out.printf("int truncatedInt = (int) veryBigLong; -> truncatedInt = %d\n", truncatedInt);
        System.out.println("Внимание! Число было обрезано до размера int, результат неверен!");

        System.out.println("\n--- Запомни ---");
        System.out.println("Неявное приведение (расширение) происходит автоматически и безопасно.");
        System.out.println("Явное приведение (сужение) требует указания программиста и может привести к потере данных!");
        System.out.println("Всегда будьте осторожны при сужающем приведении, особенно с дробными числами и большими целыми.");
    }
}