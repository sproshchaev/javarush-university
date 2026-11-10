package com.javarush.live.figure.lamp;

abstract class Lamp extends Device {

    @Override
    protected void turnOn() {
        System.out.println("Лампа включена!");
    }

}
