package com.javarush.example;

import java.util.*;

/**
 * Примеры удаления элементов из коллекции во время итерации.
 * Демонстрирует три способа обхода ограничения цикла for-each.
 */
public class Slide09_RemoveInForEachExample {

    public static void main(String[] args) {
        demonstrateProblemWithForEach();
        demonstrateSolution1_UsingDifferentLoop();
        demonstrateSolution2_UsingExplicitIterator();
        demonstrateSolution3_UsingCopyOfCollection();
        demonstrateComparisonAndBestPractices();
        demonstratePracticalUseCase();
    }

    private static void demonstrateProblemWithForEach() {
        System.out.println("=== Проблема: удаление в цикле for-each ===");

        Set<Integer> numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("Исходный набор: " + numbers);

        // Попытка удалить чётные числа в for-each
        try {
            for (Integer number : numbers) {
                if (number % 2 == 0) {
                    numbers.remove(number); // ConcurrentModificationException!
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("⚠️ Удаление элемента в цикле for-each вызвало ConcurrentModificationException!");
        }

        System.out.println("Коллекция осталась неизменной: " + numbers);
    }

    private static void demonstrateSolution1_UsingDifferentLoop() {
        System.out.println("\n=== Решение 1: Использование другого типа цикла ===");

        // Для List можно использовать цикл for с индексом (в обратном порядке!)
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Date");

        System.out.println("Исходный список: " + list);

        // Удаляем элементы, начинающиеся на 'B'
        // Важно: идём с конца, чтобы индексы не сбивались
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).startsWith("B")) {
                list.remove(i);
            }
        }

        System.out.println("После удаления: " + list);

        // Для Set или Map — лучше использовать итератор (см. решение 2)
        // Но можно использовать цикл while с removeIf (Java 8+)
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Programming");
        set.add("OOP");
        set.add("Python");

        System.out.println("Исходное множество: " + set);

        set.removeIf(tag -> tag.length() > 4); // удаляем теги длиннее 4 символов

        System.out.println("После удаления: " + set);
    }

    private static void demonstrateSolution2_UsingExplicitIterator() {
        System.out.println("\n=== Решение 2: Явное использование итератора ===");

        // Создаём множество
        Set<Integer> numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("Исходный набор: " + numbers);

        // Получаем итератор
        Iterator<Integer> intIt = numbers.iterator(); // Используем другое имя

        // Обходим и удаляем чётные числа
        while (intIt.hasNext()) {
            Integer number = intIt.next();
            if (number % 2 == 0) {
                intIt.remove(); // безопасное удаление через итератор
            }
        }

        System.out.println("После удаления чётных: " + numbers);

        // Аналогично для списка
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Date");

        System.out.println("Исходный список: " + list);

        Iterator<String> stringIt = list.iterator(); // Используем другое имя
        while (stringIt.hasNext()) {
            String fruit = stringIt.next();
            if (fruit.startsWith("C")) {
                stringIt.remove();
            }
        }

        System.out.println("После удаления: " + list);

    }

    private static void demonstrateSolution3_UsingCopyOfCollection() {
        System.out.println("\n=== Решение 3: Использование копии коллекции ===");

        // Создаём множество
        Set<Integer> numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("Исходный набор: " + numbers);

        // Создаём копию
        Set<Integer> copyNumbers = new HashSet<>(numbers);

        // Итерируем по копии, удаляем из оригинала
        for (Integer number : copyNumbers) {
            if (number % 2 == 0) {
                numbers.remove(number);
            }
        }

        System.out.println("После удаления чётных: " + numbers);

        // Для списка
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Date");

        System.out.println("Исходный список: " + list);

        // Создаём копию
        List<String> copyList = new ArrayList<>(list);

        // Итерируем по копии
        for (String fruit : copyList) {
            if (fruit.startsWith("B")) {
                list.remove(fruit);
            }
        }

        System.out.println("После удаления: " + list);

        System.out.println("✅ Копия коллекции позволяет безопасно удалять элементы.");
        System.out.println("⚠️ Но это требует дополнительной памяти и может быть медленнее.");
    }

    private static void demonstrateComparisonAndBestPractices() {
        System.out.println("\n=== Сравнение способов и лучшие практики ===");

        // Способ 1: Цикл for с индексом (для List)
        // Плюсы: простой, быстрый.
        // Минусы: работает только для List, нужно идти с конца.

        // Способ 2: Явный итератор
        // Плюсы: работает со всеми коллекциями, гибкий, безопасный.
        // Минусы: немного более многословный.

        // Способ 3: Копия коллекции
        // Плюсы: очень безопасный, легко понять.
        // Минусы: требует дополнительной памяти, может быть медленнее.

        System.out.println("✅ Рекомендуемый способ: Явный итератор — универсальный и надёжный.");
        System.out.println("✅ Альтернатива: Метод removeIf (если поддерживается Java 8+).");
        System.out.println("⚠️ Избегайте копирования, если коллекция большая.");

        // Пример с removeIf (Java 8+)
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Date");

        System.out.println("Исходный список: " + list);

        list.removeIf(fruit -> fruit.startsWith("D"));

        System.out.println("После удаления через removeIf: " + list);
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Очистка списка задач ===");

        // Создаём список задач
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Design", "High", false));
        tasks.add(new Task("Code", "Medium", true)); // завершена
        tasks.add(new Task("Test", "Low", false));
        tasks.add(new Task("Deploy", "High", true)); // завершена

        System.out.println("Все задачи:");
        for (Task task : tasks) {
            System.out.println("  " + task);
        }

        // Удаляем завершённые задачи
        // Способ 1: Явный итератор
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.isCompleted()) {
                it.remove();
            }
        }

        System.out.println("Задачи после удаления завершённых:");
        for (Task task : tasks) {
            System.out.println("  " + task);
        }

        // Способ 2: Копия коллекции
        tasks.add(new Task("Review", "Medium", true)); // добавим ещё одну завершённую

        System.out.println("Добавили новую завершённую задачу:");
        for (Task task : tasks) {
            System.out.println("  " + task);
        }

        // Создаём копию
        List<Task> copyTasks = new ArrayList<>(tasks);

        // Удаляем из оригинала
        for (Task task : copyTasks) {
            if (task.isCompleted()) {
                tasks.remove(task);
            }
        }

        System.out.println("Задачи после удаления через копию:");
        for (Task task : tasks) {
            System.out.println("  " + task);
        }
    }

    // Вспомогательный класс для задачи
    static class Task {
        private String name;
        private String priority;
        private boolean completed;

        public Task(String name, String priority, boolean completed) {
            this.name = name;
            this.priority = priority;
            this.completed = completed;
        }

        public String getName() {
            return name;
        }

        public String getPriority() {
            return priority;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        @Override
        public String toString() {
            return "Task{" + "name='" + name + "', priority='" + priority + "', completed=" + completed + '}';
        }
    }
}
