package com.javarush.abstractdemo2;

abstract class Transport {

    abstract void move();

    void beep() {
        System.out.println("beep");
    }

}
