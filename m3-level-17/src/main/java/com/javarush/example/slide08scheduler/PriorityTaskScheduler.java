package com.javarush.example.slide08scheduler;

// Специальный планировщик для работы с приоритетами
public class PriorityTaskScheduler extends TaskScheduler {
    private final PrioritySchedulingPolicy priorityPolicy;

    public PriorityTaskScheduler() {
        super(new PrioritySchedulingPolicy());
        this.priorityPolicy = (PrioritySchedulingPolicy) getPolicy();
    }

    public void schedule(Runnable task, int priority) {
        priorityPolicy.addTask(task, priority);
    }
}