package com.javarush.abstractdemo2;

public class Plane extends Transport {

    @Override
    void move() {
        System.out.println("Plane fly");
    }

    @Override
    void beep() {
        System.out.println("Uuuuuuuu.....");
    }

}
