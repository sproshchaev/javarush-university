package com.javarush.example.slide08scheduler;

// Демонстрация планировщика с разными политиками
public class Slide08_TaskScheduler {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Демонстрация Планировщика (Scheduler) ===");
        System.out.println("Разные политики планирования задач\n");

        // Тестируем FIFO политику
        System.out.println("=== FIFO Политика (First-In-First-Out) ===");
        TaskScheduler fifoScheduler = new TaskScheduler(new FIFOSchedulingPolicy());

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            fifoScheduler.schedule(() -> {
                System.out.println("Выполняется FIFO задача " + taskId);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        Thread.sleep(2000);
        fifoScheduler.shutdown();

        System.out.println("\n=== LIFO Политика (Last-In-First-Out) ===");
        TaskScheduler lifoScheduler = new TaskScheduler(new LIFOSchedulingPolicy());

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            lifoScheduler.schedule(() -> {
                System.out.println("Выполняется LIFO задача " + taskId);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        Thread.sleep(2000);
        lifoScheduler.shutdown();

        System.out.println("\nЗаметьте разницу в порядке выполнения задач!");
    }
}