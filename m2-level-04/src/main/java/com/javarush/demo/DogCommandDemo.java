package com.javarush.demo;

public class DogCommandDemo {
    public static void main(String[] args) {
        Trainer trainer = new Trainer();
        trainer.train(new Shepherd());
        trainer.train(new Poodle());
        // trainer.train(new Kitten());   // ошибка компиляции
    }
}

// Голосовой интерфейс управления собакой
interface DogCommands {
    void sit();

    void lie();
}

class Shepherd implements DogCommands {
    @Override
    public void sit() {
        System.out.println("Овчарка села");
    }

    @Override
    public void lie() {
        System.out.println("Овчарка легла");
    }
}

class Poodle implements DogCommands {
    @Override
    public void sit() {
        System.out.println("Пудель сел и ждет лакомство");
    }

    @Override
    public void lie() {
        System.out.println("Пудель лег на коврик");
    }
}

class Kitten {
    void purr() {
        System.out.println("Котенок мурлычет");
    }
}

// Дрессировщик знает только команды
class Trainer {
    void train(DogCommands dog) {
        dog.sit();
        dog.lie();
    }
}
