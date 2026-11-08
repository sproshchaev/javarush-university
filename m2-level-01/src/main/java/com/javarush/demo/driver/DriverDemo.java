package com.javarush.demo.driver;

public class DriverDemo {

    public static void main(String[] args) {

        Driver driver = new Driver();
        driver.drive(new Automobile());
        driver.drive(new SportCar());
        driver.drive(new Truck());

    }

}
