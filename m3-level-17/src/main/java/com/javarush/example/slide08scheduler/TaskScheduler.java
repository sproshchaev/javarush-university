package com.javarush.example.slide08scheduler;

// Планировщик задач
public class TaskScheduler {
    private final SchedulingPolicy policy;
    private volatile boolean running = true;
    private final Thread workerThread;

    public TaskScheduler(SchedulingPolicy policy) {
        this.policy = policy;
        this.workerThread = new Thread(this::runScheduler, "Scheduler-Thread");
        this.workerThread.start();
    }

    private void runScheduler() {
        while (running || policy.hasTasks()) {
            Runnable task = policy.getNextTask();
            if (task != null) {
                System.out.println("Планировщик [" + policy.getClass().getSimpleName() +
                        "] запускает задачу в потоке: " + Thread.currentThread().getName());
                try {
                    task.run();
                } catch (Exception e) {
                    System.err.println("Ошибка выполнения задачи: " + e.getMessage());
                }
            }
        }
    }

    public void schedule(Runnable task) {
        policy.addTask(task);
    }

    // Геттер для политики
    public SchedulingPolicy getPolicy() {
        return policy;
    }

    public void shutdown() {
        running = false;
        workerThread.interrupt();
    }
}