package com.javarush.example;

/**
 * Слайд 09: Сужение типов.
 * Демонстрация явного приведения (сужения) значений больших типов в переменные меньших типов.
 */
public class Slide09_TypeNarrowing {

    public static void main(String[] args) {
        System.out.println("=== Слайд 09: Сужение типов ===\n");

        // 1. Базовый пример с приведением (как в таблице слайда)
        System.out.println("1. Пример из слайда:");
        long a = 1L;
        int b = (int) a; // long -> int (явное сужение)
        short c = (short) b; // int -> short (явное сужение)
        byte d = (byte) c; // short -> byte (явное сужение)

        System.out.printf("long a = %d;\n", a);
        System.out.printf("int b = (int) a; -> b = %d\n", b);
        System.out.printf("short c = (short) b; -> c = %d\n", c);
        System.out.printf("byte d = (byte) c; -> d = %d\n", d);
        System.out.println();

        // 2. Пример с переполнением (как в методе main слайда)
        System.out.println("2. Пример с переполнением (как в методе main):");
        int bigNumber = 10_000_000;
        short littleNumber = (short) bigNumber; // Явное сужение int -> short

        System.out.printf("int bigNumber = %d;\n", bigNumber);
        System.out.printf("short littleNumber = (short) bigNumber; -> littleNumber = %d\n", littleNumber);
        System.out.println("Внимание! Произошло переполнение! Максимальное значение short: 32767.");

        // 3. Пример с дробными числами (усечение)
        System.out.println("3. Пример с дробными числами (усечение):");
        double pi = 3.14159;
        int intPi = (int) pi; // double -> int (явное сужение)
        float f = 5.99f;
        int intF = (int) f; // float -> int (явное сужение)

        System.out.printf("double pi = %.5f;\n", pi);
        System.out.printf("int intPi = (int) pi; -> intPi = %d\n", intPi);
        System.out.printf("float f = %.2f;\n", f);
        System.out.printf("int intF = (int) f; -> intF = %d\n", intF);
        System.out.println("Внимание! Дробная часть отброшена!");

        // 4. Опасные операции: потеря точности при приведении double/float к целым
        System.out.println("4. Опасные операции (потеря точности):");
        double veryBigDouble = 10000000000.123;
        long longFromDouble = (long) veryBigDouble; // double -> long (явное сужение)
        int intFromDouble = (int) veryBigDouble;     // double -> int (явное сужение)

        System.out.printf("double veryBigDouble = %.3f;\n", veryBigDouble);
        System.out.printf("long longFromDouble = (long) veryBigDouble; -> %d\n", longFromDouble);
        System.out.printf("int intFromDouble = (int) veryBigDouble; -> %d\n", intFromDouble);
        System.out.println("Внимание! При приведении к int произошла потеря данных (число слишком большое)!");

        // 5. Как проверить, не выйдет ли значение за пределы?
        System.out.println("5. Как проверить безопасность сужения:");
        int valueToCheck = 300;
        if (valueToCheck >= Byte.MIN_VALUE && valueToCheck <= Byte.MAX_VALUE) {
            byte safeByte = (byte) valueToCheck;
            System.out.printf("Значение %d входит в диапазон byte (%d..%d). Безопасно!\n",
                    valueToCheck, Byte.MIN_VALUE, Byte.MAX_VALUE);
            System.out.printf("safeByte = %d\n", safeByte);
        } else {
            System.out.printf("Значение %d ВЫХОДИТ за диапазон byte (%d..%d). Опасно!\n",
                    valueToCheck, Byte.MIN_VALUE, Byte.MAX_VALUE);
        }

        System.out.println("\n--- Запомни ---");
        System.out.println("Сужение типов — это опасная операция, требующая явного указания (оператор cast).");
        System.out.println("Всегда осознавайте риск потери информации (переполнение, усечение).");
        System.out.println("Если возможно, перед сужением проверяйте, входит ли значение в диапазон целевого типа.");
    }
}