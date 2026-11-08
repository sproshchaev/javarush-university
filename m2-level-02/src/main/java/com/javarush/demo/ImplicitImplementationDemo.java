package com.javarush.demo;

public class ImplicitImplementationDemo {
    public static void main(String[] args) {
        Pet pet = new Puppy();
        System.out.println("Щенок говорит: " + pet.sound());
    }
}

abstract class Pet {
    abstract String sound();
}

class Doggy extends Pet {
    @Override
    String sound() {
        return "Гав";
    }
}

// Метода sound() здесь нет, он достался от Doggy
class Puppy extends Doggy {
}
