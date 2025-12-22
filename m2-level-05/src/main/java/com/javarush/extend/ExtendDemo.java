package com.javarush.extend;

public class ExtendDemo {

    static class Device {
        // Состояние
        String model;
        // Конструктор
        Device(String model) {
            this.model = model;
        }
        // Включение устройства
        void turnOn() {
            System.out.println(model + " is turning on");
        }
    }

    static class Phone extends Device {
        // Конструктор класса
        Phone(String model) {
            super(model); // вызов конструктора родителя
        }
        // Умеет звонить
        void call() {
            System.out.println(model + " is calling!");
        }
    }

    static class Laptop extends Device {
        Laptop(String model) {
            super(model);
        }
        void code() {
            System.out.println(model + " is code running!");
        }
    }

    // Generic-сервис с ограничениями
    static class RepairService<T extends Device> {
        private T device;

        public void setDevice(T device) {
            this.device = device;
        }

        public void diagnose() {
            System.out.println("Диагностика устройства ");
            device.turnOn(); // T = Device
            // device.call(); // T = Phone - компилятор не знает
        }

        public T getDevice() {
            return device;
        }
    }

    public static void main(String[] args) {
        System.out.println("1) Сервис принимает Phone");
        RepairService<Phone> phoneService = new RepairService<>();
        phoneService.setDevice(new Phone("iPhone"));

        System.out.println("2) Сервис принимает Laptop");
        RepairService<Laptop> laptopService = new RepairService<>();
        laptopService.setDevice(new Laptop("macBook"));

        // String не ext Device:
        // RepairService<String> stringService = new RepairService<>(); // String is not within bounds of type-variable T


    }

}
