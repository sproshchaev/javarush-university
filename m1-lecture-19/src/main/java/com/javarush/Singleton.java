package com.javarush;

public class Singleton {

    // Единственный экземпляр
    private static final Singleton INSTANCE = new Singleton();

    // Приватный конструктор
    private Singleton() {}

    // Точка доступа - публичный статический метод
    public static Singleton getInstance() {
        return INSTANCE;
    }

    // Публичный метод
    public void sayHello() {
        System.out.println("Hello, Singleton!");
    }

}
