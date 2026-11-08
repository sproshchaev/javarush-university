package com.javarush.demo.driver;

public class Driver {

    // Водитель знает только общий интерфейс машины
    public void drive(Automobile car) {
        car.steer("налево");
        car.pressGas();
    }

}
