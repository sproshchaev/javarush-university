package com.javarush.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Пример использования ключевого слова var для вывода типов компилятором.
 * Демонстрирует преимущества и ограничения var.
 */
public class Slide13_VarTypeInferenceExample {

    public static void main(String[] args) {
        demonstrateBasicUsage();
        demonstrateWithCollections();
        demonstrateComplexGenerics();
        demonstrateLimitations();
        demonstrateBestPractices();
        demonstratePerformanceAndSafety();
        demonstratePracticalUseCase();
    }

    private static void demonstrateBasicUsage() {
        System.out.println("=== Базовое использование var ===");

        // Целое число
        var age = 25;
        System.out.println("age: " + age + " (тип: int)");

        // Вещественное число
        var price = 19.99;
        System.out.println("price: " + price + " (тип: double)");

        // Строка
        var greeting = "Hello, World!";
        System.out.println("greeting: " + greeting + " (тип: String)");

        // Логическое значение
        var isActive = true;
        System.out.println("isActive: " + isActive + " (тип: boolean)");

        // Символ
        var letter = 'A';
        System.out.println("letter: " + letter + " (тип: char)");

        // Массив
        var numbers = new int[]{1, 2, 3};
        System.out.println("numbers: " + java.util.Arrays.toString(numbers) + " (тип: int[])");
    }

    private static void demonstrateWithCollections() {
        System.out.println("\n=== Использование var с коллекциями ===");

        // ArrayList<String>
        var stringList = new ArrayList<String>();
        stringList.add("Apple");
        stringList.add("Banana");
        System.out.println("stringList: " + stringList);

        // ArrayList<Integer>
        var intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(2);
        System.out.println("intList: " + intList);

        // HashMap<String, Integer>
        var map = new HashMap<String, Integer>();
        map.put("One", 1);
        map.put("Two", 2);
        System.out.println("map: " + map);

        // Сложные коллекции
        var nestedList = new ArrayList<List<String>>();
        nestedList.add(List.of("A", "B"));
        nestedList.add(List.of("C", "D"));
        System.out.println("nestedList: " + nestedList);
    }

    private static void demonstrateComplexGenerics() {
        System.out.println("\n=== Работа с сложными обобщёнными типами ===");

        // Без var — много текста
        HashMap<String, List<HashMap<String, Integer>>> complexMap1 =
                new HashMap<String, List<HashMap<String, Integer>>>();

        // С var — намного короче
        var complexMap2 = new HashMap<String, List<HashMap<String, Integer>>>();

        // Заполняем
        var innerMap = new HashMap<String, Integer>();
        innerMap.put("key", 42);

        var innerList = new ArrayList<HashMap<String, Integer>>();
        innerList.add(innerMap);

        complexMap2.put("outerKey", innerList);

        System.out.println("complexMap2: " + complexMap2);
        System.out.println("✅ var значительно упрощает работу со сложными типами!");
    }

    private static void demonstrateLimitations() {
        System.out.println("\n=== Ограничения var ===");

        // ❌ Нельзя использовать без инициализации
        // var uninitialized; // ОШИБКА КОМПИЛЯЦИИ!

        // ❌ Нельзя использовать для полей класса
        // private var field = "value"; // ОШИБКА КОМПИЛЯЦИИ!

        // ❌ Нельзя использовать для параметров методов
        // public void method(var param) { } // ОШИБКА КОМПИЛЯЦИИ!

        // ❌ Нельзя использовать для возвращаемого значения
        // public var method() { return "result"; } // ОШИБКА КОМПИЛЯЦИИ!

        // ❌ Нельзя использовать с null без явного указания типа
        // var nullVar = null; // ОШИБКА КОМПИЛЯЦИИ — компилятор не может вывести тип

        // ✅ Можно использовать с null, если тип указан явно
        var nullableString = (String) null;
        System.out.println("nullableString: " + nullableString);

        // ❌ Нельзя использовать в цикле for-each без инициализации
        // for (var item : items) { } // ОШИБКА, если items не объявлены
    }

    private static void demonstrateBestPractices() {
        System.out.println("\n=== Рекомендации по использованию var ===");

        // ✅ Хорошо: тип очевиден из значения
        var count = 0;
        var message = "Hello";
        var isActive = false;

        // ✅ Хорошо: длинные обобщённые типы
        var complexList = new ArrayList<HashMap<String, List<Integer>>>();

        // ❌ Плохо: тип неочевиден
        // var result = someMethod(); // Что за тип? Нужно смотреть в методе
        // Лучше: List<String> result = someMethod();

        // ✅ Хорошо: в лямбда-выражениях и потоках
        var stream = List.of(1, 2, 3).stream()
                .filter(x -> x > 1)
                .map(x -> x * 2)
                .toList();
        System.out.println("stream result: " + stream);

        // ❌ Плохо: когда var скрывает смысл
        var a = 10;
        var b = 20;
        var c = a + b; // Что такое a, b, c? int? double?
        // Лучше: int sum = a + b;

        System.out.println("✅ Используйте var, когда тип очевиден или слишком длинный.");
    }

    private static void demonstratePerformanceAndSafety() {
        System.out.println("\n=== Производительность и безопасность ===");

        // var — это не динамический тип!
        var number = 42;
        // number = "text"; // ОШИБКА КОМПИЛЯЦИИ — тип int, нельзя присвоить String

        // Тип определяется на этапе компиляции
        System.out.println("number: " + number + " (тип: int)");

        // Производительность — такая же, как и у явного типа
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            var temp = i;
        }
        long end = System.currentTimeMillis();
        System.out.println("Цикл с var занял: " + (end - start) + " мс");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            int temp = i;
        }
        end = System.currentTimeMillis();
        System.out.println("Цикл с int занял: " + (end - start) + " мс");

        System.out.println("✅ var не влияет на производительность — это синтаксический сахар.");
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Обработка данных ===");

        // Создаём список пользователей
        var users = new ArrayList<User>();
        users.add(new User("Alice", 30));
        users.add(new User("Bob", 25));

        System.out.println("Пользователи: " + users);

        // Фильтруем по возрасту
        var adults = users.stream()
                .filter(user -> user.getAge() >= 18)
                .toList();
        System.out.println("Взрослые: " + adults);

        // Группируем по возрасту
        var groupedByAge = users.stream()
                .collect(java.util.stream.Collectors.groupingBy(User::getAge));
        System.out.println("Группировка по возрасту: " + groupedByAge);

        // Создаём карту имён
        var nameToUser = users.stream()
                .collect(java.util.stream.Collectors.toMap(User::getName, user -> user));
        System.out.println("Имя -> Пользователь: " + nameToUser);
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