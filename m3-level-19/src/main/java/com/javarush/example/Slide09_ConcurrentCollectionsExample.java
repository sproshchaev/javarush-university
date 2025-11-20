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

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== CONCURRENT COLLECTIONS: COPYONWRITEARRAYLIST ===\n");

        demonstrateCopyOnWriteArrayList();
        demonstratePerformance();
        demonstrateMethods();
        demonstrateAdditionalMethods();

        System.out.println("=== ВЫВОД ===");
        System.out.println("✓ Идеально для listeners, конфигураций, кэшей");
        System.out.println("✓ Чтение - быстрое и безопасное");
        System.out.println("✓ Запись - дорогая (копирование массива)");
        System.out.println("✓ Итераторы работают со 'снимком' на момент создания");
        System.out.println("✓ Специальные методы для атомарных операций");
    }
}
