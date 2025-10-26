package com.javarush.example;

/**
 * Детальное рассмотрение класса Double.
 * Показывает константы, методы проверки, парсинг и особенности чисел с плавающей точкой.
 */
public class Slide06_DoubleDetailsExample {

    public static void main(String[] args) {
        demonstrateConstants();
        demonstrateSpecialValues();
        demonstrateParsing();
        demonstrateValueOfAndCaching();
        demonstrateComparisonPitfalls();
        demonstrateEdgeCases();
    }

    private static void demonstrateConstants() {
        System.out.println("=== Константы класса Double ===");
        System.out.println("Плюс бесконечность: " + Double.POSITIVE_INFINITY);
        System.out.println("Минус бесконечность: " + Double.NEGATIVE_INFINITY);
        System.out.println("NaN: " + Double.NaN);
        System.out.println("Максимальное значение: " + Double.MAX_VALUE);
        System.out.println("Минимальное положительное значение: " + Double.MIN_VALUE);
        System.out.println("Максимальный экспонент: " + Double.MAX_EXPONENT);
        System.out.println("Минимальный экспонент: " + Double.MIN_EXPONENT);
    }

    private static void demonstrateSpecialValues() {
        System.out.println("\n=== Работа с бесконечностью и NaN ===");

        // Получаем бесконечность
        double inf = 1.0 / 0.0;
        double negInf = -1.0 / 0.0;
        double nan = 0.0 / 0.0;

        System.out.println("inf: " + inf);
        System.out.println("negInf: " + negInf);
        System.out.println("nan: " + nan);

        // Проверяем
        System.out.println("isInfinite(inf): " + Double.isInfinite(inf));
        System.out.println("isInfinite(negInf): " + Double.isInfinite(negInf));
        System.out.println("isNaN(nan): " + Double.isNaN(nan));

        // Операции с бесконечностью
        System.out.println("inf + 100: " + (inf + 100)); // INF
        System.out.println("inf - inf: " + (inf - inf)); // NaN
        System.out.println("inf * 0: " + (inf * 0));     // NaN
    }

    private static void demonstrateParsing() {
        System.out.println("\n=== Парсинг строк в double ===");

        String validStr = "3.14159";
        String invalidStr = "abc";

        try {
            double parsed = Double.parseDouble(validStr);
            System.out.println("Успешно распарсено: " + parsed);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка парсинга: " + e.getMessage());
        }

        try {
            double parsedInvalid = Double.parseDouble(invalidStr);
            System.out.println("Это не выведется: " + parsedInvalid);
        } catch (NumberFormatException e) {
            System.out.println("❌ Невозможно распарсить '" + invalidStr + "'");
        }
    }

    private static void demonstrateValueOfAndCaching() {
        System.out.println("\n=== Метод valueOf и кэширование ===");

        // Double.valueOf() не кэширует значения, как Integer!
        Double a = Double.valueOf(1.0);
        Double b = Double.valueOf(1.0);
        Double c = Double.valueOf(2.0);
        Double d = Double.valueOf(2.0);

        System.out.println("a == b (1.0): " + (a == b)); // false — нет кэша!
        System.out.println("c == d (2.0): " + (c == d)); // false — нет кэша!

        // Сравнение по значению — безопасно
        System.out.println("a.equals(b): " + a.equals(b)); // true
        System.out.println("c.equals(d): " + c.equals(d)); // true

        // Создание через new — всегда новый объект
        Double e = new Double(1.0);
        Double f = new Double(1.0);
        System.out.println("e == f (new): " + (e == f)); // false
    }

    private static void demonstrateComparisonPitfalls() {
        // Ловушки сравнения
        System.out.println("\n=== Ловушки сравнения ===");

        double nan1 = Double.NaN;
        double nan2 = Double.NaN;

        System.out.println("nan1 == nan2: " + (nan1 == nan2)); // false!
        // System.out.println("nan1.equals(nan2): " + nan1.equals(nan2)); // ОШИБКА: double не объект!
        // У примитивов нет метода .equals().

        // Всегда используйте isNaN()
        System.out.println("isNaN(nan1): " + Double.isNaN(nan1)); // true
        System.out.println("isNaN(0.0): " + Double.isNaN(0.0));   // false

        // Сравнение с бесконечностью
        double inf = Double.POSITIVE_INFINITY;
        System.out.println("inf > 1000000: " + (inf > 1000000)); // true
        System.out.println("inf == inf: " + (inf == inf));       // true
    }

    private static void demonstrateEdgeCases() {
        System.out.println("\n=== Граничные случаи ===");

        // MIN_VALUE — это очень маленькое положительное число, а не самое маленькое
        System.out.println("MIN_VALUE: " + Double.MIN_VALUE); // ~4.9e-324
        System.out.println("MAX_VALUE: " + Double.MAX_VALUE); // ~1.8e+308

        // Самое маленькое (по модулю) отрицательное число
        double minNegative = -Double.MAX_VALUE;
        System.out.println("minNegative: " + minNegative);

        // Проверка на "почти ноль"
        double verySmall = 1e-320;
        if (Math.abs(verySmall) < Double.MIN_VALUE) {
            System.out.println("Число меньше минимального положительного!");
        }

        // Округление ошибок
        double a = 0.1 + 0.2;
        System.out.println("0.1 + 0.2 = " + a); // 0.30000000000000004 — не 0.3!
        System.out.println("Равно 0.3? " + (a == 0.3)); // false
        System.out.println("Разница: " + Math.abs(a - 0.3));
    }
}
