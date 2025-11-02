package com.javarush.example;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Slide09_CollectionAdvice {

    public static void main(String[] args) {
        System.out.println("=== Советы по использованию коллекций ===\n");

        // 1. Используем ArrayList — когда нужен доступ по индексу и изменяемый размер
        System.out.println("✅ ArrayList — идеален для списка с доступом по индексу:");
        List<String> shoppingList = new ArrayList<>();
        shoppingList.add("Молоко");
        shoppingList.add("Хлеб");
        shoppingList.add("Яйца");

        System.out.println("Первый элемент: " + shoppingList.get(0));
        System.out.println("Весь список: " + shoppingList);

        // 2. Используем HashSet — когда нужны только уникальные элементы
        System.out.println("\n✅ HashSet — идеален для хранения уникальных значений:");
        Set<String> visitedCities = new HashSet<>();
        visitedCities.add("Москва");
        visitedCities.add("Париж");
        visitedCities.add("Москва"); // дубликат не добавится

        System.out.println("Города без дубликатов: " + visitedCities);
        System.out.println("Количество уникальных городов: " + visitedCities.size());

        // 3. Используем HashMap — когда нужно связать ключ с значением
        System.out.println("\n✅ HashMap — идеален для хранения соответствий:");
        Map<String, Double> productPrices = new HashMap<>();
        productPrices.put("Яблоко", 55.99);
        productPrices.put("Банан", 32.50);

        System.out.println("Цена яблока: " + productPrices.get("Яблоко"));
        System.out.println("Все продукты: " + productPrices);

        // 4. Сравнение производительности — демонстрация O(1) vs O(n)
        System.out.println("\n⏱️ Демонстрация скорости поиска:");

        List<Integer> arrayList = new ArrayList<>();
        Set<Integer> hashSet = new HashSet<>();

        int size = 100_000;
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            hashSet.add(i);
        }

        // Поиск в ArrayList — O(n)
        long start = System.nanoTime();
        boolean foundInList = arrayList.contains(size - 1);
        long timeList = System.nanoTime() - start;

        // Поиск в HashSet — O(1)
        start = System.nanoTime();
        boolean foundInSet = hashSet.contains(size - 1);
        long timeSet = System.nanoTime() - start;

        System.out.println("Поиск в ArrayList (" + size + " элементов): " + TimeUnit.NANOSECONDS.toMicros(timeList) + " мкс");
        System.out.println("Поиск в HashSet (" + size + " элементов): " + TimeUnit.NANOSECONDS.toMicros(timeSet) + " мкс");

        // 5. Что выбрать? Примеры из жизни:
        System.out.println("\n💡 Советы по выбору коллекции:");
        System.out.println("- Храню список покупок? → ArrayList");
        System.out.println("- Собираю уникальные email-адреса? → HashSet");
        System.out.println("- Сохраняю ID пользователя → имя? → HashMap");
        System.out.println("- Нужно вставлять в середину часто? → LinkedList");
        System.out.println("- Нужна сортировка по ключу? → TreeMap");

        // 6. Таблица сложности — краткий вывод
        System.out.println("\n📊 Краткая сводка сложности операций:");
        System.out.println("ArrayList: get(O(1)), add/end(O(1)), add/mid(O(n))");
        System.out.println("HashSet:   add(O(1)), contains(O(1)), remove(O(1))");
        System.out.println("HashMap:   put(O(1)), get(O(1)), remove(O(1))");
        System.out.println("TreeMap:   put(O(log n)), get(O(log n)) — но всегда отсортировано!");

        System.out.println("\n✅ Вывод: Выбирайте коллекцию под задачу, а не по привычке!");
    }
}