package com.javarush.demo;

public class VoiceDemo {
    public static void main(String[] args) {
        Creature[] farm = {new Barker(), new Meower(), new Mooer()};
        for (Creature creature : farm) {
            creature.voice();   // один метод на всех
        }
    }
}

abstract class Creature {
    abstract void voice();
}

class Barker extends Creature {
    @Override
    void voice() {
        System.out.println("Собака: гав-гав");
    }
}

class Meower extends Creature {
    @Override
    void voice() {
        System.out.println("Кошка: мяу");
    }
}

class Mooer extends Creature {
    @Override
    void voice() {
        System.out.println("Корова: муу");
    }
}
