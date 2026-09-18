package com.javarush.demo;

public class StaticNestedClassDemo {

    public static void main(String[] args) {

        Car.Engine petrol = new Car.Engine(150);
        Car.Engine electro = new Car.Engine(90);

        petrol.start();
        electro.start();

        Car.Engine.printInfo();

        System.out.println("Всего двигателей: " + Car.Engine.enginesCreated);

    }

}

class Car {

    public static class Engine {
        public static int enginesCreated = 0;
        int power;

        public Engine(int power) {
            enginesCreated++;
            this.power = power;
        }

        void start() {
            System.out.println("Двигатель " + power + " лс запущен");
        }

        public static void printInfo() {
            System.out.println("Engine - статический вложенный класс внутри Car");
        }

    }

}
