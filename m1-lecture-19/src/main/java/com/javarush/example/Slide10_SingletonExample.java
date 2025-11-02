package com.javarush.example;


public class Slide10_SingletonExample {

    public static void main(String[] args) {
        System.out.println("=== Простая реализация синглтона ===");

        // Получаем экземпляр через getInstance()
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        // Проверяем, что это один и тот же объект
        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2)); // true

        // Вызов метода
        singleton1.showMessage(); // Это сообщение от Singleton-экземпляра.

        // Изменяем состояние
        singleton1.setMessage("Новое сообщение от синглтона!");

        // Читаем состояние через другой объект — оно совпадает!
        System.out.println("Сообщение через singleton2: " + singleton2.getMessage());

        System.out.println("\n=== Попытка создать новый объект (не получится!) ===");
        try {
            // Singleton newSingleton = new Singleton(); // Не скомпилируется — конструктор приватный
            System.out.println("Не удалось создать объект — конструктор закрыт.");
        } catch (Exception e) {
            System.out.println("Ошибка при создании: " + e.getMessage());
        }
    }
}