package com.javarush.example;

/**
 * Слайд 05: Not a Number (NaN).
 * Демонстрация получения и работы со значением NaN для типов double/float.
 */
public class Slide05_NaN {

    public static void main(String[] args) {
        System.out.println("=== Слайд 05: Not a Number (NaN) ===\n");

        // 1. Получение NaN через деление 0.0 на 0.0
        System.out.println("1. Получение NaN через деление:");
        double nanFromZeroDivision = 0.0 / 0.0;
        System.out.printf("0.0 / 0.0 = %s\n", nanFromZeroDivision);
        System.out.println();

        // 2. Получение NaN через деление бесконечности на бесконечность
        System.out.println("2. Получение NaN через деление бесконечностей:");
        double infinity = 1d / 0d;
        double nanFromInfDivInf = infinity / infinity;
        System.out.printf("Infinity / Infinity = %s\n", nanFromInfDivInf);
        System.out.println();

        // 3. Операции с NaN (пример из слайда)
        System.out.println("3. Операции с NaN (пример из слайда):");
        double a = 0.0 / 0.0; // a == NaN
        double b = a * 10;    // b == NaN (любая операция с NaN дает NaN)
        double c = b - 100;   // c == NaN
        double d = a + infinity; // d == NaN

        System.out.printf("a = 0.0 / 0.0 = %s\n", a);
        System.out.printf("b = a * 10 = %s\n", b);
        System.out.printf("c = b - 100 = %s\n", c);
        System.out.printf("d = a + Infinity = %s\n", d);
        System.out.println();

        // 4. Уникальное свойство: NaN != NaN
        System.out.println("4. Уникальное свойство: NaN не равно самому себе!");
        System.out.printf("Is NaN == NaN? %b\n", Double.NaN == Double.NaN); // false!
        System.out.printf("Is nanFromZeroDivision == nanFromZeroDivision? %b\n",
                nanFromZeroDivision == nanFromZeroDivision); // false!
        System.out.println();

        // 5. Правильная проверка на NaN
        System.out.println("5. Правильная проверка на NaN:");
        System.out.printf("Is a NaN? %b\n", Double.isNaN(a));
        System.out.printf("Is b NaN? %b\n", Double.isNaN(b));
        System.out.printf("Is c NaN? %b\n", Double.isNaN(c));
        System.out.printf("Is d NaN? %b\n", Double.isNaN(d));
        System.out.printf("Is 5.0 NaN? %b\n", Double.isNaN(5.0)); // false
        System.out.println();

        // 6. Другие способы получения NaN
        System.out.println("6. Другие примеры получения NaN:");
        double sqrtOfNegative = Math.sqrt(-1.0); // Квадратный корень из отрицательного числа
        double logOfNegative = Math.log(-1.0);   // Логарифм отрицательного числа
        double nanFromMethod = Double.NaN;       // Явное присваивание

        System.out.printf("Math.sqrt(-1.0) = %s\n", sqrtOfNegative);
        System.out.printf("Math.log(-1.0) = %s\n", logOfNegative);
        System.out.printf("Double.NaN = %s\n", nanFromMethod);

        System.out.println("\n--- Запомни ---");
        System.out.println("NaN — это ВАЛИДНОЕ значение, а не ошибка.");
        System.out.println("Любая операция с NaN даёт NaN.");
        System.out.println("Никогда не используй == для проверки NaN! Всегда используй Double.isNaN() или Float.isNaN().");
    }
}