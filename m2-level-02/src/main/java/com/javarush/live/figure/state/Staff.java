package com.javarush.live.figure.state;

abstract class Staff {

    private String name;
    private int baseSalary;

    protected Staff(String name, int baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    abstract int bonus();

    protected String payslip() {
        return name + ": оклад " + baseSalary + ", премия " + bonus()
        + ", итого " + (baseSalary + bonus());
    }

}
