package com.javarush.example.slide08doublechecked;

// Демонстрация Double Checked Locking для ленивой инициализации
class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private final String connectionString;

    private DatabaseConnection() {
        // Имитация дорогой операции инициализации
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.connectionString = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("Установлено соединение с базой данных: " + connectionString);
    }

    // Double Checked Locking implementation
    public static DatabaseConnection getInstance() {
        // Первая проверка без синхронизации (быстрая)
        if (instance == null) {
            // Синхронизируем только при первом вызове
            synchronized (DatabaseConnection.class) {
                // Вторая проверка внутри synchronized блока
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void executeQuery(String query) {
        System.out.println("Выполняем запрос: " + query + " через " + connectionString);
    }
}

// Неправильная реализация для сравнения (без volatile)
class BrokenSingleton {
    private static BrokenSingleton instance;

    private BrokenSingleton() {
        System.out.println("Создан BrokenSingleton");
    }

    public static BrokenSingleton getInstance() {
        if (instance == null) {
            synchronized (BrokenSingleton.class) {
                if (instance == null) {
                    instance = new BrokenSingleton(); // Может быть проблема с visibility
                }
            }
        }
        return instance;
    }
}

// Демонстрация Double Checked Locking
public class Slide08_DoubleCheckedLocking {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Демонстрация Double Checked Locking ===");
        System.out.println("Несколько потоков пытаются получить экземпляр синглтона\n");

        // Создаем несколько потоков, которые одновременно запрашивают экземпляр
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                DatabaseConnection connection = DatabaseConnection.getInstance();
                connection.executeQuery("SELECT * FROM users FROM потока " + threadId);
            }, "Thread-" + i);
        }

        // Запускаем все потоки одновременно
        for (Thread thread : threads) {
            thread.start();
        }

        // Ждем завершения всех потоков
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("\nВсе потоки завершили работу. Создан только один экземпляр!");

        // Демонстрация проблемы без volatile (в образовательных целях)
        System.out.println("\n--- Демонстрация проблемы без volatile ---");
        Thread t1 = new Thread(() -> {
            BrokenSingleton broken = BrokenSingleton.getInstance();
        });
        Thread t2 = new Thread(() -> {
            BrokenSingleton broken = BrokenSingleton.getInstance();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}