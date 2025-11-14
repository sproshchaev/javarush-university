package com.javarush.example.slide07monitor;

// Классический монитор - ограниченный буфер (Bounded Buffer)
class BoundedBuffer {
    private final String[] buffer;
    private int count = 0;
    private int putIndex = 0;
    private int takeIndex = 0;

    public BoundedBuffer(int size) {
        buffer = new String[size];
    }

    // Производитель кладет элемент в буфер
    public synchronized void put(String item) throws InterruptedException {
        // Если буфер полон - ждем
        while (count == buffer.length) {
            System.out.println(Thread.currentThread().getName() + " ждет - буфер полон");
            wait();
        }

        // Кладем элемент
        buffer[putIndex] = item;
        putIndex = (putIndex + 1) % buffer.length;
        count++;

        System.out.println(Thread.currentThread().getName() + " положил: " + item +
                " (размер: " + count + "/" + buffer.length + ")");

        // Уведомляем потребителей, что появились данные
        notifyAll();
    }

    // Потребитель забирает элемент из буфера
    public synchronized String take() throws InterruptedException {
        // Если буфер пуст - ждем
        while (count == 0) {
            System.out.println(Thread.currentThread().getName() + " ждет - буфер пуст");
            wait();
        }

        // Забираем элемент
        String item = buffer[takeIndex];
        buffer[takeIndex] = null;
        takeIndex = (takeIndex + 1) % buffer.length;
        count--;

        System.out.println(Thread.currentThread().getName() + " забрал: " + item +
                " (размер: " + count + "/" + buffer.length + ")");

        // Уведомляем производителей, что появилось место
        notifyAll();

        return item;
    }
}

// Демонстрация работы монитора - паттерн Producer-Consumer
public class Slide07_BoundedBufferMonitor {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(3); // Буфер на 3 элемента

        System.out.println("=== Демонстрация Монитора (Bounded Buffer) ===");
        System.out.println("Производители и потребители синхронизируются через wait/notify\n");

        // Производители
        Thread producer1 = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.put("Сообщение-A" + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer-A");

        Thread producer2 = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.put("Сообщение-B" + i);
                    Thread.sleep(700);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer-B");

        // Потребители
        Thread consumer1 = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.take();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-1");

        Thread consumer2 = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.take();
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nВсе операции завершены!");
    }
}