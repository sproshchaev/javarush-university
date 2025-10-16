package com.javarush.example;

public class Slide04_Infinity {

    public static void main(String[] args) {
        System.out.println("=== Слайд 04: Бесконечность ===\n");

        // 1. Получение Infinity через деление на ноль
        System.out.println("1. Получение Infinity через деление:");
        double positiveInfinity = 100.0 / 0.0;
        double negativeInfinity = -100.0 / 0.0;

        System.out.printf("100.0 / 0.0 = %s\n", positiveInfinity);
        System.out.printf("-100.0 / 0.0 = %s\n", negativeInfinity);
        System.out.println();

        // 2. Проверка значений (можно использовать методы класса Double)
        System.out.println("2. Проверка значений:");
        System.out.printf("Is positiveInfinity infinite? %b\n", Double.isInfinite(positiveInfinity));
        System.out.printf("Is negativeInfinity infinite? %b\n", Double.isInfinite(negativeInfinity));
        System.out.printf("Is positiveInfinity positive? %b\n", positiveInfinity > 0);
        System.out.printf("Is negativeInfinity negative? %b\n", negativeInfinity < 0);
        System.out.println();

        // 3. Операции с Infinity (как показано в слайде)
        System.out.println("3. Операции с Infinity (пример из слайда):");
        double a = 1d / 0d; // a == Infinity
        double b = a * 10;  // b == Infinity (Infinity * любое положительное число = Infinity)
        double c = b - 100; // c == Infinity (Infinity - любое конечное число = Infinity)

        System.out.printf("a = 1d / 0d = %s\n", a);
        System.out.printf("b = a * 10 = %s\n", b);
        System.out.printf("c = b - 100 = %s\n", c);
        System.out.println();

        // 4. Сравнение Infinity
        System.out.println("4. Сравнение Infinity:");
        System.out.printf("Infinity == Infinity? %b\n", Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY); // true
        System.out.printf("Infinity > 1000000000.0? %b\n", Double.POSITIVE_INFINITY > 1_000_000_000.0); // true
        System.out.printf("-Infinity < -1000000000.0? %b\n", Double.NEGATIVE_INFINITY < -1_000_000_000.0); // true
        System.out.println();

        // 5. Важное замечание: НЕ путать с целочисленным делением на ноль!
        System.out.println("5. Важно! Целочисленное деление на ноль:");
        try {
            int intResult = 100 / 0; // Это вызовет ArithmeticException!
            System.out.println("Этот код не выполнится.");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.println("Целочисленное деление на ноль НЕ допускается и приводит к исключению!");
        }

        System.out.println("\n--- Запомни ---");
        System.out.println("Infinity и -Infinity — это ВАЛИДНЫЕ значения для double/float, а не ошибки.");
        System.out.println("Их можно использовать в вычислениях и сравнениях.");
    }
}
