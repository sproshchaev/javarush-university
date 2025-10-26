package com.javarush.example;

import java.util.*;

/**
 * Пример иерархии коллекций в Java.
 * Демонстрирует использование интерфейсов и их реализаций.
 */
public class Slide04_CollectionsHierarchyExample {

    public static void main(String[] args) {
        demonstrateCollectionInterface();
        demonstrateListHierarchy();
        demonstrateSetHierarchy();
        demonstrateQueueHierarchy();
        demonstrateMapHierarchy();
        demonstratePolymorphismAndFlexibility();
    }

    private static void demonstrateCollectionInterface() {
        System.out.println("=== Интерфейс Collection ===");

        // Collection — базовый интерфейс
        Collection<String> collection = new ArrayList<>();

        collection.add("Apple");
        collection.add("Banana");
        collection.add("Cherry");

        System.out.println("Collection: " + collection);
        System.out.println("Размер: " + collection.size());
        System.out.println("Содержит 'Apple'? " + collection.contains("Apple"));

        // Методы Collection
        collection.remove("Banana");
        System.out.println("После удаления: " + collection);

        // Очистка
        collection.clear();
        System.out.println("После очистки: " + collection);
    }

    private static void demonstrateListHierarchy() {
        System.out.println("\n=== Иерархия List ===");

        // List — упорядоченная коллекция
        List<String> list = new ArrayList<>();

        // Можно использовать методы List
        list.add("First");
        list.add(0, "Zero"); // добавление по индексу
        list.set(1, "One");  // изменение по индексу

        System.out.println("List: " + list);

        // LinkedList — тоже реализует List
        LinkedList<String> linkedList = new LinkedList<>(); // Изменён тип
        linkedList.add("A");
        linkedList.add("B");
        linkedList.addFirst("Start"); // специфичный для LinkedList

        System.out.println("LinkedList: " + linkedList);

        // Vector — устаревший, потокобезопасный
        List<String> vector = new Vector<>();
        vector.add("Vector Item");
        System.out.println("Vector: " + vector);
    }

    private static void demonstrateSetHierarchy() {
        System.out.println("\n=== Иерархия Set ===");

        // Set — множество уникальных элементов
        Set<String> set = new HashSet<>();

        set.add("Apple");
        set.add("Banana");
        set.add("Apple"); // дубликат — игнорируется

        System.out.println("Set: " + set);

        // TreeSet — упорядоченное множество
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Cherry");
        treeSet.add("Apple");
        treeSet.add("Banana");

        System.out.println("TreeSet: " + treeSet); // отсортировано

        // LinkedHashSet — сохраняет порядок добавления
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        linkedHashSet.add("Third");

        System.out.println("LinkedHashSet: " + linkedHashSet);
    }

    private static void demonstrateQueueHierarchy() {
        System.out.println("\n=== Иерархия Queue ===");

        // Queue — очередь
        Queue<String> queue = new LinkedList<>();

        queue.offer("Task 1");
        queue.offer("Task 2");
        queue.offer("Task 3");

        System.out.println("Queue: " + queue);
        System.out.println("Первый элемент: " + queue.peek()); // Task 1
        System.out.println("Извлечение: " + queue.poll()); // Task 1

        // PriorityQueue — очередь с приоритетом
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);

        System.out.println("PriorityQueue: " + priorityQueue);
        System.out.print("Извлечение по приоритету: ");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " "); // 1 2 3
        }
        System.out.println();

        // Deque — двусторонняя очередь
        Deque<String> deque = new ArrayDeque<>();
        deque.offerFirst("Front");
        deque.offerLast("Back");
        System.out.println("Deque: " + deque);
    }

    private static void demonstrateMapHierarchy() {
        System.out.println("\n=== Иерархия Map ===");

        // Map — отображение ключ-значение
        Map<String, Integer> map = new HashMap<>();

        map.put("Age", 30);
        map.put("Score", 85);
        map.put("Height", 175);

        System.out.println("Map: " + map);
        System.out.println("Значение по ключу 'Age': " + map.get("Age"));
        System.out.println("Ключи: " + map.keySet());
        System.out.println("Значения: " + map.values());

        // TreeMap — упорядоченное отображение
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Zebra", 100);
        treeMap.put("Apple", 50);
        treeMap.put("Banana", 75);

        System.out.println("TreeMap: " + treeMap); // отсортировано по ключу

        // LinkedHashMap — сохраняет порядок добавления
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("First", 1);
        linkedHashMap.put("Second", 2);
        linkedHashMap.put("Third", 3);

        System.out.println("LinkedHashMap: " + linkedHashMap);
    }

    private static void demonstratePolymorphismAndFlexibility() {
        System.out.println("\n=== Полиморфизм и гибкость ===");

        // Работаем через интерфейс — можно легко менять реализацию
        List<String> list = new ArrayList<>(); // можно заменить на LinkedList
        list.add("Item 1");
        list.add("Item 2");
        System.out.println("List (ArrayList): " + list);

        // Замена реализации без изменения кода
        list = new LinkedList<>(); // теперь LinkedList
        list.add("Item A");
        list.add("Item B");
        System.out.println("List (LinkedList): " + list);

        // Аналогично для Set
        Set<String> set = new HashSet<>(); // можно заменить на TreeSet
        set.add("X");
        set.add("Y");
        System.out.println("Set (HashSet): " + set);

        set = new TreeSet<>(); // теперь TreeSet
        set.add("C");
        set.add("A");
        System.out.println("Set (TreeSet): " + set);

        // Для Map
        Map<String, String> map = new HashMap<>(); // можно заменить на TreeMap
        map.put("Key1", "Value1");
        System.out.println("Map (HashMap): " + map);

        map = new TreeMap<>(); // теперь TreeMap
        map.put("Z", "Last");
        map.put("A", "First");
        System.out.println("Map (TreeMap): " + map);

        System.out.println("✅ Использование интерфейсов делает код более гибким и расширяемым.");
    }
}