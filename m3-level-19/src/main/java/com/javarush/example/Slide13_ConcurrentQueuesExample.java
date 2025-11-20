package com.javarush.example;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Slide13_ConcurrentQueuesExample {

    // Демонстрация ConcurrentLinkedQueue
    public static void demonstrateConcurrentLinkedQueue() throws InterruptedException {
        System.out.println("1. CONCURRENTLINKEDQUEUE (FIFO):");

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        AtomicInteger produced = new AtomicInteger(0);
        AtomicInteger consumed = new AtomicInteger(0);

        // Producer поток
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String item = "Item-" + i;
                queue.offer(item);
                produced.incrementAndGet();
                System.out.println("Produced: " + item);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        });

        // Consumer поток
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                String item = queue.poll();
                if (item != null) {
                    consumed.incrementAndGet();
                    System.out.println("Consumed: " + item);
                }
                try { Thread.sleep(150); } catch (InterruptedException e) {}
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Produced: " + produced.get() + ", Consumed: " + consumed.get());
        System.out.println("Очередь не блокирует потоки!\n");
    }

    // Демонстрация ConcurrentLinkedDeque
    public static void demonstrateConcurrentLinkedDeque() {
        System.out.println("2. CONCURRENTLINKEDDEQUE (ДВУСТОРОННЯЯ):");

        ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();

        // Добавляем с обоих концов
        deque.offerFirst("First");  // В начало
        deque.offerLast("Middle");  // В конец
        deque.offerFirst("VeryFirst"); // В начало
        deque.offerLast("Last");    // В конец

        System.out.println("Содержимое: " + deque);

        // Извлекаем с обоих концов
        System.out.println("pollFirst(): " + deque.pollFirst()); // VeryFirst
        System.out.println("pollLast(): " + deque.pollLast());   // Last
        System.out.println("pollFirst(): " + deque.pollFirst()); // First
        System.out.println("Осталось: " + deque);

        // Режим стека (LIFO)
        System.out.println("\n--- РЕЖИМ СТЕКА (LIFO) ---");
        deque.push("Stack1"); // push = addFirst
        deque.push("Stack2");
        deque.push("Stack3");

        System.out.println("Как стек: " + deque);
        System.out.println("pop(): " + deque.pop()); // Stack3 (LIFO)
        System.out.println("pop(): " + deque.pop()); // Stack2
        System.out.println("После pop: " + deque + "\n");
    }

    // Производительность non-blocking очередей
    public static void demonstratePerformance() throws InterruptedException {
        System.out.println("3. ПРОИЗВОДИТЕЛЬНОСТЬ NON-BLOCKING ОЧЕРЕДЕЙ:");

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        int numThreads = 4;
        int operationsPerThread = 10000;

        Thread[] producers = new Thread[numThreads];
        Thread[] consumers = new Thread[numThreads];

        long startTime = System.currentTimeMillis();

        // Запускаем производителей
        for (int i = 0; i < numThreads; i++) {
            producers[i] = new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    queue.offer(j);
                }
            });
            producers[i].start();
        }

        // Запускаем потребителей
        for (int i = 0; i < numThreads; i++) {
            consumers[i] = new Thread(() -> {
                for (int j = 0; j < operationsPerThread; j++) {
                    queue.poll();
                }
            });
            consumers[i].start();
        }

        // Ждем завершения
        for (Thread producer : producers) producer.join();
        for (Thread consumer : consumers) consumer.join();

        long endTime = System.currentTimeMillis();

        System.out.println("Операций: " + (numThreads * operationsPerThread * 2));
        System.out.println("Время: " + (endTime - startTime) + "ms");
        System.out.println("Non-blocking - очень быстрые!\n");
    }

    // Предупреждение про метод size()
    public static void demonstrateSizeWarning() {
        System.out.println("4. ВНИМАНИЕ: МЕТОД SIZE() МОЖЕТ БЫТЬ МЕДЛЕННЫМ");

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        // Быстрая операция
        long start = System.nanoTime();
        queue.offer(1);
        long offerTime = System.nanoTime() - start;

        // Медленная операция (в больших очередях)
        start = System.nanoTime();
        int size = queue.size();
        long sizeTime = System.nanoTime() - start;

        System.out.println("offer() занял: " + offerTime + " ns");
        System.out.println("size() занял: " + sizeTime + " ns");
        System.out.println("Вывод: избегайте частых вызовов size()!\n");
    }

    // Дополнительный метод в классе Slide13_ConcurrentQueuesExample
    public static void demonstrateBlockingQueueNeed() throws InterruptedException {
        System.out.println("5. ПОЧЕМУ НУЖНЫ BLOCKING QUEUES:");

        // Демонстрация проблемы с non-blocking очередью
        System.out.println("--- ПРОБЛЕМА: NON-BLOCKING МОЖЕТ ПЕРЕПОЛНИТЬ ПАМЯТЬ ---");

        ConcurrentLinkedQueue<Integer> nonBlockingQueue = new ConcurrentLinkedQueue<>();
        AtomicInteger memoryWarning = new AtomicInteger(0);

        // Быстрый producer, медленный consumer
        Thread fastProducer = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                nonBlockingQueue.offer(i);
                if (nonBlockingQueue.size() > 50000 && memoryWarning.get() == 0) {
                    System.out.println("⚠️  Очередь разрослась до " + nonBlockingQueue.size() + " элементов!");
                    System.out.println("⚠️  Риск OutOfMemoryError!");
                    memoryWarning.set(1);
                }
            }
        });

        Thread slowConsumer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                nonBlockingQueue.poll();
                try { Thread.sleep(10); } catch (InterruptedException e) {} // Медленно обрабатываем
            }
        });

        fastProducer.start();
        slowConsumer.start();

        Thread.sleep(1000); // Даем поработать 1 секунду
        fastProducer.interrupt();
        slowConsumer.interrupt();

        System.out.println("Размер non-blocking очереди: " + nonBlockingQueue.size());
        System.out.println("Producer добавил 100000, Consumer обработал только 1000");
        System.out.println("Результат: очередь переполнена!\n");

        // Решение: Blocking очередь
        System.out.println("--- РЕШЕНИЕ: BLOCKING QUEUE КОНТРОЛИРУЕТ СКОРОСТЬ ---");

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        AtomicInteger produced = new AtomicInteger(0);
        AtomicInteger consumed = new AtomicInteger(0);

        Thread controlledProducer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    blockingQueue.put(i); // Блокируется если очередь полная
                    produced.incrementAndGet();
                    System.out.println("Producer добавил: " + i + " (размер: " + blockingQueue.size() + ")");
                }
            } catch (InterruptedException e) {}
        });

        Thread controlledConsumer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    Thread.sleep(100); // Медленно обрабатываем
                    Integer item = blockingQueue.take(); // Блокируется если очередь пустая
                    consumed.incrementAndGet();
                    System.out.println("Consumer обработал: " + item + " (размер: " + blockingQueue.size() + ")");
                }
            } catch (InterruptedException e) {}
        });

        controlledProducer.start();
        controlledConsumer.start();

        controlledProducer.join();
        controlledConsumer.join();

        System.out.println("Produced: " + produced.get() + ", Consumed: " + consumed.get());
        System.out.println("Очередь саморегулируется - нет переполнения!");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== CONCURRENT QUEUES: NON-BLOCKING ===\n");

        demonstrateConcurrentLinkedQueue();
        demonstrateConcurrentLinkedDeque();
        demonstratePerformance();
        demonstrateSizeWarning();
        demonstrateBlockingQueueNeed();

        System.out.println("=== ВЫВОД ===");
        System.out.println("✓ ConcurrentLinkedQueue - быстрая FIFO очередь");
        System.out.println("✓ ConcurrentLinkedDeque - двусторонняя очередь + стек");
        System.out.println("✓ Non-blocking - потоки не блокируются");
        System.out.println("✓ Высокая производительность благодаря CAS");
        System.out.println("⚠ size() может быть медленным в больших очередях");
    }
}