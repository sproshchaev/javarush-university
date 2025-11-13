package com.javarush.example;

import java.io.*;

/**
 * Демонстрация интерфейса AutoCloseable и его роли в try-with-resources.
 * Показывает требования к классам для использования в автоматическом управлении ресурсами.
 */
public class Slide11_AutoCloseableInterface {

    public static void main(String[] args) {
        System.out.println("=== Интерфейс AutoCloseable ===");

        // Демонстрация требования AutoCloseable
        demonstrateAutoCloseableRequirement();

        System.out.println("\n=== Создание собственных ресурсов ===");
        demonstrateCustomResources();

        System.out.println("\n=== Разница между AutoCloseable и Closeable ===");
        demonstrateInterfaceDifference();

        System.out.println("\n=== Проверка на уровне компиляции ===");
        demonstrateCompilationCheck();
    }

    /**
     * Демонстрирует, что только AutoCloseable ресурсы можно использовать в try-with-resources
     */
    public static void demonstrateAutoCloseableRequirement() {
        System.out.println("1. Требование AutoCloseable:");

        // Пример 1: Стандартные классы Java (уже реализуют AutoCloseable)
        System.out.println("а) Стандартные классы Java:");
        try (FileInputStream fis = new FileInputStream("test.txt")) {
            System.out.println("  FileInputStream успешно создан в try-with-resources");
        } catch (FileNotFoundException e) {
            System.out.println("  Файл не найден (ожидаемо), но компиляция прошла успешно!");
        } catch (IOException e) {
            System.out.println("  Ошибка ввода-вывода: " + e.getMessage());
        }

        // Пример 2: Наш собственный ресурс, реализующий AutoCloseable
        System.out.println("\nб) Пользовательский ресурс:");
        try (DatabaseConnection dbConnection = new DatabaseConnection("my-db")) {
            dbConnection.executeQuery("SELECT * FROM users");
        } catch (Exception e) {
            System.out.println("  Ошибка работы с БД: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует создание собственных ресурсов с AutoCloseable
     */
    public static void demonstrateCustomResources() {
        System.out.println("2. Создание собственных ресурсов:");

        try (NetworkConnection connection = new NetworkConnection("https://api.example.com");
             Sensor sensor = new Sensor("Температура");
             FileLogger logger = new FileLogger("app.log")) {

            System.out.println("  Все пользовательские ресурсы созданы:");
            connection.connect();
            sensor.readData();
            logger.log("Приложение запущено");

            System.out.println("  Ресурсы автоматически закроются при выходе из блока");

        } catch (Exception e) {
            System.out.println("  Ошибка: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует разницу между AutoCloseable и Closeable
     */
    public static void demonstrateInterfaceDifference() {
        System.out.println("3. AutoCloseable vs Closeable:");

        System.out.println("а) AutoCloseable (более общий):");
        System.out.println("  - Метод: void close() throws Exception");
        System.out.println("  - Может бросать любое исключение");
        System.out.println("  - Введен в Java 7");

        System.out.println("\nб) Closeable (специализированный для I/O):");
        System.out.println("  - Метод: void close() throws IOException");
        System.out.println("  - Может бросать только IOException");
        System.out.println("  - Существовал до Java 7");

        // Демонстрация совместного использования
        System.out.println("\nв) Совместное использование в try-with-resources:");
        try (MyCloseableResource closeable = new MyCloseableResource();
             MyAutoCloseableResource autoCloseable = new MyAutoCloseableResource()) {

            System.out.println("  Оба типа ресурсов работают в try-with-resources");

        } catch (Exception e) {
            System.out.println("  Ошибка: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует проверку на уровне компиляции
     */
    public static void demonstrateCompilationCheck() {
        System.out.println("4. Проверка на уровне компиляции:");

        System.out.println("  Следующий код НЕ скомпилируется:");
        System.out.println("  ------------------------------------");
        System.out.println("  try (String s = new String(\"test\")) {");
        System.out.println("      System.out.println(s);");
        System.out.println("  }");
        System.out.println("  ------------------------------------");
        System.out.println("  Ошибка: String не реализует AutoCloseable");

        System.out.println("\n  И этот тоже:");
        System.out.println("  ------------------------------------");
        System.out.println("  try (Integer i = Integer.valueOf(42)) {");
        System.out.println("      System.out.println(i);");
        System.out.println("  }");
        System.out.println("  ------------------------------------");
        System.out.println("  Ошибка: Integer не реализует AutoCloseable");
    }

    // ========== Пользовательские классы-ресурсы ==========

    /**
     * Пользовательский ресурс для работы с БД
     */
    static class DatabaseConnection implements AutoCloseable {
        private String databaseName;

        public DatabaseConnection(String databaseName) {
            this.databaseName = databaseName;
            System.out.println("  Подключение к БД: " + databaseName);
        }

        public void executeQuery(String query) {
            System.out.println("  Выполняем запрос: " + query);
        }

        @Override
        public void close() throws Exception {
            System.out.println("  Закрываем подключение к БД: " + databaseName);
        }
    }

    /**
     * Пользовательский ресурс для сетевого соединения
     */
    static class NetworkConnection implements AutoCloseable {
        private String url;

        public NetworkConnection(String url) {
            this.url = url;
        }

        public void connect() {
            System.out.println("  Устанавливаем соединение с: " + url);
        }

        @Override
        public void close() throws Exception {
            System.out.println("  Закрываем сетевое соединение: " + url);
        }
    }

    /**
     * Пользовательский ресурс для датчика
     */
    static class Sensor implements AutoCloseable {
        private String type;

        public Sensor(String type) {
            this.type = type;
            System.out.println("  Инициализирован датчик: " + type);
        }

        public void readData() {
            System.out.println("  Читаем данные с датчика: " + type);
        }

        @Override
        public void close() throws Exception {
            System.out.println("  Отключаем датчик: " + type);
        }
    }

    /**
     * Пользовательский ресурс для логирования в файл
     */
    static class FileLogger implements AutoCloseable {
        private String filename;

        public FileLogger(String filename) {
            this.filename = filename;
        }

        public void log(String message) {
            System.out.println("  Логируем в " + filename + ": " + message);
        }

        @Override
        public void close() throws Exception {
            System.out.println("  Закрываем файл лога: " + filename);
        }
    }

    /**
     * Ресурс, реализующий Closeable (специализированный для I/O)
     */
    static class MyCloseableResource implements Closeable {
        @Override
        public void close() throws IOException {
            System.out.println("  MyCloseableResource закрыт (Closeable)");
        }
    }

    /**
     * Ресурс, реализующий AutoCloseable (общий интерфейс)
     */
    static class MyAutoCloseableResource implements AutoCloseable {
        @Override
        public void close() throws Exception {
            System.out.println("  MyAutoCloseableResource закрыт (AutoCloseable)");
        }
    }
}