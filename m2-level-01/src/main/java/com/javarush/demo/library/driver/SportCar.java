package com.javarush.demo.library.driver;

public class SportCar extends Automobile {

    @Override
    public String name() {
        return "Спорткар";
    }

    @Override
    public void pressGas() {
        System.out.println(name() + ": сотня за 3 сек");
    }

}
