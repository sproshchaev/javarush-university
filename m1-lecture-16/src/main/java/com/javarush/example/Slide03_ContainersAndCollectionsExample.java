package com.javarush.example;

import java.util.*;

/**
 * Примеры основных типов коллекций в Java.
 * Демонстрирует List, Set, Queue и Map.
 */
public class Slide03_ContainersAndCollectionsExample {

    public static void main(String[] args) {
        demonstrateList();
        demonstrateSet();
        demonstrateQueue();
        demonstrateMap();
        demonstrateChoosingCollection();
    }

    private static void demonstrateList() {
        System.out.println("=== List — упорядоченная последовательность ===");

        // ArrayList — динамический массив
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        arrayList.add("Apple"); // дубликат

        System.out.println("ArrayList: " + arrayList);
        System.out.println("Размер: " + arrayList.size());
        System.out.println("Первый элемент: " + arrayList.get(0));
        System.out.println("Индекс 'Banana': " + arrayList.indexOf("Banana"));

        // LinkedList — связный список
        List<String> linkedList = new LinkedList<>();
        linkedList.add("One");
        linkedList.add("Two");
        linkedList.add("Three");

        System.out.println("LinkedList: " + linkedList);
        System.out.println("Удаление первого элемента: " + linkedList.remove(0));
        System.out.println("После удаления: " + linkedList);
    }

    private static void demonstrateSet() {
        System.out.println("\n=== Set — множество уникальных элементов ===");

        // HashSet — неупорядоченное множество
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Cherry");
        hashSet.add("Apple"); // дубликат — игнорируется

        System.out.println("HashSet: " + hashSet);
        System.out.println("Размер: " + hashSet.size()); // 3, а не 4!
        System.out.println("Содержит 'Banana'? " + hashSet.contains("Banana"));

        // TreeSet — упорядоченное множество (по естественному порядку)
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Cherry");
        treeSet.add("Apple");
        treeSet.add("Banana");

        System.out.println("TreeSet: " + treeSet); // отсортировано: Apple, Banana, Cherry
        System.out.println("Первый элемент: " + treeSet.iterator().next());

        // LinkedHashSet — сохраняет порядок добавления
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        linkedHashSet.add("Third");

        System.out.println("LinkedHashSet: " + linkedHashSet);
    }

    private static void demonstrateQueue() {
        System.out.println("\n=== Queue — очередь (FIFO) ===");

        // PriorityQueue — очередь с приоритетом
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);

        System.out.println("PriorityQueue: " + priorityQueue); // [1, 3, 2] — внутреннее представление
        System.out.print("Извлечение элементов: ");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " "); // 1 2 3
        }
        System.out.println();

        // ArrayDeque — двусторонняя очередь (можно использовать как стек или очередь)
        Deque<String> deque = new ArrayDeque<>();
        deque.offer("First");   // в конец
        deque.offer("Second");  // в конец
        deque.offerFirst("Zero"); // в начало

        System.out.println("ArrayDeque: " + deque);
        System.out.println("Извлечение с начала: " + deque.poll()); // Zero
        System.out.println("Извлечение с конца: " + deque.pollLast()); // Second
        System.out.println("Осталось: " + deque);
    }

    private static void demonstrateMap() {
        System.out.println("\n=== Map — отображение ключ-значение ===");

        // HashMap — неупорядоченное отображение
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Age", 30);
        hashMap.put("Score", 85);
        hashMap.put("Height", 175);

        System.out.println("HashMap: " + hashMap);
        System.out.println("Значение по ключу 'Age': " + hashMap.get("Age"));
        System.out.println("Содержит ключ 'Score'? " + hashMap.containsKey("Score"));

        // TreeMap — упорядоченное отображение (по ключу)
        TreeMap<String, Integer> treeMap = new TreeMap<>(); // Изменён тип
        treeMap.put("Zebra", 100);
        treeMap.put("Apple", 50);
        treeMap.put("Banana", 75);

        System.out.println("TreeMap: " + treeMap); // отсортировано по ключу: Apple, Banana, Zebra
        System.out.println("Первый ключ: " + treeMap.firstKey()); // Теперь работает

        // LinkedHashMap — сохраняет порядок добавления
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("First", 1);
        linkedHashMap.put("Second", 2);
        linkedHashMap.put("Third", 3);

        System.out.println("LinkedHashMap: " + linkedHashMap);
    }

    private static void demonstrateChoosingCollection() {
        System.out.println("\n=== Как выбрать подходящую коллекцию? ===");

        // Нужны дубликаты и порядок? -> List
        List<String> shoppingList = new ArrayList<>();
        shoppingList.add("Milk");
        shoppingList.add("Bread");
        shoppingList.add("Milk"); // дубликат
        System.out.println("Список покупок (List): " + shoppingList);

        // Нужны уникальные элементы? -> Set
        Set<String> uniqueTags = new HashSet<>();
        uniqueTags.add("Java");
        uniqueTags.add("Programming");
        uniqueTags.add("Java"); // дубликат — игнорируется
        System.out.println("Уникальные теги (Set): " + uniqueTags);

        // Нужен доступ по ключу? -> Map
        Map<String, String> userProfiles = new HashMap<>();
        userProfiles.put("Alice", "Developer");
        userProfiles.put("Bob", "Designer");
        System.out.println("Профили пользователей (Map): " + userProfiles);

        // Нужна очередь? -> Queue
        Queue<String> taskQueue = new LinkedList<>();
        taskQueue.offer("Task 1");
        taskQueue.offer("Task 2");
        System.out.println("Очередь задач (Queue): " + taskQueue);
    }
}

