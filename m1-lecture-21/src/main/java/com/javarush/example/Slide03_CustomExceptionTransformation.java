package com.javarush.example;

/**
 * Демонстрация создания и использования собственного исключения.
 * Показан типичный сценарий: перехват стандартного исключения
 * и "трансформация" его в свое, более специфичное.
 */
public class Slide03_CustomExceptionTransformation {

    // Вложенный статический класс для нашего кастомного исключения.
    // Наследуется от Exception, значит является ПРОВЕРЯЕМЫМ (checked exception).
    static class MyCustomException extends Exception {
        // Можно добавить конструкторы для передачи сообщения или причины исключения.
        public MyCustomException() {
            super("Произошло мое собственное исключение!");
        }

        public MyCustomException(String message) {
            super(message);
        }

        public MyCustomException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Демонстрация создания своих исключений ===");

        try {
            dangerousMethod();
        } catch (MyCustomException e) {
            // Ловим уже наше собственное исключение, брошенное в методе dangerousMethod
            System.err.println("В методе main перехвачено: " + e.getMessage());
            // e.printStackTrace(); // Раскомментируйте, чтобы увидеть полный стек-трейс
        }
    }

    /**
     * Метод, в котором происходит перехват стандартного исключения
     * и бросок собственного.
     */
    public static void dangerousMethod() throws MyCustomException {
        // Создаем объект нашего исключения заранее (или можно создать прямо в throw)
        // MyCustomException myException = new MyCustomException();

        try {
            // Код, который может привести к ошибке (деление на ноль)
            int numerator = 10;
            int denominator = 0;
            int result = numerator / denominator; // Здесь будет выброшено ArithmeticException
            System.out.println("Результат: " + result); // Эта строка не выполнится

        } catch (ArithmeticException e) {
            // Перехватываем стандартное арифметическое исключение
            System.out.println("1. В dangerousMethod перехвачено стандартное исключение: " + e);

            // Создаем и бросаем наше собственное исключение.
            // Используем конструктор с сообщением и причиной (исходным исключением).
            // Это лучшая практика, так как сохраняется информация о корне проблемы.
            System.out.println("2. Трансформируем его в MyCustomException и бросаем дальше.");
            throw new MyCustomException("Не удалось выполнить критическую операцию!", e);
        }
    }
}