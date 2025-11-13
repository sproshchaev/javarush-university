package com.javarush.example;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Демонстрация работы со Stack Trace в обработке исключений.
 * Показывает, как исключения автоматически записывают стек вызовов
 * и как извлекать эту информацию для отладки.
 */
public class Slide07_ExceptionStackTrace {

    public static void main(String[] args) {
        System.out.println("=== Stack Trace в обработке исключений ===");

        // Демонстрация автоматического заполнения stack trace
        demonstrateStackTraceCapture();

        System.out.println("\n=== Разные способы получения Stack Trace ===");
        // Показываем различные методы работы со stack trace
        demonstrateStackTraceMethods();

        System.out.println("\n=== Анализ Stack Trace в коде ===");
        // Показываем, как анализировать stack trace программно
        analyzeStackTraceProgrammatically();
    }

    /**
     * Демонстрирует, как исключение автоматически захватывает stack trace
     */
    public static void demonstrateStackTraceCapture() {
        System.out.println("1. Автоматическое заполнение Stack Trace:");

        try {
            // Запускаем цепочку вызовов, которая закончится исключением
            startProcessingChain();
        } catch (RuntimeException e) {
            System.out.println("Перехвачено исключение: " + e.getMessage());
            System.out.println("\n=== ВЫВОД printStackTrace() ===");
            // Классический способ - вывод в консоль
            e.printStackTrace();
        }
    }

    /**
     * Демонстрирует различные методы работы со stack trace
     */
    public static void demonstrateStackTraceMethods() {
        System.out.println("2. Различные методы получения Stack Trace:");

        try {
            performRiskyOperation();
        } catch (IllegalStateException e) {
            System.out.println("Исключение перехвачено!");

            // Способ 1: printStackTrace() - вывод в stderr
            System.out.println("\nа) printStackTrace():");
            e.printStackTrace();

            // Способ 2: getStackTrace() - получение массива для анализа
            System.out.println("\nб) getStackTrace():");
            StackTraceElement[] stackTrace = e.getStackTrace();
            System.out.println("Количество элементов в stack trace: " + stackTrace.length);

            // Способ 3: printStackTrace с указанием потока вывода
            System.out.println("\nв) printStackTrace(PrintStream):");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println("Stack trace как строка:\n" + sw.toString());
        }
    }

    /**
     * Показывает, как анализировать stack trace в программном коде
     */
    public static void analyzeStackTraceProgrammatically() {
        System.out.println("3. Программный анализ Stack Trace:");

        try {
            deepNestedCall();
        } catch (ArithmeticException e) {
            System.out.println("Анализируем исключение: " + e.getMessage());

            StackTraceElement[] stackTrace = e.getStackTrace();

            System.out.println("\nПуть выполнения до ошибки:");
            System.out.printf("%-3s %-25s %-20s %-15s %-8s%n",
                    "#", "Класс", "Метод", "Файл", "Строка");
            System.out.println("--------------------------------------------------------------------------------");

            for (int i = 0; i < stackTrace.length; i++) {
                StackTraceElement element = stackTrace[i];
                System.out.printf("%-3d %-25s %-20s %-15s %-8d%n",
                        i,
                        shortenClassName(element.getClassName()),
                        element.getMethodName(),
                        element.getFileName(),
                        element.getLineNumber());
            }

            // Анализ конкретных элементов
            System.out.println("\nКлючевая информация:");
            if (stackTrace.length > 0) {
                StackTraceElement errorLocation = stackTrace[0];
                System.out.println("Ошибка произошла в: " + errorLocation.getClassName() +
                        "." + errorLocation.getMethodName() +
                        " (строка " + errorLocation.getLineNumber() + ")");
            }

            if (stackTrace.length > 1) {
                StackTraceElement caller = stackTrace[1];
                System.out.println("Вызвано из: " + caller.getClassName() +
                        "." + caller.getMethodName());
            }
        }
    }

    // ========== Вспомогательные методы для создания цепочек вызовов ==========

    public static void startProcessingChain() {
        System.out.println("  startProcessingChain() -> validateInput()");
        validateInput(null);
    }

    public static void validateInput(String data) {
        System.out.println("  validateInput() -> processData()");
        processData(data);
    }

    public static void processData(String data) {
        System.out.println("  processData() -> сохраняем данные");
        if (data == null) {
            throw new RuntimeException("Данные не могут быть null!");
        }
    }

    public static void performRiskyOperation() {
        System.out.println("  performRiskyOperation() -> checkPreconditions()");
        checkPreconditions();
    }

    public static void checkPreconditions() {
        System.out.println("  checkPreconditions() -> бросаем исключение");
        throw new IllegalStateException("Нарушены предварительные условия!");
    }

    public static void deepNestedCall() {
        System.out.println("  deepNestedCall() -> levelOne()");
        levelOne();
    }

    public static void levelOne() {
        System.out.println("  levelOne() -> levelTwo()");
        levelTwo();
    }

    public static void levelTwo() {
        System.out.println("  levelTwo() -> levelThree()");
        levelThree();
    }

    public static void levelThree() {
        System.out.println("  levelThree() -> деление на ноль");
        int result = 10 / 0; // ArithmeticException
    }

    /**
     * Укорачивает имя класса для лучшего отображения
     */
    private static String shortenClassName(String className) {
        if (className.startsWith("com.javarush.example")) {
            return "..." + className.substring(className.lastIndexOf('.'));
        }
        return className;
    }
}
