package com.javarush.demo;

public class StaticCounterDemo {

    public static void main(String[] args) {

        System.out.println("Создано котов: " + Cat.count);

        Cat cat1 = new Cat("cat1");
        Cat cat2 = new Cat("cat2");
        Cat cat3 = new Cat("cat3");

        System.out.println("Имена: ");
        System.out.println(" " + cat1.name);
        System.out.println(" " + cat2.name);
        System.out.println(" " + cat3.name);

        System.out.println("Создано котов: " + Cat.count);

    }

}

// Второй класс
class Cat {
    static int count = 0; // 1 переменная на весь класс
    String name; // своя переменная на каждый объект

    Cat(String name) {
        this.name = name;
        count++; // Увеличиваем счетчик под каждый экземпляр класса
    }

}
