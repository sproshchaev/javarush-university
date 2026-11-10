package com.javarush.live.figure.state;

public class TemplateStateDemo {

    public static void main(String[] args) {
        Staff[] staff = {new Manager("Анна", 100_000),
                new Courier("Борис", 10_000, 120)
        };

        for (Staff person : staff) {
            System.out.println(person.payslip());
        }

    }

}
