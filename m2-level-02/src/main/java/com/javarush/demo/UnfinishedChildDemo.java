package com.javarush.demo;

public class UnfinishedChildDemo {
    public static void main(String[] args) {
        Device lamp = new DeskLamp();
        lamp.turnOn();
        lamp.turnOff();
    }
}

abstract class Device {
    abstract void turnOn();

    abstract void turnOff();
}

// Реализован только один метод из двух, поэтому класс тоже abstract
abstract class Lamp extends Device {
    @Override
    void turnOn() {
        System.out.println("Лампа включена");
    }
}

// Второй метод реализован здесь, класс обычный
class DeskLamp extends Lamp {
    @Override
    void turnOff() {
        System.out.println("Лампа выключена");
    }
}

// class BrokenLamp extends Device {
//     @Override
//     void turnOn() {
//         System.out.println("Включаюсь");
//     }
// }
