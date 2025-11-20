package com.javarush.example;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Slide17_SynchronizersExample {

    // 1. CountDownLatch - ожидание завершения N операций
    public static void demonstrateCountDownLatch() throws InterruptedException {
        System.out.println("1. COUNTDOWNLATCH - ОЖИДАНИЕ ЗАВЕРШЕНИЯ:");

        int threadCount = 3;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 1; i <= threadCount; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    Thread.sleep(threadId * 1000L); // Имитация работы
                    System.out.println("Поток " + threadId + " завершил работу");
                    latch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        System.out.println("Главный поток ждет завершения " + threadCount + " потоков...");
        latch.await();
        System.out.println("Все потоки завершили работу! Продолжаем...\n");
    }

    // 2. Semaphore - ограничение доступа к ресурсу
    public static void demonstrateSemaphore() throws InterruptedException {
        System.out.println("2. SEMAPHORE - ОГРАНИЧЕНИЕ ДОСТУПА:");

        Semaphore semaphore = new Semaphore(2); // Максимум 2 потока одновременно
        int totalThreads = 5;

        for (int i = 1; i <= totalThreads; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + threadId + " пытается получить доступ");
                    semaphore.acquire();
                    System.out.println("Поток " + threadId + " получил доступ (свободно: " + semaphore.availablePermits() + ")");

                    Thread.sleep(2000); // Работа с ресурсом

                    semaphore.release();
                    System.out.println("Поток " + threadId + " освободил доступ");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
            Thread.sleep(500); // Запускаем потоки с задержкой
        }

        Thread.sleep(8000); // Ждем завершения
        System.out.println();
    }

    // 3. CyclicBarrier - синхронизация в одной точке
    public static void demonstrateCyclicBarrier() throws InterruptedException {
        System.out.println("3. CYCLICBARRIER - СИНХРОНИЗАЦИЯ В ТОЧКЕ:");

        int parties = 3;
        CyclicBarrier barrier = new CyclicBarrier(parties, () -> {
            System.out.println("=== Все потоки достигли барьера! Продолжаем вместе ===");
        });

        for (int i = 1; i <= parties; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("Поток " + threadId + " начал работу");
                    Thread.sleep(threadId * 1000L); // Разная продолжительность работы
                    System.out.println("Поток " + threadId + " дошел до барьера");

                    barrier.await(); // Ждем остальных

                    System.out.println("Поток " + threadId + " продолжил работу после барьера");
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        Thread.sleep(6000);
        System.out.println();
    }

    // 4. Exchanger - обмен данными между потоками
    public static void demonstrateExchanger() throws InterruptedException {
        System.out.println("4. EXCHANGER - ОБМЕН ДАННЫМИ:");

        Exchanger<String> exchanger = new Exchanger<>();

        Thread producer = new Thread(() -> {
            try {
                String data = "Данные от производителя";
                System.out.println("Producer отправляет: " + data);
                String response = exchanger.exchange(data);
                System.out.println("Producer получил: " + response);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(1000); // Consumer "думает"
                String data = "Данные от потребителя";
                System.out.println("Consumer отправляет: " + data);
                String response = exchanger.exchange(data);
                System.out.println("Consumer получил: " + response);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        System.out.println();
    }

    // 5. Phaser - продвинутый барьер
    public static void demonstratePhaser() {
        System.out.println("5. PHASER - ПРОДВИНУТЫЙ БАРЬЕР:");

        Phaser phaser = new Phaser(1); // Регистрируем главный поток

        for (int i = 1; i <= 3; i++) {
            final int threadId = i;
            phaser.register(); // Регистрируем новый поток
            new Thread(() -> {
                System.out.println("Поток " + threadId + " начал фазу " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance(); // Ждем всех

                System.out.println("Поток " + threadId + " завершил фазу " + (phaser.getPhase() - 1));
                phaser.arriveAndDeregister(); // Завершаем участие
            }).start();
        }

        phaser.arriveAndAwaitAdvance(); // Главный поток ждет
        System.out.println("Все потоки завершили первую фазу\n");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== SYNCHRONIZERS: ИНСТРУМЕНТЫ СИНХРОНИЗАЦИИ ===\n");

        demonstrateCountDownLatch();
        demonstrateSemaphore();
        demonstrateCyclicBarrier();
        demonstrateExchanger();
        demonstratePhaser();

        System.out.println("=== ВЫВОД ===");
        System.out.println("✓ CountDownLatch - ожидание завершения операций");
        System.out.println("✓ Semaphore - ограничение одновременного доступа");
        System.out.println("✓ CyclicBarrier - синхронизация в точке");
        System.out.println("✓ Exchanger - обмен данными между потоками");
        System.out.println("✓ Phaser - гибкая синхронизация по фазам");
    }
}