package com.javarush.example.slide08scheduler;

// Интерфейс политики планирования
public interface SchedulingPolicy {
    void addTask(Runnable task);
    Runnable getNextTask();
    boolean hasTasks();
}