package com.javarush.vehicle;

// Родительский базовый класс
public class Vehicle {

    protected String brand;
    protected int maxSpeed;

    public Vehicle(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    public void StartEngine() {
        System.out.println(brand + ": Starting engine ...");
    }

    public void pringInfo() {
        System.out.println("brand: " + brand + ", maxSpeed: " + maxSpeed);
    }

}
