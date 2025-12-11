package com.javarush.abstractdemo2;

public class TransportDemo {
    public static void main(String[] args) {

        // Transport transport = new Transport(); Правило 1
        Transport car = new Car();
        Car car2 = new Car();
        Transport plane = new Plane();

        // Полиморфизм: Transport-переменная, но разные реализации
        Transport[] vehicles = {car, car2, plane};

        for (Transport vehicle : vehicles) {
            vehicle.move(); // каждый движется по своему
            vehicle.beep(); // каждый по своему шумит
        }

    }
}
