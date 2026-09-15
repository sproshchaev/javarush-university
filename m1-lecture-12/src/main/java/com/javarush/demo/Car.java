package com.javarush.demo;

import java.util.Objects;

public class Car {

    // это поля класса (состояние класса)
    private String model;
    private int maxSpeed; // = 500;

    // конструктор(-ы) всегда здесь
    public Car() {
        // Это конструктор по умолчанию (можно не создавать)
    }

    // второй конструктор
    public Car(String model, int maxSpeed) {
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    // третий конструктор
    public Car(String model) {
        this.model = model;
    }

    // это методы класса
    public void printInfo() {
        System.out.println("Модель: " + model + " скорость " + maxSpeed);
    }

    // геттеры и сеттеры (это тоже часть методов)
    public void setModel(String model) {
        this.model = model;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    // переопределенные методы equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return maxSpeed == car.maxSpeed && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maxSpeed);
    }
}
