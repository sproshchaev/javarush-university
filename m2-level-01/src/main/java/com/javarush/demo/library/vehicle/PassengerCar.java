package com.javarush.demo.library.vehicle;

/**
 * Наследник
 */
public class PassengerCar extends Vehicle {
    private int doors;

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public void openTrunk() {
        System.out.println(getBrand() + ": багажник открыт");
    }

}
