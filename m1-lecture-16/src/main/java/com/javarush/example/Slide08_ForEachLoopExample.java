package com.javarush.example;

import java.util.*;

/**
 * Пример использования цикла for-each для обхода коллекций.
 * Демонстрирует синтаксис, преимущества и ограничения.
 */
public class Slide08_ForEachLoopExample {

    public static void main(String[] args) {
        demonstrateBasicUsage();
        demonstrateWithDifferentCollections();
        demonstrateIterationOverArrays();
        demonstrateCommonMistakes();
        demonstrateForEachVsIterator();
        demonstrateAdvancedUseCases();
        demonstratePracticalUseCase();
    }

    private static void demonstrateBasicUsage() {
        System.out.println("=== Базовое использование for-each ===");

        // Создаём список строк
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        System.out.println("Список фруктов:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }

        // Создаём массив чисел
        int[] numbers = {1, 2, 3, 4, 5};

        System.out.println("Массив чисел:");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();

        // Создаём множество
        Set<String> tags = new HashSet<>();
        tags.add("Java");
        tags.add("Programming");
        tags.add("OOP");

        System.out.println("Множество тегов:");
        for (String tag : tags) {
            System.out.println("  " + tag);
        }
    }

    private static void demonstrateWithDifferentCollections() {
        System.out.println("\n=== for-each с разными коллекциями ===");

        // List
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");

        System.out.println("List:");
        for (String item : list) {
            System.out.print(item + " ");
        }
        System.out.println();

        // Set
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");

        System.out.println("Set:");
        for (String fruit : set) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        // Map — итерируем ключи
        Map<String, Integer> map = new HashMap<>();
        map.put("Age", 30);
        map.put("Score", 85);

        System.out.println("Map (ключи):");
        for (String key : map.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        // Map — итерируем значения
        System.out.println("Map (значения):");
        for (Integer value : map.values()) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Map — итерируем пары (entrySet)
        System.out.println("Map (пары ключ-значение):");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();
    }

    private static void demonstrateIterationOverArrays() {
        System.out.println("\n=== for-each с массивами ===");

        // Массив примитивов
        int[] intArray = {1, 2, 3, 4, 5};
        System.out.print("Массив int: ");
        for (int num : intArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Массив объектов
        String[] stringArray = {"A", "B", "C"};
        System.out.print("Массив String: ");
        for (String s : stringArray) {
            System.out.print(s + " ");
        }
        System.out.println();

        // Многомерный массив
        int[][] matrix = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println("Многомерный массив:");
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private static void demonstrateCommonMistakes() {
        System.out.println("\n=== Распространённые ошибки ===");

        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");

        // Ошибка 1: Попытка удалить элемент во время итерации
        try {
            for (String fruit : list) {
                if ("Banana".equals(fruit)) {
                    list.remove(fruit); // ConcurrentModificationException!
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("⚠️ Удаление элемента в цикле for-each вызвало ConcurrentModificationException!");
        }

        // Ошибка 2: Попытка получить индекс
        // for (String fruit : list) {
        //     System.out.println("Индекс: " + ???); // Нет доступа к индексу!
        // }

        // Ошибка 3: Использование с неподдерживаемым типом
        // Object obj = new Object();
        // for (Object o : obj) { } // ОШИБКА КОМПИЛЯЦИИ — Object не реализует Iterable!

        System.out.println("✅ Цикл for-each не позволяет получить индекс и безопасно изменять коллекцию.");
    }

    private static void demonstrateForEachVsIterator() {
        System.out.println("\n=== for-each vs Итератор ===");

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

        for (String fruit : input) {
            if (fruit.length() > 5) { // фильтрация
                output.add(fruit.toUpperCase()); // преобразование
            }
        }

        System.out.println("Фильтрация и преобразование: " + output);

        // Поиск первого элемента, удовлетворяющего условию
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        Integer firstEven = null;
        for (Integer num : numbers) {
            if (num % 2 == 0) {
                firstEven = num;
                break; // выходим при первом найденном
            }
        }

        System.out.println("Первое чётное число: " + firstEven);

        // Суммирование элементов
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("Сумма всех чисел: " + sum);
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Подсчёт слов ===");

        // Создаём список слов
        List<String> words = new ArrayList<>();
        words.add("Java");
        words.add("is");
        words.add("awesome");
        words.add("Java");
        words.add("is");
        words.add("fun");

        System.out.println("Все слова: " + words);

        // Подсчёт уникальных слов
        Set<String> uniqueWords = new HashSet<>();
        for (String word : words) {
            uniqueWords.add(word);
        }
        System.out.println("Уникальные слова: " + uniqueWords);

        // Подсчёт частоты слов
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.merge(word, 1, Integer::sum); // если ключ есть — добавляем 1, если нет — создаём с 1
        }
        System.out.println("Частота слов: " + wordCount);

        // Вывод слов с частотой
        System.out.println("Слова и их частота:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
}