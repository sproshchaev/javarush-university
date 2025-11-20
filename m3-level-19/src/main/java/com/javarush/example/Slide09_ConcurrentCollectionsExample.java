package com.javarush.example;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Slide09_ConcurrentCollectionsExample {

    // Демонстрация CopyOnWriteArrayList
    public static void demonstrateCopyOnWriteArrayList() {
        System.out.println("1. COPYONWRITEARRAYLIST:");

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");

        System.out.println("Исходный список: " + list);

        // Итератор видит "снимок" на момент создания
        Iterator<String> iterator = list.iterator();
        System.out.print("Итерация: ");
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.print(item + " ");
            // Добавляем элемент во время итерации - БЕЗ ПРОБЛЕМ!
            if ("Python".equals(item)) {
                list.add("JavaScript");
            }
        }

        System.out.println("\nСписок после итерации: " + list);
        System.out.println("Итератор не увидел новые элементы - он работал со снимком!\n");
    }

    // Демонстрация производительности: чтение vs запись
    public static void demonstratePerformance() throws InterruptedException {
        System.out.println("2. ПРОИЗВОДИТЕЛЬНОСТЬ (ЧТЕНИЕ vs ЗАПИСЬ):");

        CopyOnWriteArrayList<Integer> cowList = new CopyOnWriteArrayList<>();
        AtomicInteger readCount = new AtomicInteger(0);
        AtomicInteger writeCount = new AtomicInteger(0);

        // Поток-читатель (быстрый)
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                for (Integer num : cowList) {
                    // Просто читаем
                    int value = num;
                }
                readCount.incrementAndGet();
            }
        });

        // Поток-писатель (медленный из-за копирования)
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                cowList.add(i);
                writeCount.incrementAndGet();
                try { Thread.sleep(1); } catch (InterruptedException e) {}
            }
        });

        // Добавляем начальные данные
        for (int i = 0; i < 10; i++) {
            cowList.add(i);
        }

        reader.start();
        writer.start();

        reader.join();
        writer.join();

        System.out.println("Прочитано: " + readCount.get() + " раз");
        System.out.println("Записано: " + writeCount.get() + " раз");
        System.out.println("Размер списка: " + cowList.size());
        System.out.println("Вывод: много чтения + мало записи = идеально для CopyOnWrite!\n");
    }

    // Демонстрация методов CopyOnWriteArrayList
    public static void demonstrateMethods() {
        System.out.println("3. СПЕЦИАЛЬНЫЕ МЕТОДЫ COPYONWRITEARRAYLIST:");

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(
                new String[]{"Apple", "Banana", "Apple", "Orange"}
        );

        System.out.println("Исходный список: " + list);

        // addIfAbsent - добавляет только если элемента нет
        boolean added1 = list.addIfAbsent("Banana"); // false - уже есть
        boolean added2 = list.addIfAbsent("Grape");  // true - добавил

        System.out.println("addIfAbsent('Banana'): " + added1);
        System.out.println("addIfAbsent('Grape'): " + added2);
        System.out.println("Список после addIfAbsent: " + list);

        // addAllAbsent - добавляет все отсутствующие элементы
        List<String> toAdd = Arrays.asList("Apple", "Mango", "Lemon");
        int addedCount = list.addAllAbsent(toAdd);
        System.out.println("addAllAbsent([Apple, Mango, Lemon]): добавлено " + addedCount);
        System.out.println("Финальный список: " + list);
    }

    // Дополнительные методы CopyOnWriteArrayList
    public static void demonstrateAdditionalMethods() {
        System.out.println("4. ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ COPYONWRITEARRAYLIST:");

        // Конструктор из массива
        String[] languages = {"Java", "Python", "C++", "Java", "JavaScript"};
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(languages);
        System.out.println("Создан из массива: " + list);

        // Поиск с начальным индексом
        System.out.println("\n--- ПОИСК С ИНДЕКСОМ ---");
        System.out.println("indexOf('Java', 0): " + list.indexOf("Java", 0)); // 0
        System.out.println("indexOf('Java', 1): " + list.indexOf("Java", 1)); // 3
        System.out.println("lastIndexOf('Java', 2): " + list.lastIndexOf("Java", 2)); // 0

        // addIfAbsent - атомарное условное добавление
        System.out.println("\n--- ADDIFABSENT ---");
        System.out.println("addIfAbsent('Python'): " + list.addIfAbsent("Python")); // false
        System.out.println("addIfAbsent('Go'): " + list.addIfAbsent("Go")); // true
        System.out.println("Список: " + list);

        // addAllAbsent - массовое условное добавление
        System.out.println("\n--- ADDALLABSENT ---");
        java.util.List<String> newLanguages = Arrays.asList("Ruby", "Python", "Kotlin", "Java");
        int addedCount = list.addAllAbsent(newLanguages);
        System.out.println("addAllAbsent([Ruby, Python, Kotlin, Java]): добавлено " + addedCount);
        System.out.println("Финальный список: " + list);

        // Использование в многопоточной среде
        System.out.println("\n--- МНОГОПОТОЧНОСТЬ ---");
        CopyOnWriteArrayList<Integer> sharedList = new CopyOnWriteArrayList<>();

        Thread writer1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                sharedList.addIfAbsent(i); // Безопасное добавление
            }
        });

        Thread writer2 = new Thread(() -> {
            for (int i = 3; i < 8; i++) {
                sharedList.addIfAbsent(i); // Безопасное добавление  
            }
        });

        writer1.start();
        writer2.start();

        try {
            writer1.join();
            writer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Результат многопоточной работы: " + sharedList);
        System.out.println("Никаких дубликатов благодаря addIfAbsent!");
    }

    // Дополнительный метод в классе Slide09_ConcurrentCollectionsExample
    public static void demonstrateConcurrentMap() {
        System.out.println("5. CONCURRENTMAP И CONCURRENTHASHMAP:");

        ConcurrentMap<String, Integer> scores = new ConcurrentHashMap<>();
        scores.put("Alice", 100);
        scores.put("Bob", 200);
        scores.put("Charlie", 150);

        System.out.println("Исходные баллы: " + scores);

        // putIfAbsent - атомарное условное добавление
        System.out.println("\n--- PUTIFABSENT ---");
        Integer result1 = scores.putIfAbsent("Alice", 999); // Уже есть
        Integer result2 = scores.putIfAbsent("David", 300); // Новый
        System.out.println("putIfAbsent('Alice', 999): " + result1);
        System.out.println("putIfAbsent('David', 300): " + result2);
        System.out.println("После putIfAbsent: " + scores);

        // remove с проверкой значения
        System.out.println("\n--- REMOVE С ПРОВЕРКОЙ ---");
        boolean removed1 = scores.remove("Bob", 999); // Неверное значение
        boolean removed2 = scores.remove("Bob", 200); // Верное значение
        System.out.println("remove('Bob', 999): " + removed1);
        System.out.println("remove('Bob', 200): " + removed2);
        System.out.println("После remove: " + scores);

        // replace методы
        System.out.println("\n--- REPLACE МЕТОДЫ ---");
        boolean replaced1 = scores.replace("Charlie", 150, 250); // Условная замена
        Integer replaced2 = scores.replace("Alice", 500); // Безусловная замена
        System.out.println("replace('Charlie', 150, 250): " + replaced1);
        System.out.println("replace('Alice', 500): вернул " + replaced2);
        System.out.println("После replace: " + scores);

        // Многопоточный сценарий
        System.out.println("\n--- МНОГОПОТОЧНЫЙ СЦЕНАРИЙ ---");
        ConcurrentMap<String, AtomicInteger> threadSafeCounter = new ConcurrentHashMap<>();

        // Несколько потоков обновляют счетчики
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                threadSafeCounter.putIfAbsent("counter", new AtomicInteger(0));
                threadSafeCounter.get("counter").incrementAndGet();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                threadSafeCounter.putIfAbsent("counter", new AtomicInteger(0));
                threadSafeCounter.get("counter").incrementAndGet();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Многопоточный счетчик: " + threadSafeCounter.get("counter"));
        System.out.println("Всегда 200 благодаря атомарным операциям!");
    }

    // Дополнительный метод в классе Slide09_ConcurrentCollectionsExample
    public static void demonstrateConcurrentHashMaps() {
        System.out.println("6. CONCURRENTHASHMAP И РОДСТВЕННИКИ:");

        // ConcurrentHashMap - основная реализация
        ConcurrentHashMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("Java", "Spring");
        concurrentMap.put("Python", "Django");
        concurrentMap.put("JavaScript", "React");

        System.out.println("ConcurrentHashMap: " + concurrentMap);

        // Безопасная итерация с модификацией
        System.out.println("\n--- БЕЗОПАСНАЯ ИТЕРАЦИЯ ---");
        for (String key : concurrentMap.keySet()) {
            System.out.print(key + " ");
            if ("Python".equals(key)) {
                concurrentMap.put("Go", "Gin"); // Без ConcurrentModificationException!
            }
        }
        System.out.println("\nПосле итерации: " + concurrentMap);

        // ConcurrentSkipListMap - отсортированная версия
        System.out.println("\n--- CONCURRENTSKIPLISTMAP ---");
        ConcurrentSkipListMap<Integer, String> sortedMap = new ConcurrentSkipListMap<>();
        sortedMap.put(30, "Thirty");
        sortedMap.put(10, "Ten");
        sortedMap.put(20, "Twenty");

        System.out.println("Автосортировка: " + sortedMap);
        System.out.println("firstKey(): " + sortedMap.firstKey());
        System.out.println("lastKey(): " + sortedMap.lastKey());
        System.out.println("headMap(25): " + sortedMap.headMap(25));
        System.out.println("tailMap(15): " + sortedMap.tailMap(15));

        // ConcurrentSkipListSet
        System.out.println("\n--- CONCURRENTSKIPLISTSET ---");
        ConcurrentSkipListSet<String> sortedSet = new ConcurrentSkipListSet<>();
        sortedSet.add("Orange");
        sortedSet.add("Apple");
        sortedSet.add("Banana");

        System.out.println("Отсортированный сет: " + sortedSet);
        System.out.println("first(): " + sortedSet.first());
        System.out.println("last(): " + sortedSet.last());

        // Производительность в многопоточной среде
        System.out.println("\n--- МНОГОПОТОЧНАЯ ПРОИЗВОДИТЕЛЬНОСТЬ ---");
        ConcurrentHashMap<String, AtomicInteger> counter = new ConcurrentHashMap<>();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    counter.computeIfAbsent("key", k -> new AtomicInteger()).incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try { thread.join(); } catch (InterruptedException e) {}
        }

        System.out.println("Результат многопоточного счетчика: " + counter.get("key"));
        System.out.println("Всегда 1000 благодаря сегментированным блокировкам!");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== CONCURRENT COLLECTIONS: COPYONWRITEARRAYLIST ===\n");

        demonstrateCopyOnWriteArrayList();
        demonstratePerformance();
        demonstrateMethods();
        demonstrateAdditionalMethods();
        demonstrateConcurrentMap();
        demonstrateConcurrentHashMaps();

        System.out.println("=== ВЫВОД ===");
        System.out.println("✓ Идеально для listeners, конфигураций, кэшей");
        System.out.println("✓ Чтение - быстрое и безопасное");
        System.out.println("✓ Запись - дорогая (копирование массива)");
        System.out.println("✓ Итераторы работают со 'снимком' на момент создания");
        System.out.println("✓ Специальные методы для атомарных операций");
    }
}
