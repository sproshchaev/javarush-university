package com.javarush.demo;

public class Car {

    private String brand;

    private int maxSpeed;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
