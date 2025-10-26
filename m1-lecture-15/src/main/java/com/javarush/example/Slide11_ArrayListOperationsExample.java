package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример использования основных операций с ArrayList.
 * Демонстрирует добавление, получение, изменение, удаление, очистку и проверки.
 */
public class Slide11_ArrayListOperationsExample {

    public static void main(String[] args) {
        demonstrateAdding();
        demonstrateGettingAndSetting();
        demonstrateRemoving();
        demonstrateClearing();
        demonstrateCheckingMethods();
        demonstrateToArray();
        demonstrateErrorHandling();
        demonstratePracticalUseCase();
    }

    private static void demonstrateAdding() {
        System.out.println("=== Добавление элементов ===");

        ArrayList<String> list = new ArrayList<>();

        // add(T value) — добавляет в конец
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        System.out.println("После add(): " + list);

        // add(int index, T value) — добавляет на определённую позицию
        list.add(0, "Orange"); // в начало
        list.add(2, "Grape");  // между Apple и Banana
        System.out.println("После add(index, value): " + list);

        // Добавление нескольких элементов
        List<String> moreFruits = List.of("Mango", "Pineapple");
        list.addAll(moreFruits);
        System.out.println("После addAll(): " + list);
    }

    private static void demonstrateGettingAndSetting() {
        System.out.println("\n=== Получение и изменение элементов ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("Исходный список: " + list);

        // get(int index) — получаем элемент по индексу
        String first = list.get(0);
        String last = list.get(list.size() - 1);
        System.out.println("Первый элемент: " + first);
        System.out.println("Последний элемент: " + last);

        // set(int index, T value) — заменяем элемент
        list.set(1, "Blueberry"); // заменяем Banana на Blueberry
        System.out.println("После set(1, 'Blueberry'): " + list);

        // Изменение через индекс в цикле
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).toUpperCase());
        }
        System.out.println("Все элементы в верхнем регистре: " + list);
    }

    private static void demonstrateRemoving() {
        System.out.println("\n=== Удаление элементов ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add("Date");
        list.add("Elderberry");
        list.add("Apple"); // дубликат

        System.out.println("Исходный список: " + list);

        // remove(int index) — удаляет по индексу, возвращает удалённый элемент
        String removed = list.remove(1); // удаляем Banana
        System.out.println("Удалён элемент по индексу 1: " + removed);
        System.out.println("После remove(1): " + list);

        // remove(T value) — удаляет первое вхождение указанного элемента
        boolean isRemoved = list.remove("Cherry"); // удаляем Cherry
        System.out.println("Удалён ли 'Cherry'? " + isRemoved);
        System.out.println("После remove('Cherry'): " + list);

        // Удаление всех вхождений (Java 8+)
        list.removeIf(s -> s.equals("Apple")); // удаляем все Apple
        System.out.println("После removeIf: " + list);

        // Удаление по условию
        list.removeIf(s -> s.startsWith("D")); // удаляем все, начинающиеся на D
        System.out.println("После removeIf(startsWith('D')): " + list);
    }

    private static void demonstrateClearing() {
        System.out.println("\n=== Очистка списка ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("Перед очисткой: " + list);
        System.out.println("Размер: " + list.size());

        // clear() — удаляет все элементы
        list.clear();
        System.out.println("После clear(): " + list);
        System.out.println("Размер после очистки: " + list.size());
        System.out.println("Список пуст? " + list.isEmpty());
    }

    private static void demonstrateCheckingMethods() {
        System.out.println("\n=== Проверка состояния списка ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("Список: " + list);

        // contains(T value) — проверяет наличие элемента
        boolean hasApple = list.contains("Apple");
        boolean hasGrape = list.contains("Grape");
        System.out.println("Содержит 'Apple'? " + hasApple);
        System.out.println("Содержит 'Grape'? " + hasGrape);

        // isEmpty() — проверяет, пуст ли список
        System.out.println("Список пуст? " + list.isEmpty());

        // size() — возвращает количество элементов
        System.out.println("Размер списка: " + list.size());

        // Проверка на пустоту перед операциями
        if (!list.isEmpty()) {
            System.out.println("Первый элемент: " + list.get(0));
        }
    }

    private static void demonstrateToArray() {
        System.out.println("\n=== Преобразование в массив ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("Список: " + list);

        // toArray() — возвращает массив Object[]
        Object[] objectArray = list.toArray();
        System.out.println("Массив Object: ");
        for (Object obj : objectArray) {
            System.out.print(obj + " ");
        }
        System.out.println();

        // toArray(T[] a) — возвращает массив указанного типа
        String[] stringArray = list.toArray(new String[0]);
        System.out.println("Массив String: ");
        for (String s : stringArray) {
            System.out.print(s + " ");
        }
        System.out.println();

        // Передача массива в метод
        printArray(stringArray);
    }

    private static void printArray(String[] array) {
        System.out.print("Метод получил массив: ");
        for (String s : array) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private static void demonstrateErrorHandling() {
        System.out.println("\n=== Обработка ошибок ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");

        // Попытка получить элемент по несуществующему индексу
        try {
            String element = list.get(5); // IndexOutOfBoundsException!
            System.out.println(element);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("⚠️ Попытка получить элемент по индексу 5 вызвала исключение!");
        }

        // Попытка установить элемент по несуществующему индексу
        try {
            list.set(5, "Grape"); // IndexOutOfBoundsException!
        } catch (IndexOutOfBoundsException e) {
            System.out.println("⚠️ Попытка установить элемент по индексу 5 вызвала исключение!");
        }

        // Безопасное получение элемента
        int index = 5;
        if (index >= 0 && index < list.size()) {
            System.out.println("Элемент по индексу " + index + ": " + list.get(index));
        } else {
            System.out.println("Индекс " + index + " выходит за пределы списка.");
        }
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Управление задачами ===");

        ArrayList<String> tasks = new ArrayList<>();

        // Добавляем задачи
        tasks.add("Написать отчёт");
        tasks.add("Позвонить клиенту");
        tasks.add("Запланировать встречу");

        System.out.println("Задачи: " + tasks);

        // Отмечаем выполненную задачу (удаляем её)
        tasks.remove("Позвонить клиенту");
        System.out.println("После выполнения: " + tasks);

        // Добавляем новую задачу
        tasks.add(0, "Отправить email"); // в начало
        System.out.println("Новая задача: " + tasks);

        // Проверяем, есть ли ещё задачи
        if (!tasks.isEmpty()) {
            System.out.println("Осталось задач: " + tasks.size());
            System.out.println("Первая задача: " + tasks.get(0));
        }

        // Очищаем список, если всё сделано
        tasks.clear();
        System.out.println("Все задачи выполнены. Список: " + tasks);
    }
}
