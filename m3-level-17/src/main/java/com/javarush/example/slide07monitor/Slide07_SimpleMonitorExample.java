package com.javarush.example.slide07monitor;

// Простой пример монитора - потокобезопасная коллекция
class ThreadSafeStack {
    private final String[] stack;
    private int top = -1;

    public ThreadSafeStack(int capacity) {
        stack = new String[capacity];
    }

    // synchronized методы обеспечивают мониторный доступ
    public synchronized void push(String item) throws InterruptedException {
        // Если стек полон - ждем
        while (top == stack.length - 1) {
            System.out.println(Thread.currentThread().getName() + " ждет - стек полон");
            wait();
        }

        stack[++top] = item;
        System.out.println(Thread.currentThread().getName() + " добавил: " + item);

        // Уведомляем ожидающих pop()
        notifyAll();
    }

    public synchronized String pop() throws InterruptedException {
        // Если стек пуст - ждем
        while (top == -1) {
            System.out.println(Thread.currentThread().getName() + " ждет - стек пуст");
            wait();
        }

        String item = stack[top];
        stack[top--] = null;
        System.out.println(Thread.currentThread().getName() + " удалил: " + item);

        // Уведомляем ожидающих push()
        notifyAll();

        return item;
    }

    public synchronized boolean isEmpty() {
        return top == -1;
    }

    public synchronized int size() {
        return top + 1;
    }
}

// Демонстрация простого монитора
public class Slide07_SimpleMonitorExample {
    public static void main(String[] args) {
        ThreadSafeStack stack = new ThreadSafeStack(2);

        System.out.println("=== Простой Монитор (synchronized методы) ===");

        Thread pusher1 = new Thread(() -> {
            try {
                stack.push("A");
                stack.push("B");
                stack.push("C"); // Будет ждать, пока освободится место
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Pusher-1");

        Thread pusher2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                stack.push("D");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Pusher-2");

        Thread popper = new Thread(() -> {
            try {
                Thread.sleep(2000); // Даем время наполнить стек
                while (!stack.isEmpty()) {
                    stack.pop();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Popper");

        pusher1.start();
        pusher2.start();
        popper.start();

        try {
            pusher1.join();
            pusher2.join();
            popper.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nПрограмма завершена. Размер стека: " + stack.size());
    }
}