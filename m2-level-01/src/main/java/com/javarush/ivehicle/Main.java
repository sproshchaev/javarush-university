package com.javarush.ivehicle;

// "Полиморфизм = один интерфейс, множество реализаций!"
public class Main {

    public static void main(String[] args) {
        Driver driver = new Driver();
        Car car = new Car();
        Truck truck = new Truck();

        driver.driver(car);   // Car starts engine ...
        driver.driver(truck); // Truck starts engine ...

    }

}
