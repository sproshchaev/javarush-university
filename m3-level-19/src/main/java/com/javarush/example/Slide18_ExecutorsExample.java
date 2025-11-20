package com.javarush.example;

import java.util.concurrent.*;
import java.util.*;

public class Slide18_ExecutorsExample {

    // 1. Future и Callable - асинхронные задачи с результатом
    public static void demonstrateFutureAndCallable() throws Exception {
        System.out.println("1. FUTURE И CALLABLE - АСИНХРОННЫЕ ЗАДАЧИ:");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Callable задача с возвращаемым значением
        Callable<String> task = () -> {
            Thread.sleep(1000);
            return "Результат из потока " + Thread.currentThread().getName();
        };

        // Запускаем несколько задач
        Future<String> future1 = executor.submit(task);
        Future<String> future2 = executor.submit(task);

        System.out.println("Задачи запущены, ждем результаты...");

        // Блокируемся до получения результатов
        System.out.println("Результат 1: " + future1.get());
        System.out.println("Результат 2: " + future2.get());

        System.out.println("isDone future1: " + future1.isDone());
        System.out.println("isDone future2: " + future2.isDone());

        executor.shutdown();
        System.out.println();
    }

    // 2. FutureTask - конкретная реализация Future
    public static void demonstrateFutureTask() throws Exception {
        System.out.println("2. FUTURETASK - КОНКРЕТНАЯ РЕАЛИЗАЦИЯ:");

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(500);
            return "Результат FutureTask";
        });

        // Запускаем вручную через Thread
        new Thread(futureTask).start();

        System.out.println("FutureTask запущен...");
        System.out.println("Результат: " + futureTask.get());
        System.out.println();
    }

    // 3. ExecutorService - различные пулы потоков
    public static void demonstrateExecutorServices() throws InterruptedException {
        System.out.println("3. EXECUTORSERVICE - РАЗЛИЧНЫЕ ПУЛЫ:");

        // FixedThreadPool - фиксированное количество потоков
        System.out.println("--- FixedThreadPool ---");
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            fixedPool.execute(() -> {
                System.out.println("FixedPool задача " + taskId + " в " + Thread.currentThread().getName());
            });
        }
        fixedPool.shutdown();
        fixedPool.awaitTermination(1, TimeUnit.SECONDS);

        // CachedThreadPool - динамическое создание потоков
        System.out.println("--- CachedThreadPool ---");
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            cachedPool.execute(() -> {
                System.out.println("CachedPool задача " + taskId + " в " + Thread.currentThread().getName());
            });
        }
        cachedPool.shutdown();
        cachedPool.awaitTermination(1, TimeUnit.SECONDS);

        // SingleThreadExecutor - один поток
        System.out.println("--- SingleThreadExecutor ---");
        ExecutorService singleThread = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            singleThread.execute(() -> {
                System.out.println("SingleThread задача " + taskId + " в " + Thread.currentThread().getName());
            });
        }
        singleThread.shutdown();
        singleThread.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println();
    }

    // 4. ScheduledExecutorService - отложенные и периодические задачи
    public static void demonstrateScheduledExecutor() throws InterruptedException {
        System.out.println("4. SCHEDULEDEXECUTORSERVICE - ПЛАНИРОВАНИЕ:");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Задача с задержкой
        System.out.println("Запланирована задача с задержкой 2 секунды");
        ScheduledFuture<?> delayedTask = scheduler.schedule(() -> {
            System.out.println("Выполнено с задержкой!");
        }, 2, TimeUnit.SECONDS);

        // Периодическая задача
        System.out.println("Запускаем периодическую задачу (каждую секунду)");
        ScheduledFuture<?> periodicTask = scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Периодическая задача выполняется...");
        }, 0, 1, TimeUnit.SECONDS);

        // Ждем 5 секунд и отменяем периодическую задачу
        Thread.sleep(5000);
        periodicTask.cancel(false);
        System.out.println("Периодическая задача отменена");

        scheduler.shutdown();
        System.out.println();
    }

    // 5. CompletionService - получение результатов по готовности
    public static void demonstrateCompletionService() throws Exception {
        System.out.println("5. COMPLETIONSERVICE - РЕЗУЛЬТАТЫ ПО ГОТОВНОСТИ:");

        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        // Запускаем задачи с разной продолжительностью
        completionService.submit(() -> {
            Thread.sleep(3000);
            return "Медленная задача";
        });

        completionService.submit(() -> {
            Thread.sleep(1000);
            return "Быстрая задача";
        });

        completionService.submit(() -> {
            Thread.sleep(2000);
            return "Средняя задача";
        });

        System.out.println("Получаем результаты по готовности:");

        // Получаем результаты в порядке завершения
        for (int i = 0; i < 3; i++) {
            Future<String> completedFuture = completionService.take();
            System.out.println("Готово: " + completedFuture.get());
        }

        executor.shutdown();
        System.out.println();
    }

    // 6. ThreadFactory - кастомизация создания потоков
    public static void demonstrateThreadFactory() throws InterruptedException {
        System.out.println("6. THREADFACTORY - КАСТОМИЗАЦИЯ ПОТОКОВ:");

        ThreadFactory customFactory = r -> {
            Thread thread = new Thread(r);
            thread.setName("CustomThread-" + System.currentTimeMillis());
            thread.setDaemon(true);
            thread.setPriority(Thread.MAX_PRIORITY);
            return thread;
        };

        ExecutorService customExecutor = Executors.newFixedThreadPool(2, customFactory);

        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            customExecutor.execute(() -> {
                System.out.println("Задача " + taskId + " в потоке: " + Thread.currentThread().getName());
                System.out.println("Приоритет: " + Thread.currentThread().getPriority());
                System.out.println("Демон: " + Thread.currentThread().isDaemon());
            });
        }

        customExecutor.shutdown();
        customExecutor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== EXECUTORS: УПРАВЛЕНИЕ АСИНХРОННЫМИ ЗАДАЧАМИ ===\n");

        demonstrateFutureAndCallable();
        demonstrateFutureTask();
        demonstrateExecutorServices();
        demonstrateScheduledExecutor();
        demonstrateCompletionService();
        demonstrateThreadFactory();

        System.out.println("=== ВЫВОД ===");
        System.out.println("✓ Future/Callable - асинхронные задачи с результатом");
        System.out.println("✓ ExecutorService - различные стратегии пулов потоков");
        System.out.println("✓ ScheduledExecutorService - планирование задач");
        System.out.println("✓ CompletionService - результаты по готовности");
        System.out.println("✓ ThreadFactory - кастомизация создания потоков");
    }
}