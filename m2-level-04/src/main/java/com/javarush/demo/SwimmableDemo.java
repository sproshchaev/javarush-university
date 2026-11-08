package com.javarush.demo;

public class SwimmableDemo {
    public static void main(String[] args) {
        Swimmable[] swimmers = {new Fish(), new Swimmer("Анна")};
        for (Swimmable swimmer : swimmers) {
            swimmer.swim();
        }
    }
}

interface Swimmable {
    public void swim();
}

class Fish implements Swimmable {
    @Override
    public void swim() {
        System.out.println("Рыба плывет, виляя хвостом");
    }
}

class Swimmer implements Swimmable {
    private String name;

    Swimmer(String name) {
        this.name = name;
    }

    @Override
    public void swim() {
        System.out.println("Пловец " + name + " плывет кролем");
    }
}
