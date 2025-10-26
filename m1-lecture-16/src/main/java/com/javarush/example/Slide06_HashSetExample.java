package com.javarush.example;

import java.util.*;

/**
 * Пример использования коллекции HashSet.
 * Демонстрирует создание, основные операции и особенности.
 */
public class Slide06_HashSetExample {

    public static void main(String[] args) {
        demonstrateCreation();
        demonstrateBasicOperations();
        demonstrateUniquenessAndNull();
        demonstratePerformance();
        demonstrateIteration();
        demonstrateWithCustomObjects();
        demonstrateComparisonWithOtherSets();
        demonstratePracticalUseCase();
    }

    private static void demonstrateCreation() {
        System.out.println("=== Создание HashSet ===");

        // Создание пустого HashSet
        HashSet<String> set1 = new HashSet<>();
        System.out.println("Пустой HashSet: " + set1);

        // Создание HashSet с начальной ёмкостью
        HashSet<String> set2 = new HashSet<>(50);
        System.out.println("HashSet с начальной ёмкостью 50: " + set2);

        // Создание HashSet на основе другой коллекции
        List<String> list = List.of("Apple", "Banana", "Cherry");
        HashSet<String> set3 = new HashSet<>(list);
        System.out.println("HashSet из списка: " + set3);

        // Создание через интерфейс Set
        Set<String> set4 = new HashSet<>();
        set4.add("One");
        set4.add("Two");
        System.out.println("HashSet через интерфейс Set: " + set4);
    }

    private static void demonstrateBasicOperations() {
        System.out.println("\n=== Основные операции ===");

        HashSet<String> set = new HashSet<>();

        // add(T value) — добавляет элемент
        boolean added1 = set.add("Apple");
        boolean added2 = set.add("Banana");
        boolean added3 = set.add("Apple"); // дубликат — false

        System.out.println("Добавлен 'Apple'? " + added1); // true
        System.out.println("Добавлен 'Banana'? " + added2); // true
        System.out.println("Добавлен 'Apple' снова? " + added3); // false

        System.out.println("Set после добавления: " + set);

        // remove(T value) — удаляет элемент
        boolean removed = set.remove("Banana");
        System.out.println("Удалён 'Banana'? " + removed); // true
        System.out.println("Set после удаления: " + set);

        // contains(T value) — проверяет наличие элемента
        boolean hasApple = set.contains("Apple");
        boolean hasBanana = set.contains("Banana");
        System.out.println("Содержит 'Apple'? " + hasApple); // true
        System.out.println("Содержит 'Banana'? " + hasBanana); // false

        // size() — количество элементов
        System.out.println("Размер множества: " + set.size());

        // clear() — очистка
        set.clear();
        System.out.println("После очистки: " + set);
        System.out.println("Множество пусто? " + set.isEmpty());
    }

    private static void demonstrateUniquenessAndNull() {
        System.out.println("\n=== Уникальность и null ===");

        HashSet<String> set = new HashSet<>();
        set.add("One");
        set.add("Two");
        set.add("One"); // дубликат — игнорируется
        set.add("Two"); // дубликат — игнорируется

        System.out.println("Set: " + set);
        System.out.println("Размер: " + set.size()); // 2

        // Добавление null
        set.add(null);
        set.add(null); // второй null — тоже игнорируется
        System.out.println("Set с null: " + set);
        System.out.println("Размер после добавления null: " + set.size()); // 3

        // Проверка на null
        System.out.println("Содержит null? " + set.contains(null)); // true

        // Удаление null
        boolean removedNull = set.remove(null);
        System.out.println("Удалён null? " + removedNull); // true
        System.out.println("Set после удаления null: " + set);
    }

    private static void demonstratePerformance() {
        System.out.println("\n=== Производительность ===");

        int size = 100_000;
        HashSet<Integer> hashSet = new HashSet<>();

        long start, end;

        // Добавление элементов
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            hashSet.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("Добавление " + size + " элементов: " + (end - start) + " мс");

        // Поиск элемента
        start = System.currentTimeMillis();
        boolean found = hashSet.contains(size / 2);
        end = System.currentTimeMillis();
        System.out.println("Поиск элемента " + size / 2 + ": " + (end - start) + " мс");
        System.out.println("Найден? " + found);

        // Удаление элемента
        start = System.currentTimeMillis();
        boolean removed = hashSet.remove(size / 2);
        end = System.currentTimeMillis();
        System.out.println("Удаление элемента " + size / 2 + ": " + (end - start) + " мс");
        System.out.println("Удалён? " + removed);

        System.out.println("✅ HashSet обеспечивает высокую производительность (O(1) в среднем).");
    }

    private static void demonstrateIteration() {
        System.out.println("\n=== Итерация по HashSet ===");

        HashSet<String> set = new HashSet<>();
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

    private static void demonstrateWithCustomObjects() {
        System.out.println("\n=== HashSet с пользовательскими объектами ===");

        // Создаём множество пользователей
        HashSet<User> users = new HashSet<>();
        users.add(new User("Alice", 30));
        users.add(new User("Bob", 25));
        users.add(new User("Alice", 30)); // дубликат — будет игнорироваться, если equals/hashCode реализованы правильно!

        System.out.println("Users: " + users);

        // Проверяем размер
        System.out.println("Размер: " + users.size()); // должно быть 2, если Alice с одинаковыми полями считается дубликатом

        // Для корректной работы нужно переопределить equals и hashCode
        User alice1 = new User("Alice", 30);
        User alice2 = new User("Alice", 30);
        System.out.println("alice1.equals(alice2): " + alice1.equals(alice2)); // true
        System.out.println("alice1.hashCode() == alice2.hashCode(): " + (alice1.hashCode() == alice2.hashCode())); // true
    }

    private static void demonstrateComparisonWithOtherSets() {
        System.out.println("\n=== Сравнение с другими реализациями Set ===");

        // HashSet — без порядка
        Set<String> hashSet = new HashSet<>();
        hashSet.add("C");
        hashSet.add("A");
        hashSet.add("B");
        System.out.println("HashSet: " + hashSet); // Порядок произвольный

        // TreeSet — упорядоченный
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

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Уникальные теги ===");

        // Создаём множество тегов
        HashSet<String> tags = new HashSet<>();

        // Добавляем теги (возможно с дубликатами)
        tags.add("Java");
        tags.add("Programming");
        tags.add("OOP");
        tags.add("Java"); // дубликат
        tags.add("Programming"); // дубликат

        System.out.println("Все теги: " + tags);
        System.out.println("Уникальных тегов: " + tags.size());

        // Проверка на наличие тега
        String tagToCheck = "Java";
        if (tags.contains(tagToCheck)) {
            System.out.println("Тег '" + tagToCheck + "' найден!");
        } else {
            System.out.println("Тег '" + tagToCheck + "' не найден.");
        }

        // Удаление тега
        tags.remove("OOP");
        System.out.println("После удаления 'OOP': " + tags);

        // Вывод всех тегов
        System.out.print("Все уникальные теги: ");
        for (String tag : tags) {
            System.out.print(tag + " ");
        }
        System.out.println();
    }

    // Вспомогательный класс для демонстрации
    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return age == user.age && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "User{" + "name='" + name + "', age=" + age + '}';
        }
    }
}