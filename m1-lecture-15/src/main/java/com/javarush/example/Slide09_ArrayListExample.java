package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример использования ArrayList — динамического списка в Java.
 * Демонстрирует создание, добавление, удаление, поиск и другие операции.
 */
public class Slide09_ArrayListExample {

    public static void main(String[] args) {
        demonstrateCreation();
        demonstrateAddingElements();
        demonstrateRemovingElements();
        demonstrateAccessingElements();
        demonstrateSearchingAndChecking();
        demonstrateCapacityAndPerformance();
        demonstratePracticalUseCase();
    }

    private static void demonstrateCreation() {
        System.out.println("=== Создание ArrayList ===");

        // Объявление и создание
        ArrayList<String> fruits = new ArrayList<>();
        System.out.println("Пустой список: " + fruits);

        // С указанием начальной ёмкости (оптимизация)
        ArrayList<Integer> numbers = new ArrayList<>(50);
        System.out.println("Список с начальной ёмкостью 50: " + numbers);

        // Через интерфейс List (рекомендуемый способ)
        List<Double> prices = new ArrayList<>();
        prices.add(19.99);
        prices.add(5.50);
        System.out.println("Цены: " + prices);
    }

    private static void demonstrateAddingElements() {
        System.out.println("\n=== Добавление элементов ===");

        ArrayList<String> list = new ArrayList<>();

        // Добавление в конец
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("После add(): " + list);

        // Добавление по индексу
        list.add(0, "Orange"); // в начало
        list.add(2, "Grape");  // между Apple и Banana
        System.out.println("После add(index, element): " + list);

        // Добавление коллекции
        ArrayList<String> moreFruits = new ArrayList<>();
        moreFruits.add("Mango");
        moreFruits.add("Pineapple");

        list.addAll(moreFruits);
        System.out.println("После addAll(): " + list);

        // Добавление в конец (ещё раз)
        list.add("Kiwi");
        System.out.println("Добавлен Kiwi: " + list);
    }

    private static void demonstrateRemovingElements() {
        System.out.println("\n=== Удаление элементов ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Date");
        list.add("Elderberry");

        System.out.println("Исходный список: " + list);

        // Удаление по индексу
        String removed = list.remove(1); // удаляем Banana
        System.out.println("Удалён элемент по индексу 1: " + removed);
        System.out.println("После remove(1): " + list);

        // Удаление по значению
        boolean isRemoved = list.remove("Cherry"); // удаляем Cherry
        System.out.println("Удалён ли 'Cherry'? " + isRemoved);
        System.out.println("После remove('Cherry'): " + list);

        // Удаление диапазона (Java 8+)
        list.removeIf(s -> s.startsWith("D")); // удаляем все, начинающиеся на D
        System.out.println("После removeIf: " + list);

        // Очистка всего списка
        list.clear();
        System.out.println("После clear(): " + list);
    }

    private static void demonstrateAccessingElements() {
        System.out.println("\n=== Получение элементов ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("Список: " + list);

        // Получение по индексу
        String first = list.get(0);
        String last = list.get(list.size() - 1);
        System.out.println("Первый элемент: " + first);
        System.out.println("Последний элемент: " + last);

        // Изменение элемента по индексу
        list.set(1, "Blueberry"); // заменяем Banana на Blueberry
        System.out.println("После set(1, 'Blueberry'): " + list);

        // Итерация по списку
        System.out.print("Итерация через for-each: ");
        for (String fruit : list) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        // Итерация через индекс
        System.out.print("Итерация через индекс: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    private static void demonstrateSearchingAndChecking() {
        System.out.println("\n=== Поиск и проверка ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Apple"); // дубликат

        System.out.println("Список: " + list);

        // Проверка наличия элемента
        boolean hasApple = list.contains("Apple");
        boolean hasGrape = list.contains("Grape");
        System.out.println("Содержит 'Apple'? " + hasApple);
        System.out.println("Содержит 'Grape'? " + hasGrape);

        // Поиск первого вхождения
        int firstIndex = list.indexOf("Apple");
        int lastIndex = list.lastIndexOf("Apple");
        System.out.println("Первое вхождение 'Apple': " + firstIndex);
        System.out.println("Последнее вхождение 'Apple': " + lastIndex);

        // Проверка на пустоту
        System.out.println("Список пуст? " + list.isEmpty());

        // Размер списка
        System.out.println("Размер списка: " + list.size());
    }

    private static void demonstrateCapacityAndPerformance() {
        System.out.println("\n=== Ёмкость и производительность ===");

        ArrayList<String> list = new ArrayList<>(10); // начальная ёмкость 10
        System.out.println("Начальная ёмкость: " + getCapacity(list));

        // Добавляем больше элементов — ёмкость увеличится автоматически
        for (int i = 0; i < 20; i++) {
            list.add("Item" + i);
        }

        System.out.println("После добавления 20 элементов:");
        System.out.println("Размер: " + list.size());
        System.out.println("Текущая ёмкость: " + getCapacity(list)); // может быть > 20

        // Производительность при вставке в середину
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            list.add(0, "New Item"); // вставка в начало — медленно!
        }
        long end = System.currentTimeMillis();
        System.out.println("Вставка 1000 элементов в начало заняла: " + (end - start) + " мс");

        // Для сравнения — добавление в конец
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            list.add("End Item"); // в конец — быстро!
        }
        end = System.currentTimeMillis();
        System.out.println("Добавление 1000 элементов в конец заняло: " + (end - start) + " мс");
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Список покупок ===");

        ArrayList<String> shoppingList = new ArrayList<>();

        // Добавляем товары
        shoppingList.add("Хлеб");
        shoppingList.add("Молоко");
        shoppingList.add("Яйца");
        shoppingList.add("Сыр");

        System.out.println("Список покупок: " + shoppingList);

        // Удаляем купленное
        shoppingList.remove("Молоко");
        System.out.println("После покупки молока: " + shoppingList);

        // Проверяем, есть ли что-то
        if (shoppingList.contains("Хлеб")) {
            System.out.println("✅ Хлеб ещё не куплен.");
        }

        // Выводим список с номерами
        System.out.println("Список покупок с номерами:");
        for (int i = 0; i < shoppingList.size(); i++) {
            System.out.println((i + 1) + ". " + shoppingList.get(i));
        }
    }

    // Метод для получения текущей ёмкости ArrayList (через рефлексию)
    private static int getCapacity(ArrayList<?> list) {
        try {
            java.lang.reflect.Field field = ArrayList.class.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] array = (Object[]) field.get(list);
            return array.length;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return -1;
        }
    }
}
