package com.javarush.ivehicle;

public class Truck implements Vehicle{
    @Override
    public void startEngine() {
        System.out.println("Truck starts engine ...");
    }
}
