package com.javarush.ivehicle;

public class Car implements Vehicle {
    @Override
    public void startEngine() {
        System.out.println("Car starts engine ...");
    }
}
