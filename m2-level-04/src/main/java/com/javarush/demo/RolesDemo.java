package com.javarush.demo;

public class RolesDemo {
    public static void main(String[] args) {
        CanDrive[] drivers = {new Automobile(), new Bicycle(), new Motorcycle()};
        for (CanDrive vehicle : drivers) {
            vehicle.drive();
        }

        CanCarryPeople[] carriers = {new Automobile(), new Motorcycle()};
        for (CanCarryPeople carrier : carriers) {
            System.out.println("Мест для людей: " + carrier.seats());
        }
    }
}

// Интерфейсы описывают способности
interface CanDrive {
    void drive();
}

interface CanCarryPeople {
    int seats();
}

class Automobile implements CanDrive, CanCarryPeople {
    @Override
    public void drive() {
        System.out.println("Машина едет");
    }

    @Override
    public int seats() {
        return 5;
    }
}

class Bicycle implements CanDrive {
    @Override
    public void drive() {
        System.out.println("Велосипед едет");
    }
}

class Motorcycle implements CanDrive, CanCarryPeople {
    @Override
    public void drive() {
        System.out.println("Мотоцикл едет");
    }

    @Override
    public int seats() {
        return 2;
    }
}
