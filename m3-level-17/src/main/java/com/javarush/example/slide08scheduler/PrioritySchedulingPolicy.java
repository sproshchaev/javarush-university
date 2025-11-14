package com.javarush.example.slide08scheduler;

import java.util.*;

// Политика планирования по приоритетам
public class PrioritySchedulingPolicy implements SchedulingPolicy {
    private final PriorityQueue<PriorityTask> priorityQueue = new PriorityQueue<>();

    @Override
    public synchronized void addTask(Runnable task) {
        // По умолчанию средний приоритет
        addTask(task, 5);
    }

    public synchronized void addTask(Runnable task, int priority) {
        priorityQueue.offer(new PriorityTask(task, priority));
        notifyAll();
    }

    @Override
    public synchronized Runnable getNextTask() {
        while (priorityQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return priorityQueue.poll().getTask();
    }

    @Override
    public synchronized boolean hasTasks() {
        return !priorityQueue.isEmpty();
    }
}