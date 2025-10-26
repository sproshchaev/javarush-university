package com.javarush.example;

import java.util.*;

/**
 * Пример использования коллекции Set.
 * Демонстрирует уникальность, отсутствие порядка и основные операции.
 */
public class Slide05_SetExample {

    public static void main(String[] args) {
        demonstrateBasicOperations();
        demonstrateUniqueness();
        demonstrateNoOrder();
        demonstrateDifferentImplementations();
        demonstrateUseCases();
        demonstrateIteration();
        demonstratePerformanceComparison();
    }

    private static void demonstrateBasicOperations() {
        System.out.println("=== Основные операции над Set ===");

        Set<String> set = new HashSet<>();

        // add(T element) — добавляет элемент, возвращает true, если элемент был добавлен
        boolean added1 = set.add("Apple");
        boolean added2 = set.add("Banana");
        boolean added3 = set.add("Apple"); // дубликат — false

        System.out.println("Добавлен 'Apple'? " + added1); // true
        System.out.println("Добавлен 'Banana'? " + added2); // true
        System.out.println("Добавлен 'Apple' снова? " + added3); // false

        System.out.println("Set после добавления: " + set);

        // remove(Object element) — удаляет элемент, возвращает true, если элемент был удалён
        boolean removed = set.remove("Banana");
        System.out.println("Удалён 'Banana'? " + removed); // true
        System.out.println("Set после удаления: " + set);

        // contains(Object element) — проверяет наличие элемента
        boolean hasApple = set.contains("Apple");
        boolean hasBanana = set.contains("Banana");
        System.out.println("Содержит 'Apple'? " + hasApple); // true
        System.out.println("Содержит 'Banana'? " + hasBanana); // false

        // size() — количество элементов
        System.out.println("Размер множества: " + set.size());

        // isEmpty() — проверка на пустоту
        System.out.println("Множество пусто? " + set.isEmpty());
    }

    private static void demonstrateUniqueness() {
        System.out.println("\n=== Уникальность элементов ===");

        Set<String> set = new HashSet<>();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add("One"); // дубликат — игнорируется
        set.add("Two"); // дубликат — игнорируется

        System.out.println("Set: " + set);
        System.out.println("Размер: " + set.size()); // 3, а не 5!

        // Попытка добавить null
        set.add(null);
        set.add(null); // второй null — тоже игнорируется
        System.out.println("Set с null: " + set);
        System.out.println("Размер после добавления null: " + set.size()); // 4

        // Проверка на дубликаты
        String[] duplicates = {"A", "B", "A", "C", "B", "A"};
        Set<String> uniqueSet = new HashSet<>();
        for (String s : duplicates) {
            uniqueSet.add(s);
        }
        System.out.println("Исходный массив: " + Arrays.toString(duplicates));
        System.out.println("Уникальные элементы: " + uniqueSet);
    }

    private static void demonstrateNoOrder() {
        System.out.println("\n=== Отсутствие порядка ===");

        Set<String> set = new HashSet<>();
        set.add("Zebra");
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");

        System.out.println("HashSet: " + set); // Порядок произвольный!
        System.out.println("Элементы не имеют индексов — нельзя получить по индексу!");

        // Попытка получить по индексу — ошибка!
        try {
            // set.get(0); // ОШИБКА КОМПИЛЯЦИИ — нет метода get(int)
            System.out.println("Эта строка не выполнится.");
        } catch (Exception e) {
            System.out.println("❌ Нельзя получить элемент по индексу в Set.");
        }

        // Для упорядоченного вывода используем TreeSet
        Set<String> orderedSet = new TreeSet<>(set);
        System.out.println("TreeSet (упорядоченный): " + orderedSet);
    }

    private static void demonstrateDifferentImplementations() {
        System.out.println("\n=== Разные реализации Set ===");

        // HashSet — быстрый, без порядка
        Set<String> hashSet = new HashSet<>();
        hashSet.add("C");
        hashSet.add("A");
        hashSet.add("B");
        System.out.println("HashSet: " + hashSet); // Порядок произвольный

        // TreeSet — упорядоченный (по естественному порядку)
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("C");
        treeSet.add("A");
        treeSet.add("B");
        System.out.println("TreeSet: " + treeSet); // A, B, C

        // LinkedHashSet — сохраняет порядок добавления
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        linkedHashSet.add("Third");
        System.out.println("LinkedHashSet: " + linkedHashSet); // First, Second, Third
    }

    private static void demonstrateUseCases() {
        System.out.println("\n=== Практические примеры ===");

        // Удаление дубликатов из списка
        List<String> listWithDuplicates = List.of("Apple", "Banana", "Apple", "Cherry", "Banana");
        Set<String> uniqueSet = new HashSet<>(listWithDuplicates);
        System.out.println("Список с дубликатами: " + listWithDuplicates);
        System.out.println("Уникальные элементы: " + uniqueSet);

        // Проверка на наличие элемента
        Set<String> tags = new HashSet<>();
        tags.add("Java");
        tags.add("Programming");
        tags.add("OOP");

        String tagToCheck = "Java";
        if (tags.contains(tagToCheck)) {
            System.out.println("Тег '" + tagToCheck + "' найден!");
        } else {
            System.out.println("Тег '" + tagToCheck + "' не найден.");
        }

        // Хранение уникальных ID
        Set<Integer> userIds = new HashSet<>();
        userIds.add(1001);
        userIds.add(1002);
        userIds.add(1001); // дубликат — игнорируется
        System.out.println("Уникальные ID пользователей: " + userIds);
    }

    private static void demonstrateIteration() {
        System.out.println("\n=== Итерация по Set ===");

        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");

        // Используем for-each
        System.out.print("Итерация через for-each: ");
        for (String fruit : set) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        // Используем итератор
        System.out.print("Итерация через Iterator: ");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // Используем stream API
        System.out.print("Итерация через stream: ");
        set.stream().forEach(fruit -> System.out.print(fruit + " "));
        System.out.println();
    }

    private static void demonstratePerformanceComparison() {
        System.out.println("\n=== Сравнение производительности ===");

        int size = 10_000;
        Set<String> hashSet = new HashSet<>();
        Set<String> treeSet = new TreeSet<>();

        long start, end;

        // Добавление элементов
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            hashSet.add("Item" + i);
        }
        end = System.currentTimeMillis();
        System.out.println("Добавление " + size + " элементов в HashSet: " + (end - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            treeSet.add("Item" + i);
        }
        end = System.currentTimeMillis();
        System.out.println("Добавление " + size + " элементов в TreeSet: " + (end - start) + " мс");

        // Проверка наличия
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            hashSet.contains("Item" + i);
        }
        end = System.currentTimeMillis();
        System.out.println("Проверка наличия " + size + " элементов в HashSet: " + (end - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            treeSet.contains("Item" + i);
        }
        end = System.currentTimeMillis();
        System.out.println("Проверка наличия " + size + " элементов в TreeSet: " + (end - start) + " мс");

        System.out.println("✅ HashSet обычно быстрее для добавления и поиска.");
    }
}