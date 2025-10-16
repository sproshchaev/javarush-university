package com.javarush;

/**
 * JavaRush-University
 */
public class Main2 {


    public static void main(String[] args) {

        //  Нельзя создать экземпляр от абстрактного класса!
        // Animal animal = new Animal(); // java: com.javarush.Animal is abstract; cannot be instantiated

        Dog dog = new Dog();
        // dog.name = "Шарик";
        // dog.age = 300;

        dog.setName("Шарик");
        // dog.setAge(300);
        dog.setAge(3);

        dog.makeSound();
        System.out.println(dog.toString());
        System.out.println(dog);

        // Создание через конструктор ("по ГОСТУ-у")
        Dog dog2 = new Dog("Бобик", 5);
        System.out.println("Кличка: " + dog2.getName() + " возраст " + dog2.getAge());
        System.out.println(dog2);

        // Приведение: вверх по иерархии (upcasting) - оно безопасно
        Dog dog3 = new Dog();
        Animal animal = dog3;

        // Приведение: вниз по иерархии (downcasting)
        Animal animal2 = new Dog();
        Dog dog4 = (Dog) animal2; // явное приведение

        // Animal dog5 = new Animal(); - абстрактный класс
        Animal dog5 = new Dog(); // - можем создавать: Animal -> Dog
    }


}
