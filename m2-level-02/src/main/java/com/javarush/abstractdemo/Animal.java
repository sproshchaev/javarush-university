package com.javarush.abstractdemo;

// Абстрактный класс
abstract class Animal {

    // Состояния
    private String name;

    // Конструктор
    public Animal(String name) {
        this.name = name;
    }

    // Абстрактный метод (метод без тела)
    abstract void makeSound();

    // Обычный метод (не абстрактный)
    public void sleep() {
        System.out.println(name + " Sleeping");
    }

    // Геттер будет доступен для наследников для поля Name
    public String getName() {
        return name;
    }
}
