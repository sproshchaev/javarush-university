package com.javarush.demo;

public class DuckDemo {
    public static void main(String[] args) {
        Duck duck = new Duck();
        duck.fly();
        duck.swim();

        FlyingBird flyer = duck;
        Waterfowl swimmer = duck;
        flyer.fly();
        swimmer.swim();
    }
}

interface FlyingBird {
    void fly();
}

interface Waterfowl {
    void swim();
}

class Duck implements FlyingBird, Waterfowl {
    //методы обоих интерфейсов легко объединяются в одном классе
    @Override
    public void fly() {
        System.out.println("Летим!");
    }

    @Override
    public void swim() {
        System.out.println("Плывем!");
    }
}
