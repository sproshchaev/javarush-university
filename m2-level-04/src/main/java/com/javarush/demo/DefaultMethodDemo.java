package com.javarush.demo;

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
    // Метод по умолчанию: тело прямо в интерфейсе
    default void gas() {
        System.out.println("Газуем!");
    }

    void brake();
}

// gas() не переопределяем, берем метод по умолчанию
class RegularCar implements Car {
    @Override
    public void brake() {
        System.out.println("Обычная машина тормозит");
    }
}

//Дефолтные методы интерфейсов можно переопределять.
class UnusualCar implements Car {
    @Override
    public void gas() {
        System.out.println("Эта машина газует по-другому!");
    }

    @Override
    public void brake() {
        System.out.println("Необычная машина тормозит");
    }
}
