package com.javarush.example;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Слайды 9-10: Демонстрация проблем видимости и состояния гонки
 */
public class Slide09_10_MemoryVisibilityRaceCondition {

    // Для демонстрации видимости
    private static boolean nonVolatileFlag = true;
    private static volatile boolean volatileFlag = true;

    // Для демонстрации гонки
    private static int unsafeCounter = 0;
    private static final AtomicInteger safeCounter = new AtomicInteger(0);
    private static int synchronizedCounter = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ВИДИМОСТЬ И СОСТОЯНИЕ ГОНКИ (Слайды 9-10) ===\n");

        demonstrateVisibilityProblem();
        demonstrateRaceCondition();
        demonstrateSolutions();
    }

    /**
     * Слайд 9: Демонстрация проблемы видимости изменений между потоками
     */
    private static void demonstrateVisibilityProblem() throws InterruptedException {
        System.out.println("1. ПРОБЛЕМА ВИДИМОСТИ:");

        // Поток с NON-volatile переменной
        Thread nonVolatileThread = new Thread(() -> {
            int iterations = 0;
            while (nonVolatileFlag) {
                iterations++;
            }
            System.out.println("   Non-volatile поток остановился после " + iterations + " итераций");
        });

        // Поток с VOLATILE переменной
        Thread volatileThread = new Thread(() -> {
            int iterations = 0;
            while (volatileFlag) {
                iterations++;
            }
            System.out.println("   Volatile поток остановился после " + iterations + " итераций");
        });

        nonVolatileThread.start();
        volatileThread.start();

        // Даем потокам время запуститься
        Thread.sleep(100);

        // Меняем флаги
        nonVolatileFlag = false;
        volatileFlag = false;

        System.out.println("   Main установил оба флага в false");

        // Ждем завершения потоков
        volatileThread.join(1000);
        nonVolatileThread.join(100);

        if (nonVolatileThread.isAlive()) {
            System.out.println("   ⚠️ Non-volatile поток НЕ увидел изменение! Проблема видимости!");
        }

        System.out.println();
    }

    /**
     * Слайд 10: Демонстрация состояния гонки (race condition)
     */
    private static void demonstrateRaceCondition() throws InterruptedException {
        System.out.println("2. СОСТОЯНИЕ ГОНКИ (RACE CONDITION):");

        int numberOfThreads = 10;
        int incrementsPerThread = 1000;
        Thread[] threads = new Thread[numberOfThreads];

        // Запускаем потоки, которые увеличивают небезопасный счетчик
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    unsafeCounter++;  // НЕ атомарная операция!
                }
            });
            threads[i].start();
        }

        // Ждем завершения всех потоков
        for (Thread thread : threads) {
            thread.join();
        }

        int expected = numberOfThreads * incrementsPerThread;
        System.out.println("   Ожидаемое значение: " + expected);
        System.out.println("   Фактическое значение: " + unsafeCounter);
        System.out.println("   Потеряно значений: " + (expected - unsafeCounter));
        System.out.println();
    }

    /**
     * Демонстрация решений проблем многопоточности
     */
    private static void demonstrateSolutions() throws InterruptedException {
        System.out.println("3. РЕШЕНИЯ ПРОБЛЕМ МНОГОПОТОЧНОСТИ:");

        int numberOfThreads = 10;
        int incrementsPerThread = 1000;

        // Решение 1: Atomic классы
        Thread[] atomicThreads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            atomicThreads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    safeCounter.incrementAndGet();  // Атомарная операция
                }
            });
            atomicThreads[i].start();
        }

        // Решение 2: synchronized блок
        Thread[] syncThreads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    synchronized (Slide09_10_MemoryVisibilityRaceCondition.class) {
                        synchronizedCounter++;  // Синхронизированная операция
                    }
                }
            });
            syncThreads[i].start();
        }

        // Ждем завершения
        for (Thread thread : atomicThreads) thread.join();
        for (Thread thread : syncThreads) thread.join();

        int expected = numberOfThreads * incrementsPerThread;
        System.out.println("   AtomicInteger результат: " + safeCounter.get() + " (ожидалось: " + expected + ")");
        System.out.println("   Synchronized результат: " + synchronizedCounter + " (ожидалось: " + expected + ")");
        System.out.println("   ✅ Оба решения дали правильный результат!");
    }
}