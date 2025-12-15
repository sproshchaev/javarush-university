package com.javarush.interfacedemo;

public class Bell implements SoundMaker {
    @Override
    public void makeSound() {
        System.out.println("Дзинь-дзинь!");
    }
}
