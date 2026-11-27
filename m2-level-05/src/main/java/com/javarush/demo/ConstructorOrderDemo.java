package com.javarush.demo;

public class ConstructorOrderDemo {

    public static void main(String[] args) {
        System.out.println("new Cat()");
        new Cat();
    }

    private static class Animal {

        private String type = trace("Поле Animal.type", "животное");

        {
            System.out.println("Блок Animal, type = " + type);
        }

        Animal() {
            System.out.println("Тело Animal()");
        }
    }

    private static class Cat extends Animal {
        private String name = trace("Поле Cat.name", "Безымянный");

        {
            System.out.println("Блок Cat, name = " + name);
        }

        Cat() {
            this("Барсик");
            System.out.println("Тело Cat()");
        }

        Cat(String name) {
            this.name = name;
            System.out.println("Тело Cat(String), name = " + this.name);
        }
    }

    private static String trace(String label, String value) {
        System.out.println(label + " = " + value);
        return value;
    }


}
