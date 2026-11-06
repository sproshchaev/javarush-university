package com.javarush.demo.library.vehicle;

/**
 * Родитель
 */
public class Vehicle {

    private String brand;
    private int speed;

    public void accelerate(int delta) {
        speed += delta;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", speed=" + speed +
                '}';
    }
}
