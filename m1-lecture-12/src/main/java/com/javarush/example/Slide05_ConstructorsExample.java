package com.javarush.example;

/**
 * Пример, демонстрирующий использование конструкторов в Java.
 * Конструкторы инициализируют объект при его создании.
 */
public class Slide05_ConstructorsExample {

    // --- Поля класса ---
    private String modelName;
    private int maxSpeed;
    private boolean isElectric;

    // --- Конструкторы ---

    /**
     * Конструктор по умолчанию (без параметров).
     * Инициализирует объект с базовыми значениями.
     */
    public Slide05_ConstructorsExample() {
        this.modelName = "Unknown Model";
        this.maxSpeed = 100;
        this.isElectric = false;
    }

    /**
     * Конструктор с одним параметром.
     * Инициализирует только имя модели, остальные поля - по умолчанию.
     *
     * @param modelName Название модели автомобиля
     */
    public Slide05_ConstructorsExample(String modelName) {
        this.modelName = modelName;
        this.maxSpeed = 120; // Установим значение по умолчанию
        this.isElectric = false;
    }

    /**
     * Конструктор с двумя параметрами.
     * Инициализирует имя модели и максимальную скорость.
     *
     * @param modelName Название модели автомобиля
     * @param maxSpeed Максимальная скорость автомобиля
     */
    public Slide05_ConstructorsExample(String modelName, int maxSpeed) {
        this.modelName = modelName;
        this.maxSpeed = maxSpeed;
        this.isElectric = false;
    }

    /**
     * Конструктор с тремя параметрами.
     * Полная инициализация всех полей объекта.
     *
     * @param modelName Название модели автомобиля
     * @param maxSpeed Максимальная скорость автомобиля
     * @param isElectric Признак того, является ли автомобиль электрическим
     */
    public Slide05_ConstructorsExample(String modelName, int maxSpeed, boolean isElectric) {
        this.modelName = modelName;
        this.maxSpeed = maxSpeed;
        this.isElectric = isElectric;
    }

    // --- Публичные методы (геттеры) ---
    public String getModelName() {
        return modelName;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public boolean isElectric() {
        return isElectric;
    }

    /**
     * Выводит информацию об автомобиле в консоль.
     */
    public void displayInfo() {
        System.out.println("Модель: " + modelName);
        System.out.println("Макс. скорость: " + maxSpeed + " км/ч");
        System.out.println("Электрический: " + (isElectric ? "Да" : "Нет"));
        System.out.println("---");
    }

    /**
     * Метод main - точка входа в программу.
     * Демонстрирует создание объектов с помощью разных конструкторов.
     */
    public static void main(String[] args) {
        // Создаем объекты с помощью разных конструкторов

        // 1. Используем конструктор по умолчанию
        Slide05_ConstructorsExample car1 = new Slide05_ConstructorsExample();
        System.out.println("Автомобиль 1 (конструктор по умолчанию):");
        car1.displayInfo();

        // 2. Используем конструктор с одним параметром
        Slide05_ConstructorsExample car2 = new Slide05_ConstructorsExample("Toyota Corolla");
        System.out.println("Автомобиль 2 (конструктор с именем):");
        car2.displayInfo();

        // 3. Используем конструктор с двумя параметрами
        Slide05_ConstructorsExample car3 = new Slide05_ConstructorsExample("Ford Mustang", 250);
        System.out.println("Автомобиль 3 (конструктор с именем и скоростью):");
        car3.displayInfo();

        // 4. Используем конструктор со всеми параметрами
        Slide05_ConstructorsExample car4 = new Slide05_ConstructorsExample("Tesla Model S", 260, true);
        System.out.println("Автомобиль 4 (полный конструктор):");
        car4.displayInfo();
    }
}