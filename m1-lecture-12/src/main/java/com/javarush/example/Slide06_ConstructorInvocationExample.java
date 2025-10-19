package com.javarush.example;

/**
 * Пример, демонстрирующий процесс вызова конструктора при создании объекта.
 * Показывает, что сначала создается объект, а затем вызывается конструктор.
 */
public class Slide06_ConstructorInvocationExample {

    // --- Поля класса ---
    private String name;
    private int age;
    private boolean isActive;

    // --- Конструкторы ---

    /**
     * Конструктор без параметров.
     * Устанавливает значения по умолчанию для полей.
     */
    public Slide06_ConstructorInvocationExample() {
        this.name = "Unknown";
        this.age = 0;
        this.isActive = false;
        System.out.println("Конструктор без параметров вызван. Имя: " + name + ", Возраст: " + age + ", Активен: " + isActive);
    }

    /**
     * Конструктор с параметрами.
     * Устанавливает значения полей на основе переданных параметров.
     *
     * @param name Имя пользователя
     * @param age  Возраст пользователя
     */
    public Slide06_ConstructorInvocationExample(String name, int age) {
        this.name = name;
        this.age = age;
        this.isActive = true; // По умолчанию активный
        System.out.println("Конструктор с параметрами вызван. Имя: " + name + ", Возраст: " + age + ", Активен: " + isActive);
    }

    // --- Публичные методы ---
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Выводит информацию об объекте в консоль.
     */
    public void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Активен: " + (isActive ? "Да" : "Нет"));
        System.out.println("---");
    }

    /**
     * Метод main - точка входа в программу.
     * Демонстрирует создание объектов и вызов конструкторов.
     */
    public static void main(String[] args) {
        System.out.println("=== Создание объекта с помощью new ===");

        // 1. Создаем объект с помощью конструктора по умолчанию
        System.out.println("\nШаг 1: Создание объекта 'user1' с помощью 'new Slide06_ConstructorInvocationExample()'...");
        Slide06_ConstructorInvocationExample user1 = new Slide06_ConstructorInvocationExample();
        System.out.println("Объект 'user1' создан. Информация:");
        user1.displayInfo();

        // 2. Создаем объект с помощью конструктора с параметрами
        System.out.println("\nШаг 2: Создание объекта 'user2' с помощью 'new Slide06_ConstructorInvocationExample(\"Анна\", 25)'...");
        Slide06_ConstructorInvocationExample user2 = new Slide06_ConstructorInvocationExample("Анна", 25);
        System.out.println("Объект 'user2' создан. Информация:");
        user2.displayInfo();

        // 3. Демонстрация того, что конструктор вызывается сразу после создания объекта
        System.out.println("\nШаг 3: Демонстрация порядка событий.");
        System.out.println("При выполнении строки:");
        System.out.println("    Slide06_ConstructorInvocationExample user3 = new Slide06_ConstructorInvocationExample(\"Борис\", 30);");
        System.out.println("Java-машина сначала создаст объект (выделит память, установит поля по умолчанию),");
        System.out.println("а затем вызовет конструктор, который установит нужные значения.");
        System.out.println("Это видно из вывода сообщения внутри конструктора выше.");
    }
}