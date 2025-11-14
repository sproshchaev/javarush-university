package com.javarush.example.slide06locks;

import java.util.concurrent.Semaphore;

// Пример использования семафора для ограничения доступа к ресурсу
class DatabaseConnectionPool {
    private Semaphore semaphore;
    private int connectionCount = 0;
    private final int MAX_CONNECTIONS;

    public DatabaseConnectionPool(int maxConnections) {
        this.MAX_CONNECTIONS = maxConnections;
        this.semaphore = new Semaphore(maxConnections);
    }

    public void useConnection(String user) {
        try {
            System.out.println(user + " пытается получить соединение...");
            semaphore.acquire(); // Получаем разрешение от семафора

            // Критическая секция - работа с соединением
            synchronized (this) {
                connectionCount++;
                System.out.println(user + " получил соединение! Активных соединений: " +
                        connectionCount + "/" + MAX_CONNECTIONS);
            }

            // Имитация работы с базой данных
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Освобождаем соединение
            synchronized (this) {
                connectionCount--;
            }
            semaphore.release(); // Освобождаем разрешение
            System.out.println(user + " освободил соединение. Активных соединений: " +
                    connectionCount + "/" + MAX_CONNECTIONS);
        }
    }
}

// Демонстрация работы семафора
public class Slide06_SemaphoreExample {
    public static void main(String[] args) {
        // Пул соединений на 3 одновременных подключения
        DatabaseConnectionPool pool = new DatabaseConnectionPool(3);

        System.out.println("=== Демонстрация Семафора ===");
        System.out.println("Пул баз данных позволяет только 3 одновременных соединения\n");

        // Создаем 8 пользователей, которые хотят подключиться к базе
        for (int i = 1; i <= 8; i++) {
            final String userName = "Пользователь-" + i;
            new Thread(() -> pool.useConnection(userName)).start();

            // Небольшая задержка между созданием потоков для наглядности
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
