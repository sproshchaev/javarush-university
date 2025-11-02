package com.javarush.example;

/**
 * Простая реализация паттерна Singleton.
 * Демонстрирует:
 * - приватный конструктор (запрещает создание объектов извне)
 * - статическое поле INSTANCE (единственный экземпляр)
 * - публичный статический метод getInstance() (точка доступа)
 * - проверку уникальности объекта
 * - использование в коде
 */
public class Singleton {

    // Единственный экземпляр класса — создаётся при загрузке класса
    private static final Singleton INSTANCE = new Singleton();

    // Приватный конструктор — никто не может создать объект снаружи
    private Singleton() {
        System.out.println("Создан экземпляр Singleton");
    }

    // Публичный статический метод — глобальная точка доступа к экземпляру
    public static Singleton getInstance() {
        return INSTANCE;
    }

    // Метод для демонстрации работы
    public void showMessage() {
        System.out.println("Это сообщение от Singleton-экземпляра.");
    }

    // Пример поля — состояние синглтона
    private String message = "Я — единственный экземпляр";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}