package com.javarush.example.slide08scheduler;

import java.util.*;

// Политика LIFO (Last-In-First-Out) - стек
public class LIFOSchedulingPolicy implements SchedulingPolicy {
    private final Deque<Runnable> stack = new LinkedList<>();

    @Override
    public synchronized void addTask(Runnable task) {
        stack.push(task);
        notifyAll();
    }

    @Override
    public synchronized Runnable getNextTask() {
        while (stack.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        return stack.pop();
    }

    @Override
    public synchronized boolean hasTasks() {
        return !stack.isEmpty();
    }
}