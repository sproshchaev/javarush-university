package com.javarush.example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Примеры продвинутого использования дженериков.
 * Демонстрирует несколько типов-параметров, вложенные дженерики и массивы дженериков.
 */
public class Slide16_AdvancedGenericsExample {

    public static void main(String[] args) {
        demonstrateMultipleTypeParameters();
        demonstrateNestedGenerics();
        demonstrateArraysOfGenerics();
        demonstrateGenericClassesWithMultipleParams();
        demonstratePracticalUseCase();
    }

    private static void demonstrateMultipleTypeParameters() {
        System.out.println("=== Несколько типов-параметров ===");

        // Map<K, V> — два параметра: K (ключ) и V (значение)
        Map<String, Integer> ageMap = new HashMap<>();
        ageMap.put("Alice", 30);
        ageMap.put("Bob", 25);
        System.out.println("ageMap: " + ageMap);

        // Создаём пару с двумя разными типами
        Pair<String, Integer> pair = new Pair<>("Age", 30);
        System.out.println("pair: " + pair);

        // Три параметра
        Triple<String, Integer, Boolean> triple = new Triple<>("Name", 42, true);
        System.out.println("triple: " + triple);

        // Используем универсальный метод с несколькими параметрами
        String result = combine("Hello", 123, true);
        System.out.println("combine result: " + result);
    }

    private static <T1, T2, T3> String combine(T1 a, T2 b, T3 c) {
        return a + " | " + b + " | " + c;
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

    // Вспомогательный класс для тройки
    static class Triple<T1, T2, T3> {
        private T1 first;
        private T2 second;
        private T3 third;

        public Triple(T1 first, T2 second, T3 third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public String toString() {
            return "Triple{" + "first=" + first + ", second=" + second + ", third=" + third + '}';
        }
    }

    private static void demonstrateNestedGenerics() {
        System.out.println("\n=== Вложенные дженерики ===");

        // Список списков строк
        List<List<String>> listOfLists = new ArrayList<>();
        listOfLists.add(List.of("Apple", "Banana"));
        listOfLists.add(List.of("Cherry", "Date"));
        System.out.println("listOfLists: " + listOfLists);

        // Карта, где ключ — строка, а значение — список целых чисел
        Map<String, List<Integer>> mapOfLists = new HashMap<>();
        mapOfLists.put("Numbers", List.of(1, 2, 3));
        mapOfLists.put("Scores", List.of(85, 90, 78));
        System.out.println("mapOfLists: " + mapOfLists);

        // Сложный случай: список карт
        List<Map<String, Integer>> listOfMaps = new ArrayList<>();
        listOfMaps.add(Map.of("One", 1, "Two", 2));
        listOfMaps.add(Map.of("Three", 3, "Four", 4));
        System.out.println("listOfMaps: " + listOfMaps);

        // Итерация по вложенным структурам
        for (Map<String, Integer> map : listOfMaps) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }

    private static void demonstrateArraysOfGenerics() {
        System.out.println("\n=== Массивы дженериков ===");

        // ❌ Нельзя создать массив с конкретным дженерик-типом
        // List<String>[] badArray = new ArrayList<String>[10]; // ОШИБКА КОМПИЛЯЦИИ!

        // ✅ Можно создать массив с "сырым" типом (ArrayList[]), но это выдаст предупреждение
        @SuppressWarnings("unchecked") // Подавляем предупреждение о сыром типе
        List<String>[] rawArray = new ArrayList[3];
        System.out.println("rawArray (пока пуст): " + Arrays.toString(rawArray));

        // ✅ Создаём изменяемые списки и присваиваем их элементам массива
        // Это работает, потому что ArrayList - это подкласс List
        rawArray[0] = new ArrayList<>();
        rawArray[1] = new ArrayList<>();
        rawArray[2] = new ArrayList<>();

        // Заполняем списки
        rawArray[0].add("Hello");
        rawArray[1].add("World");
        rawArray[2].add("Java");

        System.out.println("Заполненный массив: " + Arrays.toString(rawArray));

        // ❌ Но нельзя добавить неправильный тип в элемент массива
        // rawArray[0].add(123); // ОШИБКА КОМПИЛЯЦИИ — List<String> не принимает int

        // ✅ Можно безопасно добавлять строки
        rawArray[0].add("New String");
        System.out.println("После добавления в первый список: " + rawArray[0]);

        // Создаём ещё один массив для демонстрации проблемы с List.of()
        @SuppressWarnings("unchecked")
        List<String>[] problematicArray = new ArrayList[2];
        // problematicArray[0] = List.of("A", "B"); // Это вызывает ArrayStoreException!
        // System.out.println("Это не выполнится из-за ошибки выше.");

        System.out.println("⚠️ Присваивание результатов List.of() элементу массива ArrayList[] вызывает ArrayStoreException.");
        System.out.println("✅ Всегда используйте изменяемые коллекции (new ArrayList<>()) при работе с массивами дженериков.");
    }

    private static void demonstrateGenericClassesWithMultipleParams() {
        System.out.println("\n=== Классы с несколькими параметрами ===");

        // Универсальный контейнер с двумя типами
        Container<String, Integer> container = new Container<>("Count", 42);
        System.out.println("container: " + container);

        // Получаем значения
        String name = container.getName();
        Integer value = container.getValue();
        System.out.println("name: " + name);
        System.out.println("value: " + value);

        // Изменяем значение
        container.setValue(100);
        System.out.println("Новое значение: " + container.getValue());

        // Контейнер с тремя типами
        TripleContainer<String, Integer, Boolean> tripleContainer =
                new TripleContainer<>("Status", 1, true);
        System.out.println("tripleContainer: " + tripleContainer);
    }

    // Универсальный контейнер с двумя параметрами
    static class Container<T1, T2> {
        private T1 name;
        private T2 value;

        public Container(T1 name, T2 value) {
            this.name = name;
            this.value = value;
        }

        public T1 getName() {
            return name;
        }

        public T2 getValue() {
            return value;
        }

        public void setValue(T2 value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Container{" + "name=" + name + ", value=" + value + '}';
        }
    }

    // Универсальный контейнер с тремя параметрами
    static class TripleContainer<T1, T2, T3> {
        private T1 first;
        private T2 second;
        private T3 third;

        public TripleContainer(T1 first, T2 second, T3 third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public String toString() {
            return "TripleContainer{" + "first=" + first + ", second=" + second + ", third=" + third + '}';
        }
    }

    private static void demonstratePracticalUseCase() {
        System.out.println("\n=== Практический пример: Управление проектами ===");

        // Создаём проект с задачами
        Project<String, List<Task>> project = new Project<>("MyProject", new ArrayList<>());

        // Добавляем задачи
        Task task1 = new Task("Design", "High");
        Task task2 = new Task("Code", "Medium");
        project.getTasks().add(task1);
        project.getTasks().add(task2);

        System.out.println("Проект: " + project.getName());
        System.out.println("Задачи:");
        for (Task task : project.getTasks()) {
            System.out.println("  " + task);
        }

        // Группируем задачи по приоритету
        Map<String, List<Task>> groupedByPriority = project.getTasks().stream()
                .collect(Collectors.groupingBy(Task::getPriority));
        System.out.println("Группировка по приоритету: " + groupedByPriority);
    }

    // Вспомогательный класс для проекта
    static class Project<T, U extends List<Task>> {
        private T name;
        private U tasks;

        public Project(T name, U tasks) {
            this.name = name;
            this.tasks = tasks;
        }

        public T getName() {
            return name;
        }

        public U getTasks() {
            return tasks;
        }

        @Override
        public String toString() {
            return "Project{" + "name='" + name + "', tasks=" + tasks + '}';
        }
    }

    // Вспомогательный класс для задачи
    static class Task {
        private String name;
        private String priority;

        public Task(String name, String priority) {
            this.name = name;
            this.priority = priority;
        }

        public String getName() {
            return name;
        }

        public String getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return "Task{" + "name='" + name + "', priority='" + priority + "'}";
        }
    }
}
