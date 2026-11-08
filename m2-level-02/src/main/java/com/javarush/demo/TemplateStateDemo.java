package com.javarush.demo;

public class TemplateStateDemo {
    public static void main(String[] args) {
        Staff[] staff = {new Manager("Анна", 100_000), new Courier("Борис", 50_000, 120)};
        for (Staff person : staff) {
            System.out.println(person.payslip());
        }
    }
}

// Заготовка: общее состояние (поля), общее поведение (payslip), одна дыра (bonus)
abstract class Staff {
    private String name;
    private int baseSalary;

    Staff(String name, int baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    abstract int bonus();

    String payslip() {
        return name + ": оклад " + baseSalary + ", премия " + bonus()
                + ", итого " + (baseSalary + bonus());
    }
}

class Manager extends Staff {
    Manager(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    int bonus() {
        return 20_000;
    }
}

class Courier extends Staff {
    private int deliveries;

    Courier(String name, int baseSalary, int deliveries) {
        super(name, baseSalary);
        this.deliveries = deliveries;
    }

    @Override
    int bonus() {
        return deliveries * 100;
    }
}
