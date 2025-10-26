package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Примеры создания объектов ArrayList с разными типами.
 * Демонстрирует синтаксис, использование diamond operator и особенности типизации.
 */
public class Slide10_ArrayListCreationExample {

    public static void main(String[] args) {
        demonstrateBasicCreation();
        demonstrateDiamondOperator();
        demonstratePrimitiveTypesWithAutoboxing();
        demonstrateTypeSafety();
        demonstrateCommonMistakes();
        demonstratePracticalUseCase();
    }

    private static void demonstrateBasicCreation() {
        System.out.println("=== Базовое создание ArrayList ===");

        // Список целых чисел
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(20);
        integers.add(30);
        System.out.println("Список Integer: " + integers);

        // Список строк
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Java");
        strings.add("Python");
        strings.add("C++");
        System.out.println("Список String: " + strings);

        // Список вещественных чисел
        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(3.14);
        doubles.add(2.718);
        doubles.add(1.414);
        System.out.println("Список Double: " + doubles);

        // Список логических значений
        ArrayList<Boolean> booleans = new ArrayList<>();
        booleans.add(true);
        booleans.add(false);
        booleans.add(true);
        System.out.println("Список Boolean: " + booleans);
    }

    private static void demonstrateDiamondOperator() {
        System.out.println("\n=== Diamond Operator (Java 7+) ===");

        // Явное указание типа (до Java 7)
        ArrayList<String> oldWay = new ArrayList<String>();

        // Diamond operator — тип выводится автоматически
        ArrayList<String> newWay = new ArrayList<>();

        // Это эквивалентно
        System.out.println("oldWay == newWay? " + (oldWay.getClass() == newWay.getClass()));

        // Можно использовать с любым типом
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        System.out.println("Список чисел (diamond): " + numbers);

        // Даже если тип сложный
        ArrayList<List<String>> listOfLists = new ArrayList<>();
        listOfLists.add(new ArrayList<>());
        listOfLists.get(0).add("Nested");
        System.out.println("Список списков: " + listOfLists);
    }

    private static void demonstratePrimitiveTypesWithAutoboxing() {
        System.out.println("\n=== Работа с примитивами через автоупаковку ===");

        // ArrayList<int> — ОШИБКА! Нельзя хранить примитивы
        // ArrayList<int> primitiveList = new ArrayList<int>(); // Не скомпилируется!

        // Но можно хранить обёртки — и добавлять примитивы (автоупаковка)
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(5);      // auto-boxing: int → Integer
        intList.add(10);     // auto-boxing
        intList.add(15);     // auto-boxing

        System.out.println("Список Integer с примитивами: " + intList);

        // Получение элемента — автораспаковка
        int first = intList.get(0); // Integer → int
        System.out.println("Первый элемент как int: " + first);

        // Аналогично для других типов
        ArrayList<Double> doubleList = new ArrayList<>();
        doubleList.add(3.14);   // double → Double
        doubleList.add(2.718);  // double → Double

        double pi = doubleList.get(0); // Double → double
        System.out.println("Число пи: " + pi);
    }

    private static void demonstrateTypeSafety() {
        System.out.println("\n=== Безопасность типов ===");

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("World");

        // Компилятор не позволит добавить не-строку
        // stringList.add(123); // ОШИБКА КОМПИЛЯЦИИ!

        // Получение элемента — не нужно приведение типов
        String first = stringList.get(0); // компилятор знает, что это String
        System.out.println("Первый элемент: " + first);

        // Без типизации — Object и приведение типов
        @SuppressWarnings("rawtypes")
        ArrayList rawList = new ArrayList();
        rawList.add("Text");
        rawList.add(123); // можно добавить что угодно!

        // При получении — нужно приводить тип
        String text = (String) rawList.get(0); // unsafe!
        int number = (int) rawList.get(1); // unsafe!

        System.out.println("Без типизации: " + text + ", " + number);
        System.out.println("⚠️ Опасно! Нет проверки типов на этапе компиляции.");
    }

    private static void demonstrateCommonMistakes() {
        System.out.println("\n=== Распространённые ошибки ===");

        // Ошибка 1: Попытка использовать примитивный тип
        try {
            // ArrayList<int> list = new ArrayList<int>(); // Не скомпилируется!
            System.out.println("Ошибка 1: ArrayList<int> — невозможно!");
        } catch (Exception e) {
            System.out.println("Ошибка 1: " + e.getMessage());
        }

        // Ошибка 2: Забыли <> — сырой тип (raw type)
        @SuppressWarnings("rawtypes")
        ArrayList rawList = new ArrayList(); // сырой тип — не рекомендуется
        rawList.add("String");
        rawList.add(123); // можно добавить что угодно

        System.out.println("Сырой тип: " + rawList);
        System.out.println("⚠️ Предупреждения компилятора и риск ClassCastException!");

        // Ошибка 3: Использование несуществующего типа
        // ArrayList<NonExistentClass> list = new ArrayList<>(); // Ошибка компиляции, если класс не существует

        // Ошибка 4: Неправильное использование diamond operator
        // ArrayList<String> list = new ArrayList<Integer>(); // Ошибка компиляции — несовместимые типы
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Список студентов ===");

        // Создаём список имён студентов
        ArrayList<String> studentNames = new ArrayList<>();
        studentNames.add("Анна");
        studentNames.add("Борис");
        studentNames.add("Виктория");

        System.out.println("Список студентов: " + studentNames);

        // Создаём список их оценок
        ArrayList<Integer> grades = new ArrayList<>();
        grades.add(5);
        grades.add(4);
        grades.add(5);

        System.out.println("Оценки: " + grades);

        // Создаём список посещаемости (true — был, false — не был)
        ArrayList<Boolean> attendance = new ArrayList<>();
        attendance.add(true);
        attendance.add(false);
        attendance.add(true);

        System.out.println("Посещаемость: " + attendance);

        // Выводим информацию
        for (int i = 0; i < studentNames.size(); i++) {
            System.out.println(studentNames.get(i) + ": оценка " + grades.get(i) +
                    ", посетил? " + attendance.get(i));
        }
    }
}
