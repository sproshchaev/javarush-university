package com.javarush.abstractdemo;

public class AbstractDemo {
    public static void main(String[] args) {

        // Animal animal = new Animal("Name"); // 'Animal' is abstract; cannot be instantiated

        Animal dog = new Dog("Dog");
        dog.makeSound(); // I'm a dog
        dog.sleep(); // Dog Sleeping
        System.out.println(dog.getName()); // Dog

    }
}
