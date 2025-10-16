package com.javarush;

/**
 * Класс - наследник Animal
 */
public class Dog extends Animal {

    // Поля - состояния
    private String name;
    private int age;

    // Конструктор класса - без параметров (по умолчанию)
    public Dog() {
    }

    // Конструктор класса #2 - с параметрами определяющими состояние объекта
    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Геттер для поля name
    public String getName() {
        return name;
    }

    // Сеттер для поля name
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 20) {
            System.out.println("Ошибка: собаки столько не живут!");
        } else {
            this.age = age;
        }
    }

    // Методы - это действия
    // Переопределяем метод и помечаем его через @Override
    @Override
    public void makeSound() {
        System.out.println("Гав-гав!");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
