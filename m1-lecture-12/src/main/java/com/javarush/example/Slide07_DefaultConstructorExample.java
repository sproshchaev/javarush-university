package com.javarush.example;

/**
 * Пример, демонстрирующий работу с несколькими конструкторами и конструктором по умолчанию.
 */
public class Slide07_DefaultConstructorExample {

    // --- Поля класса ---
    private String name;
    private int age;
    private boolean isStudent;

    // --- Конструкторы ---

    /**
     * Конструктор по умолчанию.
     * Явно объявлен, потому что в классе есть другие конструкторы.
     * Инициализирует объект с базовыми значениями.
     */
    public Slide07_DefaultConstructorExample() {
        this.name = "Неизвестный";
        this.age = 0;
        this.isStudent = false;
        System.out.println("Вызван конструктор по умолчанию.");
    }

    /**
     * Конструктор с одним параметром.
     *
     * @param name Имя пользователя
     */
    public Slide07_DefaultConstructorExample(String name) {
        this.name = name;
        this.age = 18; // Установим значение по умолчанию
        this.isStudent = true;
        System.out.println("Вызван конструктор с именем: " + name);
    }

    /**
     * Конструктор со всеми параметрами.
     *
     * @param name Имя пользователя
     * @param age  Возраст пользователя
     * @param isStudent Признак студента
     */
    public Slide07_DefaultConstructorExample(String name, int age, boolean isStudent) {
        this.name = name;
        this.age = age;
        this.isStudent = isStudent;
        System.out.println("Вызван полный конструктор: " + name + ", " + age + ", " + isStudent);
    }

    // --- Публичные методы ---
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    /**
     * Выводит информацию о пользователе в консоль.
     */
    public void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Студент: " + (isStudent ? "Да" : "Нет"));
        System.out.println("---");
    }

    /**
     * Метод main - точка входа в программу.
     * Демонстрирует создание объектов с помощью разных конструкторов.
     */
    public static void main(String[] args) {
        System.out.println("=== Создание объектов с помощью разных конструкторов ===");

        // 1. Используем конструктор по умолчанию
        System.out.println("\nШаг 1: Создание объекта 'user1' с помощью конструктора по умолчанию...");
        Slide07_DefaultConstructorExample user1 = new Slide07_DefaultConstructorExample();
        user1.displayInfo();

        // 2. Используем конструктор с одним параметром
        System.out.println("\nШаг 2: Создание объекта 'user2' с помощью конструктора с именем...");
        Slide07_DefaultConstructorExample user2 = new Slide07_DefaultConstructorExample("Анна");
        user2.displayInfo();

        // 3. Используем конструктор со всеми параметрами
        System.out.println("\nШаг 3: Создание объекта 'user3' с помощью полного конструктора...");
        Slide07_DefaultConstructorExample user3 = new Slide07_DefaultConstructorExample("Борис", 25, true);
        user3.displayInfo();

        // 4. Комментарий о том, что конструктор по умолчанию был добавлен вручную
        System.out.println("\nШаг 4: Обратите внимание!");
        System.out.println("В этом классе есть несколько конструкторов, поэтому компилятор");
        System.out.println("НЕ добавил конструктор по умолчанию автоматически.");
        System.out.println("Мы добавили его вручную, чтобы иметь возможность создавать объекты");
        System.out.println("без передачи параметров (как user1).");
    }
}