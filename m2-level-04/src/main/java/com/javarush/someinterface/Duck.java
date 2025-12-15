package com.javarush.someinterface;

public class Duck implements Flyable, Swimmable, Speakable{

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }

    @Override
    public void speak() {
        System.out.println("I'm speaking");
    }

    @Override
    public void swim() {
        System.out.println("I'm swiming");
    }
}
