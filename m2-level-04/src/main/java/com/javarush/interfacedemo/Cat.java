package com.javarush.interfacedemo;

public class Cat implements SoundMaker {

    @Override
    public void makeSound() {
        System.out.println("мяу!");
    }
}
