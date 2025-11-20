package com.javarush.example;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Slide24_LocksExample {

    // 1. ReentrantLock - базовые операции
    public static void demonstrateReentrantLock() throws InterruptedException {
        System.out.println("1. REENTRANTLOCK - БАЗОВЫЕ ОПЕРАЦИИ:");

        ReentrantLock lock = new ReentrantLock();
        StringBuilder sharedResource = new StringBuilder();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Поток 1 захватил lock");
                sharedResource.append("Данные от потока 1\n");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
                System.out.println("Поток 1 освободил lock");
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Поток 2 захватил lock");
                sharedResource.append("Данные от потока 2\n");
            } finally {
                lock.unlock();
                System.out.println("Поток 2 освободил lock");
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Общий ресурс:\n" + sharedResource);
        System.out.println();
    }

    // 2. tryLock - попытка захвата без блокировки
    public static void demonstrateTryLock() throws InterruptedException {
        System.out.println("2. TRYLOCK - ПОПЫТКА БЕЗ БЛОКИРОВКИ:");

        ReentrantLock lock = new ReentrantLock();

        // Первый поток захватывает lock надолго
        Thread holder = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Holder захватил lock на 3 секунды");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
                System.out.println("Holder освободил lock");
            }
        });

        // Второй поток пытается получить lock
        Thread tryer = new Thread(() -> {
            try {
                Thread.sleep(500); // Ждем немного

                // Пытаемся получить lock без блокировки
                if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Tryer получил lock!");
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println("Tryer не смог получить lock за 1 секунду");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        holder.start();
        tryer.start();
        holder.join();
        tryer.join();
        System.out.println();
    }

    // 3. Condition - улучшенные wait/notify
    public static void demonstrateCondition() throws InterruptedException {
        System.out.println("3. CONDITION - УЛУЧШЕННЫЕ WAIT/NOTIFY:");

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        // Используем массив для обхода ограничения final
        boolean[] dataReady = new boolean[1];

        // Consumer - ждет данные
        Thread consumer = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("Consumer ждет данные...");
                while (!dataReady[0]) {
                    condition.await(); // Аналог wait()
                }
                System.out.println("Consumer получил данные!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        // Producer - производит данные
        Thread producer = new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(1000); // Имитация работы
                dataReady[0] = true;
                System.out.println("Producer подготовил данные");
                condition.signal(); // Аналог notify()
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        System.out.println();
    }

    // 4. ReadWriteLock - раздельные блокировки для чтения/записи
    public static void demonstrateReadWriteLock() throws InterruptedException {
        System.out.println("4. READWRITELOCK - РАЗДЕЛЬНЫЕ БЛОКИРОВКИ:");

        ReadWriteLock rwLock = new ReentrantReadWriteLock();

        // Используем массив для обхода ограничения final
        String[] sharedData = new String[]{"Исходные данные"};

        // Несколько читателей (работают параллельно)
        for (int i = 1; i <= 3; i++) {
            final int readerId = i;
            new Thread(() -> {
                rwLock.readLock().lock();
                try {
                    System.out.println("Reader " + readerId + " читает: " + sharedData[0]);
                    Thread.sleep(1000); // Чтение занимает время
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    rwLock.readLock().unlock();
                    System.out.println("Reader " + readerId + " завершил чтение");
                }
            }).start();
        }

        // Писатель (работает эксклюзивно)
        Thread writer = new Thread(() -> {
            rwLock.writeLock().lock();
            try {
                System.out.println("Writer начинает запись...");
                sharedData[0] = "Обновленные данные";
                Thread.sleep(2000);
                System.out.println("Writer завершил запись");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                rwLock.writeLock().unlock();
            }
        });

        writer.start();
        writer.join();
        Thread.sleep(1000);
        System.out.println();
    }

    // 5. Fair vs Non-fair ReentrantLock
    public static void demonstrateFairness() throws InterruptedException {
        System.out.println("5. FAIR VS NON-FAIR REENTRANTLOCK:");

        // Нечестный lock (по умолчанию)
        ReentrantLock nonFairLock = new ReentrantLock(false);
        // Честный lock
        ReentrantLock fairLock = new ReentrantLock(true);

        System.out.println("Тестируем нечестный lock...");
        testLockFairness(nonFairLock, "NonFair");

        Thread.sleep(1000);

        System.out.println("Тестируем честный lock...");
        testLockFairness(fairLock, "Fair");
        System.out.println();
    }

    private static void testLockFairness(ReentrantLock lock, String lockType) throws InterruptedException {
        int threadCount = 3;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(threadCount);

        for (int i = 1; i <= threadCount; i++) {
            final int threadId = i;
            new Thread(() -> {
                try {
                    startLatch.await(); // Ждем старта
                    lock.lock();
                    try {
                        System.out.println(lockType + " - Поток " + threadId + " получил lock");
                    } finally {
                        lock.unlock();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    endLatch.countDown();
                }
            }).start();
        }

        // Запускаем все потоки одновременно
        startLatch.countDown();
        endLatch.await();
    }

    // 6. StampedLock - оптимистичные блокировки
    public static void demonstrateStampedLock() throws InterruptedException {
        System.out.println("6. STAMPEDLOCK - ОПТИМИСТИЧНЫЕ БЛОКИРОВКИ:");

        StampedLock stampedLock = new StampedLock();

        // Используем массив для обхода ограничения final
        String[] data = new String[]{"Данные"};

        // Оптимистичное чтение
        Thread optimisticReader = new Thread(() -> {
            long stamp = stampedLock.tryOptimisticRead();
            String currentData = data[0];

            if (!stampedLock.validate(stamp)) {
                // Если данные изменились, получаем пессимистичный lock
                stamp = stampedLock.readLock();
                try {
                    currentData = data[0];
                } finally {
                    stampedLock.unlockRead(stamp);
                }
            }

            System.out.println("Оптимистичный Reader прочитал: " + currentData);
        });

        // Писатель
        Thread writer = new Thread(() -> {
            long stamp = stampedLock.writeLock();
            try {
                data[0] = "Новые данные";
                System.out.println("Writer обновил данные");
            } finally {
                stampedLock.unlockWrite(stamp);
            }
        });

        optimisticReader.start();
        writer.start();
        optimisticReader.join();
        writer.join();
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== LOCKS: ГИБКИЕ МЕХАНИЗМЫ СИНХРОНИЗАЦИИ ===\n");

        demonstrateReentrantLock();
        demonstrateTryLock();
        demonstrateCondition();
        demonstrateReadWriteLock();
        demonstrateFairness();
        demonstrateStampedLock();

        System.out.println("=== ВЫВОД ===");
        System.out.println("✓ ReentrantLock - альтернатива synchronized");
        System.out.println("✓ tryLock() - попытка захвата без блокировки");
        System.out.println("✓ Condition - улучшенные wait/notify");
        System.out.println("✓ ReadWriteLock - раздельные блокировки чтения/записи");
        System.out.println("✓ Fairness - честная/нечестная очередь");
        System.out.println("✓ StampedLock - оптимистичные блокировки для чтения");
    }
}