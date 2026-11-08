package com.javarush.demo.library.driver;

public class Driver {

    // Водитель знает только общий интерфейс машины
    public void drive(Automobile car) {
        car.steer("налево");
        car.pressGas();
    }

}
