package com.javarush.demo.library.driver;

public class Automobile {

    public String name() {
        return "Обычная машина";
    }

    public void steer(String direction) {
        System.out.println(name() + ": руль " + direction);
    }

    public void pressGas() {
        System.out.println(name() + ": плавно разгоняется");
    }

}
