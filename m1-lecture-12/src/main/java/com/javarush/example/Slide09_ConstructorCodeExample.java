package com.javarush.example;

/**
 * Пример, демонстрирующий, как следует писать код в конструкторе.
 * Основное правило: конструктор должен быть простым и содержать только инициализацию полей.
 */
public class Slide09_ConstructorCodeExample {

    // --- Поля класса ---
    private String name;
    private int age;
    private boolean isActive;

    // --- Конструкторы ---

    /**
     * Конструктор с параметрами.
     * Содержит только простую инициализацию полей.
     * Не содержит сложной логики или вызовов методов, которые могут выбрасывать исключения.
     *
     * @param name Имя пользователя
     * @param age  Возраст пользователя
     */
    public Slide09_ConstructorCodeExample(String name, int age) {
        // Простая инициализация полей
        this.name = name;
        this.age = age;
        this.isActive = true; // Устанавливаем значение по умолчанию

        // НЕ рекомендуется: сложная логика, проверки, вызовы методов
        // Например, вот так делать НЕЛЬЗЯ:
        // if (name == null || name.trim().isEmpty()) {
        //     throw new IllegalArgumentException("Имя не может быть пустым.");
        // }
        // Такие проверки лучше вынести в отдельный метод или использовать фабричный метод.
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
     * Метод для установки активности пользователя.
     * Этот метод может содержать сложную логику и обработку исключений.
     */
    public void setActive(boolean active) {
        this.isActive = active;
        // Здесь можно добавить сложную логику, например, запись в лог, отправку уведомления и т.д.
        System.out.println("Статус активности пользователя '" + name + "' изменён на: " + active);
    }

    /**
     * Выводит информацию о пользователе в консоль.
     */
    public void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Активен: " + (isActive ? "Да" : "Нет"));
        System.out.println("---");
    }

    /**
     * Метод main - точка входа в программу.
     * Демонстрирует создание объекта и использование методов.
     */
    public static void main(String[] args) {
        System.out.println("=== Создание объекта с помощью конструктора ===");

        // Создаем объект
        Slide09_ConstructorCodeExample user = new Slide09_ConstructorCodeExample("Анна", 25);
        System.out.println("\nОбъект 'user' создан. Информация:");
        user.displayInfo();

        // Изменяем статус активности с помощью метода (а не конструктора)
        user.setActive(false);
        System.out.println("\nПосле изменения статуса активности:");
        user.displayInfo();

        System.out.println("\n=== Демонстрация инициализации базового класса ===");
        System.out.println("В этом примере нет наследования, но принцип следующий:");
        System.out.println("- При создании объекта наследника сначала инициализируются поля базового класса.");
        System.out.println("- Затем вызывается конструктор базового класса.");
        System.out.println("- После этого инициализируются поля наследника и вызывается его конструктор.");
        System.out.println("Это обеспечивает корректное состояние объекта на всех уровнях иерархии.");
    }
}