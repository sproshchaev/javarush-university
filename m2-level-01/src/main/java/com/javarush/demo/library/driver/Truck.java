package com.javarush.demo.library.driver;

public class Truck extends Automobile {

    @Override
    public String name() {
        return "Грузовик";
    }

    public void pressGas() {
        System.out.println(name() + ": медленно трогается");
    }

}
