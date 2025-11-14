package com.javarush.example.slide08scheduler;

import java.util.*;

// Задача с приоритетом
class PriorityTask implements Comparable<PriorityTask> {
    private final Runnable task;
    private final int priority;
    private final long sequenceNumber;
    private static long nextSequence = 0;

    public PriorityTask(Runnable task, int priority) {
        this.task = task;
        this.priority = priority;
        this.sequenceNumber = nextSequence++;
    }

    public Runnable getTask() {
        return task;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(PriorityTask other) {
        // Сначала по приоритету (высокий приоритет первый), потом по порядку добавления
        int priorityCompare = Integer.compare(other.priority, this.priority); // обратный порядок
        if (priorityCompare != 0) {
            return priorityCompare;
        }
        return Long.compare(this.sequenceNumber, other.sequenceNumber);
    }
}