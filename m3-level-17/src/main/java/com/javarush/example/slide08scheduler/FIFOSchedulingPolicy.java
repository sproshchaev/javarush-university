package com.javarush.example.slide08scheduler;

import java.util.*;

// Политика FIFO (First-In-First-Out)
public class FIFOSchedulingPolicy implements SchedulingPolicy {
    private final Queue<Runnable> queue = new LinkedList<>();

    @Override
    public synchronized void addTask(Runnable task) {
        queue.offer(task);
        notifyAll(); // Уведомляем планировщик о новой задаче
    }

    @Override
    public synchronized Runnable getNextTask() {
        while (queue.isEmpty()) {
            try {
                wait(); // Ждем, пока появятся задачи
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return queue.poll();
    }

    @Override
    public synchronized boolean hasTasks() {
        return !queue.isEmpty();
    }
}