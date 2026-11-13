package com.javarush.live;

public class SwimmableDemo {

    public static void main(String[] args) {

        // Массив интерфейсов
        Swimmable[] swimmers = {new Fish(), new Swimmer("Анна") /*, new Book()*/};

        for (Swimmable swimmer : swimmers) {
            swimmer.swim();
        }
    }

}

interface Swimmable {
    void swim();
}

class Fish implements Swimmable {

    @Override
    public void swim() {
        System.out.println("Рыба плавает");
    }
}

class Swimmer implements Swimmable {

    private final String name;

    Swimmer(String name) {
        this.name = name;
    }

    @Override
    public void swim() {
        System.out.println("Пловец " + name + " плывет кролем");
    }
}