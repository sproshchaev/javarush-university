package com.javarush.example;

/**
 * Детальное изучение класса StackTraceElement.
 * Демонстрирует все основные методы этого класса и их применение.
 */
public class Slide05_StackTraceElementDetails {

    public static void main(String[] args) {
        System.out.println("=== Детали StackTraceElement ===");

        // Демонстрация основных методов StackTraceElement
        showBasicMethods();

        System.out.println("\n=== Анализ цепочки вызовов ===");
        // Создаем цепочку вызовов для демонстрации
        startAnalysis();
    }

    /**
     * Демонстрирует основные методы класса StackTraceElement
     */
    public static void showBasicMethods() {
        System.out.println("Получение информации о текущем выполнении:");

        // Получаем текущий stack trace
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // Элемент, соответствующий текущему методу (showBasicMethods)
        // Обратите внимание: в разных версиях JVM индексы могут немного отличаться
        // Обычно: [0] - getStackTrace, [1] - текущий метод, [2] - вызывающий метод
        StackTraceElement currentElement = findCurrentMethodElement(stackTrace, "showBasicMethods");

        if (currentElement != null) {
            printElementInfo("ТЕКУЩИЙ МЕТОД", currentElement);
        }

        // Показываем информацию о вызывающем методе (main)
        if (stackTrace.length > 2) {
            StackTraceElement callerElement = stackTrace[2];
            printElementInfo("ВЫЗВАВШИЙ МЕТОД", callerElement);
        }
    }

    /**
     * Запускает цепочку вызовов для демонстрации анализа stack trace
     */
    public static void startAnalysis() {
        System.out.println("Запуск анализа цепочки...");
        processData("тестовые данные");
    }

    public static void processData(String data) {
        System.out.println("Обработка данных: " + data);
        validateData(data);
    }

    public static void validateData(String data) {
        System.out.println("Валидация данных...");
        performDeepAnalysis(data);
    }

    public static void performDeepAnalysis(String data) {
        System.out.println("Глубокий анализ...");

        // Получаем полный stack trace для анализа
        StackTraceElement[] fullStackTrace = Thread.currentThread().getStackTrace();

        System.out.println("\n=== ПОЛНЫЙ АНАЛИЗ STACK TRACE ===");
        analyzeStackTrace(fullStackTrace);

        System.out.println("\n=== ПОИСК КОНКРЕТНЫХ МЕТОДОВ ===");
        findSpecificMethods(fullStackTrace);
    }

    /**
     * Анализирует весь stack trace и выводит детальную информацию о каждом элементе
     */
    private static void analyzeStackTrace(StackTraceElement[] stackTrace) {
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
    }

    /**
     * Ищет конкретные методы в stack trace и показывает их детали
     */
    private static void findSpecificMethods(StackTraceElement[] stackTrace) {
        for (StackTraceElement element : stackTrace) {
            // Ищем методы, относящиеся к нашему приложению (игнорируем системные)
            if (element.getClassName().startsWith("com.javarush.example")) {
                System.out.println("\nНАЙДЕН МЕТОД ПРИЛОЖЕНИЯ:");
                printElementInfo("ПРИЛОЖЕНИЕ", element);

                // Демонстрация дополнительных методов (могут быть null)
                System.out.println("  Модуль: " +
                        (element.getModuleName() != null ? element.getModuleName() : "N/A"));
                System.out.println("  Версия модуля: " +
                        (element.getModuleVersion() != null ? element.getModuleVersion() : "N/A"));
            }
        }
    }

    /**
     * Выводит форматированную информацию об элементе stack trace
     */
    private static void printElementInfo(String title, StackTraceElement element) {
        System.out.println("--- " + title + " ---");
        System.out.println("  Класс: " + element.getClassName());
        System.out.println("  Метод: " + element.getMethodName());
        System.out.println("  Файл: " + element.getFileName());
        System.out.println("  Строка: " + element.getLineNumber());
        System.out.println("  Полное описание: " + element.toString());
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

    /**
     * Находит элемент stack trace, соответствующий текущему методу
     * Более надежный способ, чем использование фиксированных индексов
     */
    private static StackTraceElement findCurrentMethodElement(StackTraceElement[] stackTrace, String methodName) {
        for (StackTraceElement element : stackTrace) {
            if (element.getMethodName().equals(methodName)) {
                return element;
            }
        }
        return null;
    }
}