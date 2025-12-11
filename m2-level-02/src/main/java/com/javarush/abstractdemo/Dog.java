package com.javarush.abstractdemo;

// Класс-наследник
public class Dog extends Animal {

    // Поле name будет унаследовано от родителя

    public Dog(String name) {
        super(name); // super - обращение к конструктору родителя (абстрактного класса)
    }

    // Все наследники обязаны реализовать абстрактный метод по-своему (@Override - переопределение)
    @Override
    void makeSound() {
        System.out.println("I'm a dog");
    }
}
