package com.javarush.example.slide08scheduler;

// Демонстрация планировщика с приоритетами
public class Slide08_PriorityScheduler {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Планировщик с Приоритетами ===");

        PriorityTaskScheduler priorityScheduler = new PriorityTaskScheduler();

        // Добавляем задачи с разными приоритетами
        System.out.println("Добавляем задачи:");
        priorityScheduler.schedule(() -> System.out.println("Задача с НИЗКИМ приоритетом (1)"), 1);
        priorityScheduler.schedule(() -> System.out.println("Задача с ВЫСОКИМ приоритетом (9)"), 9);
        priorityScheduler.schedule(() -> System.out.println("Задача со СРЕДНИМ приоритетом (5)"), 5);
        priorityScheduler.schedule(() -> System.out.println("Задача с ОЧЕНЬ ВЫСОКИМ приоритетом (10)"), 10);
        priorityScheduler.schedule(() -> System.out.println("Задача с НИЗКИМ приоритетом (2)"), 2);

        Thread.sleep(2000);
        priorityScheduler.shutdown();

        System.out.println("\nЗадачи выполняются в порядке приоритета, а не добавления!");
    }
}