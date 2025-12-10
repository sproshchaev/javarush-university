package com.javarush.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Практический пример использования generic-методов.
 * Показывает их силу в сочетании с wildcards и в утилитных классах.
 */
public class Slide08_GenericMethodsInPractice {

    // ========== УТИЛИТНЫЙ КЛАСС (только static generic-методы) ==========
    static class CollectionUtils {
        // 1. Копирование из одного списка в другой (производитель -> потребитель)
        // PECS в действии: источник - Producer (? extends), назначение - Consumer (? super)
        public static <T> void copy(List<? extends T> source, List<? super T> destination) {
            for (T item : source) {
                destination.add(item);
            }
        }

        // 2. Поиск максимального элемента (требует Comparable)
        public static <T extends Comparable<T>> T findMax(List<T> list) {
            if (list == null || list.isEmpty()) {
                return null;
            }
            T max = list.get(0);
            for (T item : list) {
                if (item.compareTo(max) > 0) {
                    max = item;
                }
            }
            return max;
        }

        // 3. Создание пары из двух значений (как Map.Entry)
        public static <K, V> Pair<K, V> createPair(K key, V value) {
            return new Pair<>(key, value);
        }

        // 4. Объединение двух массивов в один список
        @SafeVarargs
        public static <T> List<T> mergeArrays(T[]... arrays) {
            List<T> result = new ArrayList<>();
            for (T[] array : arrays) {
                result.addAll(Arrays.asList(array));
            }
            return result;
        }
    }

    // ========== ВСПОМОГАТЕЛЬНЫЙ GENERIC-КЛАСС ==========
    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }

        @Override
        public String toString() {
            return "Pair{key=" + key + ", value=" + value + "}";
        }
    }

    // ========== ТОЧКА ВХОДА ==========
    public static void main(String[] args) {
        System.out.println("========== 1. Копирование списков (copy) ==========\n");

        List<Integer> sourceInts = Arrays.asList(1, 2, 3, 4, 5);
        List<Number> destNumbers = new ArrayList<>(); // Number super Integer

        // Копируем Integer (производитель) в Number (потребитель)
        CollectionUtils.copy(sourceInts, destNumbers);
        System.out.println("Скопировано из List<Integer> в List<Number>: " + destNumbers);

        List<Double> sourceDoubles = Arrays.asList(1.5, 2.5, 3.5);
        List<Object> destObjects = new ArrayList<>(); // Object super Double

        CollectionUtils.copy(sourceDoubles, destObjects);
        System.out.println("Скопировано из List<Double> в List<Object>: " + destObjects);

        System.out.println("\n========== 2. Поиск максимального элемента (findMax) ==========\n");

        List<String> names = Arrays.asList("Анна", "Борис", "Виктор", "Александр");
        List<Integer> ages = Arrays.asList(25, 30, 28, 35);

        String longestName = CollectionUtils.findMax(names); // Сравнивает по алфавиту
        Integer oldestAge = CollectionUtils.findMax(ages);   // Сравнивает числа

        System.out.println("Самое длинное имя: " + longestName);
        System.out.println("Самый большой возраст: " + oldestAge);

        System.out.println("\n========== 3. Создание пар (createPair) ==========\n");

        Pair<String, Integer> nameAgePair = CollectionUtils.createPair("Иван", 30);
        Pair<Integer, String> idNamePair = CollectionUtils.createPair(101, "Мария");

        System.out.println("Пара 1: " + nameAgePair);
        System.out.println("Пара 2: " + idNamePair);

        System.out.println("\n========== 4. Объединение массивов (mergeArrays) ==========\n");

        String[] fruits = {"Яблоко", "Банан"};
        String[] vegetables = {"Морковь", "Огурец"};
        String[] berries = {"Клубника", "Малина"};

        List<String> allFood = CollectionUtils.mergeArrays(fruits, vegetables, berries);
        System.out.println("Все продукты: " + allFood);

        Integer[] odds = {1, 3, 5};
        Integer[] evens = {2, 4, 6};
        List<Integer> allNumbers = CollectionUtils.mergeArrays(odds, evens);
        System.out.println("Все числа: " + allNumbers);

        System.out.println("\n========== 5. Generic-методы в не-generic классе ==========\n");

        // Демонстрация, что наш основной класс Slide08_GenericMethodsInPractice
        // не является generic, но содержит generic-метод
        String test = genericMethodInRegularClass("Просто строка");
        System.out.println("Generic-метод в обычном классе вернул: " + test);

        System.out.println("\n========== ВЫВОД ==========");
        System.out.println("1. Generic-методы делают отдельные алгоритмы универсальными.");
        System.out.println("2. Часто используются в static утилитных классах (как Collections).");
        System.out.println("3. Отлично сочетаются с wildcards для реализации PECS.");
        System.out.println("4. Позволяют писать типобезопасный код без дублирования.");
    }

    // Пример generic-метода в обычном (не-generic) классе
    public static <T> String genericMethodInRegularClass(T item) {
        return "Получен: " + item + " типа " + item.getClass().getSimpleName();
    }
}