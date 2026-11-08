package com.javarush.demo.library.vehicle;

public class InheritanceDemo {

    public static void main(String[] args) {
        PassengerCar car = new PassengerCar();
        car.setBrand("Лада"); // поле родителя
        car.setDoors(4); // свое поле
        car.accelerate(60); // родительский метод
        car.accelerate(20);

        System.out.println(car);

        System.out.println("Дверей: " + car.getDoors());
        car.openTrunk(); // свой метод

    }

}
