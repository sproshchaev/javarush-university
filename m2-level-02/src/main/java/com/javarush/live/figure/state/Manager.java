package com.javarush.live.figure.state;

public class Manager extends Staff /*, Driver */ {

    protected Manager(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    int bonus() {
        return 20_000;
    }

}
