package com.javarush.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Пример использования оператора diamond (<>).
 * Демонстрирует удобство, ограничения и опасности при использовании с var.
 */
public class Slide14_DiamondOperatorExample {

    public static void main(String[] args) {
        demonstrateBasicUsage();
        demonstrateWithComplexGenerics();
        demonstrateWithVar();
        demonstrateWhyVarAndDiamondAreBadTogether();
        demonstrateBestPractices();
        demonstrateReflectionToSeeActualType();
        demonstratePracticalUseCase();
    }

    private static void demonstrateBasicUsage() {
        System.out.println("=== Базовое использование diamond operator ===");

        // Старый способ (Java 6 и ранее)
        ArrayList<String> oldWay = new ArrayList<String>();
        oldWay.add("Apple");
        oldWay.add("Banana");
        System.out.println("Старый способ: " + oldWay);

        // Новый способ с diamond (Java 7+)
        ArrayList<String> newWay = new ArrayList<>();
        newWay.add("Cherry");
        newWay.add("Date");
        System.out.println("Новый способ (diamond): " + newWay);

        // Работает с любыми обобщёнными типами
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        System.out.println("List<Integer>: " + intList);

        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        System.out.println("Map<String, Integer>: " + map);
    }

    private static void demonstrateWithComplexGenerics() {
        System.out.println("\n=== Работа с сложными обобщёнными типами ===");

        // Без diamond — много текста
        HashMap<String, List<HashMap<String, Integer>>> complexMap1 =
                new HashMap<String, List<HashMap<String, Integer>>>();

        // С diamond — намного короче
        HashMap<String, List<HashMap<String, Integer>>> complexMap2 =
                new HashMap<>();

        // Заполняем
        var innerMap = new HashMap<String, Integer>();
        innerMap.put("key", 42);

        var innerList = new ArrayList<HashMap<String, Integer>>();
        innerList.add(innerMap);

        complexMap2.put("outerKey", innerList);

        System.out.println("complexMap2: " + complexMap2);
        System.out.println("✅ Diamond значительно упрощает работу со сложными типами!");
    }

    private static void demonstrateWithVar() {
        System.out.println("\n=== Использование var без diamond ===");

        // var + явное указание типа в конструкторе
        var stringList = new ArrayList<String>();
        stringList.add("Hello");
        stringList.add("World");
        System.out.println("stringList: " + stringList);

        var intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(2);
        System.out.println("intList: " + intList);

        // Это допустимо, но не очень удобно — тип повторяется
        var complexList = new ArrayList<HashMap<String, List<Integer>>>();
        System.out.println("complexList: " + complexList);
    }

    private static void demonstrateWhyVarAndDiamondAreBadTogether() {
        System.out.println("\n=== Почему var + diamond — плохая идея ===");

        // ❌ Опасный код — тип будет ArrayList<Object>
        var badList = new ArrayList<>();
        badList.add("String"); // OK
        badList.add(42);       // OK — Object может хранить всё!
        badList.add(true);     // OK

        System.out.println("badList: " + badList);

        // Попытка получить строку — ClassCastException!
        try {
            String first = (String) badList.get(0); // OK
            System.out.println("Первый элемент как строка: " + first);

            // Но второй элемент — это Integer!
            String second = (String) badList.get(1); // ClassCastException!
            System.out.println("Второй элемент как строка: " + second);
        } catch (ClassCastException e) {
            System.out.println("⚠️ Попытка привести Integer к String вызвала исключение!");
        }

        // Как должно быть:
        var goodList = new ArrayList<String>();
        goodList.add("Hello");
        // goodList.add(42); // ОШИБКА КОМПИЛЯЦИИ — нельзя добавить int в ArrayList<String>

        System.out.println("goodList: " + goodList);
    }

    private static void demonstrateBestPractices() {
        System.out.println("\n=== Рекомендации по использованию diamond ===");

        // ✅ Хорошо: явное объявление типа + diamond
        ArrayList<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // ✅ Хорошо: var + явный тип в конструкторе
        var list3 = new ArrayList<String>();
        var map2 = new HashMap<String, Integer>();

        // ❌ Плохо: var + diamond
        // var badList = new ArrayList<>(); // Тип будет Object!

        // ✅ Хорошо: если тип очевиден из контекста
        var stream = List.of(1, 2, 3).stream()
                .filter(x -> x > 1)
                .map(x -> x * 2)
                .toList();
        System.out.println("stream result: " + stream);

        // ✅ Хорошо: в методах, где тип выводится
        var result = createList();
        System.out.println("result: " + result);

        System.out.println("✅ Используйте diamond, когда тип указан в объявлении переменной.");
    }

    private static void demonstrateReflectionToSeeActualType() {
        System.out.println("\n=== Взгляд внутрь: какой тип реально создан? ===");

        // Создаём список с diamond
        ArrayList<String> list = new ArrayList<>();
        list.add("Test");

        // Создаём список с var и diamond — опасный случай
        var badList = new ArrayList<>();
        badList.add("Test");

        try {
            // Получаем класс объекта
            System.out.println("Тип list: " + list.getClass().getName());
            System.out.println("Тип badList: " + badList.getClass().getName());

            // Проверяем, что оба списка — одного типа (ArrayList)
            System.out.println("list instanceof ArrayList: " + (list instanceof ArrayList));
            System.out.println("badList instanceof ArrayList: " + (badList instanceof ArrayList));

            // Но тип элементов разный!
            System.out.println("Тип элементов list: " + getElementType(list));
            System.out.println("Тип элементов badList: " + getElementType(badList));

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static String getElementType(ArrayList<?> list) {
        if (list.isEmpty()) return "unknown";
        Object first = list.get(0);
        return first != null ? first.getClass().getName() : "null";
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Работа с данными ===");

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

    // Метод для демонстрации best practices
    private static ArrayList<String> createList() {
        return new ArrayList<>();
    }
}
