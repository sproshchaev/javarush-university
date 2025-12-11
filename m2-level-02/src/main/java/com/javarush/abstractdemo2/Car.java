package com.javarush.abstractdemo2;

public class Car extends Transport {
    @Override
    void move() {
        System.out.println("Car move into road");
    }
}
