package com.javarush.example;

/**
 * Пример, демонстрирующий порядок инициализации переменных в Java.
 * Показывает, что инициализация при объявлении происходит до вызова конструктора.
 */
public class Slide08_VariableInitializationExample {

    // --- Поля класса ---
    // Инициализация при объявлении (происходит ДО вызова конструктора)
    private String name = "Default Name";
    private int age = 18;
    private boolean isActive = true;

    // Поле, которое будет инициализировано в конструкторе
    private double salary;

    // Поле, которое зависит от другого поля (показывает, что можно использовать уже инициализированные поля)
    private double annualSalary = salary * 12; // Внимание: здесь salary еще 0.0!

    // --- Конструкторы ---

    /**
     * Конструктор без параметров.
     * Инициализирует только поле salary.
     */
    public Slide08_VariableInitializationExample() {
        this.salary = 50000.0;
        System.out.println("Конструктор вызван. Salary установлен в: " + salary);
        // Обратите внимание: annualSalary уже был инициализирован (как 0.0 * 12 = 0.0)
        // Поэтому его нужно пересчитать вручную, если это необходимо
        this.annualSalary = this.salary * 12;
        System.out.println("AnnualSalary пересчитан в конструкторе: " + annualSalary);
    }

    /**
     * Конструктор с параметрами.
     *
     * @param name Имя пользователя
     * @param age  Возраст пользователя
     * @param salary Зарплата пользователя
     */
    public Slide08_VariableInitializationExample(String name, int age, double salary) {
        // Поля, инициализированные при объявлении, уже имеют свои значения!
        // Здесь мы можем их переопределить
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.isActive = false; // Переопределяем значение

        System.out.println("Конструктор с параметрами вызван.");
        System.out.println("Имя: " + this.name);
        System.out.println("Возраст: " + this.age);
        System.out.println("Зарплата: " + this.salary);
        System.out.println("Активен: " + this.isActive);

        // annualSalary уже был инициализирован при создании объекта (как 0.0)
        // Пересчитываем его
        this.annualSalary = this.salary * 12;
        System.out.println("Годовая зарплата: " + this.annualSalary);
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

    public double getSalary() {
        return salary;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    /**
     * Выводит информацию об объекте в консоль.
     */
    public void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Активен: " + (isActive ? "Да" : "Нет"));
        System.out.println("Зарплата: " + salary);
        System.out.println("Годовая зарплата: " + annualSalary);
        System.out.println("---");
    }

    /**
     * Метод main - точка входа в программу.
     * Демонстрирует создание объектов и порядок инициализации.
     */
    public static void main(String[] args) {
        System.out.println("=== Создание объекта с помощью конструктора по умолчанию ===");

        // Создаем объект
        Slide08_VariableInitializationExample user1 = new Slide08_VariableInitializationExample();
        System.out.println("\nОбъект 'user1' создан. Информация:");
        user1.displayInfo();

        System.out.println("\n=== Создание объекта с помощью конструктора с параметрами ===");

        // Создаем объект с параметрами
        Slide08_VariableInitializationExample user2 = new Slide08_VariableInitializationExample("Анна", 25, 60000.0);
        System.out.println("\nОбъект 'user2' создан. Информация:");
        user2.displayInfo();

        // Демонстрация того, что инициализация при объявлении происходит до конструктора
        System.out.println("\n=== Демонстрация порядка инициализации ===");
        System.out.println("Поле 'annualSalary' было инициализировано при объявлении как 'salary * 12'.");
        System.out.println("На момент инициализации 'salary' имел значение по умолчанию (0.0).");
        System.out.println("Поэтому 'annualSalary' изначально равен 0.0, даже если 'salary' потом меняется.");
        System.out.println("В конструкторе мы пересчитываем 'annualSalary' вручную.");
    }
}