package com.javarush.live;

public class DefaultMethodDemo {

    public static void main(String[] args) {

        Car[] cars = {new RegularCar(), new UnusualCar()};

        for (Car car : cars) {
            car.gas();
            car.brake();
        }

    }

}

interface Car {
    default void gas() {
        System.out.println("Газуем!");
    }

    void brake();
}

class RegularCar implements Car {
    public void brake() {
        System.out.println("Машина тормозит");
    }
}

class UnusualCar implements Car {

    @Override
    public void gas() {
        System.out.println("Эта машина газует по другому!");
    }

    @Override
    public void brake() {
        System.out.println("Необычная машина тормозит!");
    }
}