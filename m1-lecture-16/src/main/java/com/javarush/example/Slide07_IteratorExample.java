package com.javarush.example;

import java.util.*;

/**
 * Пример использования итератора для обхода коллекций.
 * Демонстрирует основные методы и особенности.
 */
public class Slide07_IteratorExample {

    public static void main(String[] args) {
        demonstrateBasicUsage();
        demonstrateWithDifferentCollections();
        demonstrateSafeRemoval();
        demonstrateCommonMistakes();
        demonstrateForEachLoopVsIterator();
        demonstrateAdvancedUseCases();
        demonstratePracticalUseCase();
    }

    private static void demonstrateBasicUsage() {
        System.out.println("=== Базовое использование итератора ===");

        // Создаём список
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Получаем итератор
        Iterator<String> it = list.iterator();

        System.out.println("Обход списка:");
        while (it.hasNext()) {
            String element = it.next();
            System.out.println("  " + element);
        }

        // Итератор "исчерпан" — hasNext() теперь false
        System.out.println("Итератор исчерпан? " + !it.hasNext());

        // Чтобы снова пройти по списку — нужно получить новый итератор
        it = list.iterator();
        System.out.println("Новый итератор: " + it.hasNext());
    }

    private static void demonstrateWithDifferentCollections() {
        System.out.println("\n=== Итератор с разными коллекциями ===");

        // Список (List)
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        System.out.println("List:");
        Iterator<String> listIt = list.iterator();
        while (listIt.hasNext()) {
            System.out.print(listIt.next() + " ");
        }
        System.out.println();

        // Множество (Set)
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");

        System.out.println("Set:");
        Iterator<String> setIt = set.iterator();
        while (setIt.hasNext()) {
            System.out.print(setIt.next() + " ");
        }
        System.out.println();

        // Карта (Map) — итерируем ключи
        Map<String, Integer> map = new HashMap<>();
        map.put("Age", 30);
        map.put("Score", 85);

        System.out.println("Map (ключи):");
        Iterator<String> keyIt = map.keySet().iterator();
        while (keyIt.hasNext()) {
            String key = keyIt.next();
            System.out.print(key + " ");
        }
        System.out.println();

        // Или итерируем пары (entrySet)
        System.out.println("Map (пары ключ-значение):");
        Iterator<Map.Entry<String, Integer>> entryIt = map.entrySet().iterator();
        while (entryIt.hasNext()) {
            Map.Entry<String, Integer> entry = entryIt.next();
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();
    }

    private static void demonstrateSafeRemoval() {
        System.out.println("\n=== Безопасное удаление элементов ===");

        // Создаём список
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Date");

        System.out.println("Исходный список: " + list);

        // Удаляем элементы, удовлетворяющие условию
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String fruit = it.next();
            if (fruit.startsWith("B")) { // удаляем все, начинающиеся на B
                it.remove(); // безопасное удаление
            }
        }

        System.out.println("После удаления: " + list);

        // Аналогично для множества
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Programming");
        set.add("OOP");
        set.add("Python");

        System.out.println("Исходное множество: " + set);

        Iterator<String> setIt = set.iterator();
        while (setIt.hasNext()) {
            String tag = setIt.next();
            if (tag.length() > 4) { // удаляем теги длиннее 4 символов
                setIt.remove();
            }
        }

        System.out.println("После удаления: " + set);
    }

    private static void demonstrateCommonMistakes() {
        System.out.println("\n=== Распространённые ошибки ===");

        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");

        // Ошибка 1: Вызов next() после hasNext() == false
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            it.next();
        }
        try {
            String element = it.next(); // NoSuchElementException!
            System.out.println(element);
        } catch (NoSuchElementException e) {
            System.out.println("⚠️ Вызов next() после окончания коллекции вызвал NoSuchElementException!");
        }

        // Ошибка 2: Вызов remove() без предварительного next()
        it = list.iterator();
        try {
            it.remove(); // IllegalStateException!
        } catch (IllegalStateException e) {
            System.out.println("⚠️ Вызов remove() без предварительного next() вызвал IllegalStateException!");
        }

        // Ошибка 3: Параллельное изменение коллекции
        it = list.iterator();
        list.add("Cherry"); // изменяем коллекцию во время итерации
        try {
            while (it.hasNext()) {
                it.next();
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("⚠️ Изменение коллекции во время итерации вызвало ConcurrentModificationException!");
        }
    }

    private static void demonstrateForEachLoopVsIterator() {
        System.out.println("\n=== Цикл for-each vs Итератор ===");

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println("Цикл for-each:");
        for (String s : list) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("Итератор:");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            System.out.print(s + " ");
        }
        System.out.println();

        // Ключевое отличие: в for-each нельзя удалять элементы
        System.out.println("Удаление через for-each:");
        try {
            for (String s : list) {
                if ("B".equals(s)) {
                    list.remove(s); // ConcurrentModificationException!
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("⚠️ Удаление элемента в цикле for-each вызвало ConcurrentModificationException!");
        }

        // А через итератор — можно
        System.out.println("Удаление через итератор:");
        it = list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if ("B".equals(s)) {
                it.remove();
            }
        }
        System.out.println("После удаления: " + list);
    }

    private static void demonstrateAdvancedUseCases() {
        System.out.println("\n=== Продвинутые примеры ===");

        // Фильтрация и преобразование
        List<String> input = List.of("apple", "banana", "cherry", "date");
        List<String> output = new ArrayList<>();

        Iterator<String> stringIt = input.iterator(); // Используем другое имя
        while (stringIt.hasNext()) {
            String fruit = stringIt.next();
            if (fruit.length() > 5) { // фильтрация
                output.add(fruit.toUpperCase()); // преобразование
            }
        }

        System.out.println("Фильтрация и преобразование: " + output);

        // Поиск первого элемента, удовлетворяющего условию
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        Integer firstEven = null;
        Iterator<Integer> intIt = numbers.iterator(); // Используем другое имя
        while (intIt.hasNext()) {
            Integer num = intIt.next();
            if (num % 2 == 0) {
                firstEven = num;
                break; // выходим при первом найденном
            }
        }

        System.out.println("Первое чётное число: " + firstEven);
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Управление задачами ===");

        // Создаём список задач
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Design", "High"));
        tasks.add(new Task("Code", "Medium"));
        tasks.add(new Task("Test", "Low"));

        System.out.println("Все задачи:");
        for (Task task : tasks) {
            System.out.println("  " + task);
        }

        // Удаляем завершённые задачи
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

        // Отмечаем первую задачу как завершённую
        it = tasks.iterator();
        if (it.hasNext()) {
            Task firstTask = it.next();
            firstTask.setCompleted(true);
        }

        System.out.println("После отметки первой задачи как завершённой:");
        for (Task task : tasks) {
            System.out.println("  " + task);
        }
    }

    // Вспомогательный класс для задачи
    static class Task {
        private String name;
        private String priority;
        private boolean completed;

        public Task(String name, String priority) {
            this.name = name;
            this.priority = priority;
            this.completed = false;
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