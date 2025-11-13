package com.javarush.example;

/**
 * Демонстрация работы со Stack Trace (цепочкой вызовов методов).
 * Показывает, как получить и проанализировать стек вызовов вручную.
 */
public class Slide04_StackTraceAnalysis {

    public static void main(String[] args) {
        System.out.println("=== Анализ Stack Trace ===");

        // Демонстрация двух способов получения Stack Trace
        demonstrateStackTrace();

        System.out.println("\n=== Продвинутый анализ ===");
        // Более сложный пример с вложенными вызовами
        startDeepCallChain();
    }

    /**
     * Демонстрирует два способа получения Stack Trace
     */
    public static void demonstrateStackTrace() {
        System.out.println("\n1. Способ 1 (в одну строку):");
        // Компактный способ - получаем stack trace сразу
        StackTraceElement[] stackTrace1 = Thread.currentThread().getStackTrace();
        printStackTraceInfo(stackTrace1, 3); // Выводим только первые 3 элемента

        System.out.println("\n2. Способ 2 (в две строки):");
        // Более подробный способ - сначала получаем текущий поток
        Thread currentThread = Thread.currentThread();
        StackTraceElement[] stackTrace2 = currentThread.getStackTrace();
        printStackTraceInfo(stackTrace2, 3);
    }

    /**
     * Запускает цепочку вложенных вызовов для демонстрации полного stack trace
     */
    public static void startDeepCallChain() {
        System.out.println("Запуск цепочки вызовов...");
        levelOne();
    }

    public static void levelOne() {
        System.out.println("--> Уровень 1");
        levelTwo();
    }

    public static void levelTwo() {
        System.out.println("----> Уровень 2");
        levelThree();
    }

    public static void levelThree() {
        System.out.println("------> Уровень 3");

        // Получаем полный stack trace в глубине вызовов
        StackTraceElement[] fullStackTrace = Thread.currentThread().getStackTrace();

        System.out.println("====== ПОЛНЫЙ STACK TRACE ======");
        printDetailedStackTrace(fullStackTrace);

        System.out.println("====== АНАЛИЗ ОТДЕЛЬНЫХ ЭЛЕМЕНТОВ ======");
        analyzeSpecificElements(fullStackTrace);
    }

    /**
     * Выводит основную информацию о stack trace с ограничением по количеству элементов
     */
    private static void printStackTraceInfo(StackTraceElement[] stackTrace, int limit) {
        int count = 0;
        for (StackTraceElement element : stackTrace) {
            if (count >= limit) break;

            System.out.println("  " + element.getClassName() + "." +
                    element.getMethodName() + " (строка " +
                    element.getLineNumber() + ")");
            count++;
        }
    }

    /**
     * Детально выводит всю информацию о stack trace
     */
    private static void printDetailedStackTrace(StackTraceElement[] stackTrace) {
        System.out.println("Всего элементов в стеке: " + stackTrace.length);
        System.out.println("Путь выполнения (сверху - текущий метод):");

        for (int i = 0; i < stackTrace.length; i++) {
            StackTraceElement element = stackTrace[i];
            System.out.printf("%2d. %-40s [Файл: %-15s Строка: %-3d]%n",
                    i,
                    element.getClassName() + "." + element.getMethodName(),
                    element.getFileName(),
                    element.getLineNumber());
        }
    }

    /**
     * Анализирует конкретные элементы stack trace
     */
    private static void analyzeSpecificElements(StackTraceElement[] stackTrace) {
        if (stackTrace.length > 1) {
            // Текущий метод (первый в массиве, но обратите внимание на особенности индексации)
            StackTraceElement current = stackTrace[1]; // [0] обычно getStackTrace, [1] - текущий метод
            System.out.println("Текущий метод: " + current.getMethodName() +
                    " в классе " + current.getClassName());

            // Метод, который вызвал текущий
            if (stackTrace.length > 2) {
                StackTraceElement caller = stackTrace[2];
                System.out.println("Вызван из: " + caller.getMethodName() +
                        " (строка " + caller.getLineNumber() + ")");
            }

            // Корневой метод (обычно main)
            StackTraceElement root = stackTrace[stackTrace.length - 1];
            System.out.println("Корневой метод: " + root.getMethodName());
        }
    }
}
