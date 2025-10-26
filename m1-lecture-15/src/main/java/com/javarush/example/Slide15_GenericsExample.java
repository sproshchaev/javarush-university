package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример использования дженериков в Java.
 * Демонстрирует безопасность типов, стирание типов и основные принципы работы.
 */
public class Slide15_GenericsExample {

    public static void main(String[] args) {
        demonstrateBasicUsage();
        demonstrateTypeSafety();
        demonstrateTypeErasure();
        demonstrateGenericMethods();
        demonstrateBoundedGenerics();
        demonstrateWildcards();
        demonstratePracticalUseCase();
    }

    private static void demonstrateBasicUsage() {
        System.out.println("=== Базовое использование дженериков ===");

        // Создание списка строк
        List<String> stringList = new ArrayList<>();
        stringList.add("Apple");
        stringList.add("Banana");
        System.out.println("stringList: " + stringList);

        // Создание списка целых чисел
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        System.out.println("intList: " + intList);

        // Создание списка пользователей
        List<User> userList = new ArrayList<>();
        userList.add(new User("Alice", 30));
        userList.add(new User("Bob", 25));
        System.out.println("userList: " + userList);
    }

    private static void demonstrateTypeSafety() {
        System.out.println("\n=== Безопасность типов ===");

        // Без дженериков — можно добавить что угодно
        @SuppressWarnings("rawtypes")
        List rawList = new ArrayList();
        rawList.add("String");
        rawList.add(123); // OK, но опасно!
        rawList.add(true);

        // При получении — нужно приводить тип
        String text = (String) rawList.get(0); // OK
        // int number = (int) rawList.get(1); // ClassCastException при выполнении!
        try {
            int number = (int) rawList.get(1); // Опасно!
            System.out.println("Number: " + number);
        } catch (ClassCastException e) {
            System.out.println("⚠️ ClassCastException при приведении Object к int!");
        }

        // С дженериками — компилятор не позволит добавить неправильный тип
        List<String> genericList = new ArrayList<>();
        genericList.add("Hello"); // OK
        // genericList.add(123); // ОШИБКА КОМПИЛЯЦИИ!
        // genericList.add(true); // ОШИБКА КОМПИЛЯЦИИ!

        System.out.println("genericList: " + genericList);
        System.out.println("✅ Компилятор защищает от ошибок типов.");
    }

    private static void demonstrateTypeErasure() {
        System.out.println("\n=== Стирание типов (Type Erasure) ===");

        // Создаём два списка с разными типами
        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        // После компиляции — оба являются просто ArrayList
        System.out.println("Тип stringList: " + stringList.getClass().getName());
        System.out.println("Тип intList: " + intList.getClass().getName());

        // Они одного типа!
        System.out.println("stringList instanceof ArrayList: " + (stringList instanceof ArrayList));
        System.out.println("intList instanceof ArrayList: " + (intList instanceof ArrayList));

        // Проверяем, что они равны по типу
        System.out.println("stringList и intList одного типа? " +
                (stringList.getClass() == intList.getClass())); // true

        // Но компилятор всё равно проверяет типы на этапе компиляции
        // stringList.add(123); // ОШИБКА КОМПИЛЯЦИИ — хотя runtime тип одинаковый

        System.out.println("⚠️ Информация о типах-параметрах теряется после компиляции (type erasure).");
    }

    private static void demonstrateGenericMethods() {
        System.out.println("\n=== Обобщённые методы ===");

        // Метод, который работает с любым типом
        String result1 = processItem("Hello");
        Integer result2 = processItem(42);
        Boolean result3 = processItem(true);

        System.out.println("processItem('Hello'): " + result1);
        System.out.println("processItem(42): " + result2);
        System.out.println("processItem(true): " + result3);

        // Метод с несколькими параметрами
        Pair<String, Integer> pair = createPair("Age", 30);
        System.out.println("Pair: " + pair);
    }

    private static <T> T processItem(T item) {
        System.out.println("Обрабатываем элемент: " + item);
        return item;
    }

    private static <K, V> Pair<K, V> createPair(K key, V value) {
        return new Pair<>(key, value);
    }

    // Вспомогательный класс для пары
    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Pair{" + "key=" + key + ", value=" + value + '}';
        }
    }

    private static void demonstrateBoundedGenerics() {
        System.out.println("\n=== Ограниченные дженерики (Bounded Generics) ===");

        // Тип T должен быть подклассом Number
        NumberProcessor<Double> doubleProcessor = new NumberProcessor<>(3.14);
        NumberProcessor<Integer> intProcessor = new NumberProcessor<>(42);

        System.out.println("doubleProcessor: " + doubleProcessor.getValue());
        System.out.println("intProcessor: " + intProcessor.getValue());

        // Можно вызывать методы Number
        System.out.println("doubleProcessor.doubleValue(): " + doubleProcessor.getValue().doubleValue());
        System.out.println("intProcessor.intValue(): " + intProcessor.getValue().intValue());

        // Нельзя создать с типом, не являющимся Number
        // NumberProcessor<String> stringProcessor = new NumberProcessor<>("text"); // ОШИБКА КОМПИЛЯЦИИ!
    }

    static class NumberProcessor<T extends Number> {
        private T value;

        public NumberProcessor(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    private static void demonstrateWildcards() {
        System.out.println("\n=== Wildcards (?) ===");

        // Неизвестный тип — можно читать, но не писать
        List<?> unknownList = List.of("A", "B", "C");
        System.out.println("unknownList: " + unknownList);

        // Можно получить элемент (как Object)
        Object first = unknownList.get(0);
        System.out.println("Первый элемент: " + first);

        // Нельзя добавить элемент (кроме null)
        // unknownList.add("D"); // ОШИБКА КОМПИЛЯЦИИ!
        unknownList.add(null); // OK

        // Ограниченные wildcards
        List<? extends Number> numberList = List.of(1, 2, 3.14);
        for (Number n : numberList) {
            System.out.print(n.doubleValue() + " ");
        }
        System.out.println();

        // List<? super Integer> — можно добавлять Integer и его подклассы
        List<Object> objectList = new ArrayList<>();
        objectList.add("Text");
        objectList.add(42);
        List<? super Integer> superList = objectList;
        superList.add(100); // OK
        System.out.println("superList: " + superList);
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Универсальный хранилище ===");

        // Создаём хранилище строк
        Storage<String> stringStorage = new Storage<>();
        stringStorage.addItem("Hello");
        stringStorage.addItem("World");
        System.out.println("stringStorage: " + stringStorage.getItems());

        // Создаём хранилище чисел
        Storage<Integer> intStorage = new Storage<>();
        intStorage.addItem(1);
        intStorage.addItem(2);
        System.out.println("intStorage: " + intStorage.getItems());

        // Создаём хранилище пользователей
        Storage<User> userStorage = new Storage<>();
        userStorage.addItem(new User("Alice", 30));
        userStorage.addItem(new User("Bob", 25));
        System.out.println("userStorage: " + userStorage.getItems());

        // Используем универсальный метод
        printStorage(stringStorage);
        printStorage(intStorage);
        printStorage(userStorage);
    }

    // Универсальный класс-хранилище
    static class Storage<T> {
        private List<T> items = new ArrayList<>();

        public void addItem(T item) {
            items.add(item);
        }

        public List<T> getItems() {
            return new ArrayList<>(items);
        }
    }

    // Универсальный метод для вывода хранилища
    private static <T> void printStorage(Storage<T> storage) {
        System.out.println("Хранилище содержит:");
        for (T item : storage.getItems()) {
            System.out.println("  " + item);
        }
    }

    // Вспомогательный класс для примера
    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + "}";
        }
    }
}
