package com.javarush.ivehicle;

public class Driver {

    public void driver(Vehicle vehicle) {
        System.out.println("Водитель:");
        vehicle.startEngine(); // Полморфный вызов
    }

}
