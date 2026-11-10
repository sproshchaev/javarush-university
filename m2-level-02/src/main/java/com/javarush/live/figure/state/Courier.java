package com.javarush.live.figure.state;

public class Courier extends Staff {

    private int deliveries;

    protected Courier(String name, int baseSalary, int deliveries) {
        super(name, baseSalary);
        this.deliveries = deliveries;
    }

    @Override
    int bonus() {
        return deliveries * 100;
    }

}
