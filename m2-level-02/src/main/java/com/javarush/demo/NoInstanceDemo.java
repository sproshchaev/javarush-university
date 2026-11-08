package com.javarush.demo;

public class NoInstanceDemo {
    public static void main(String[] args) {
        // Machine broken = new Machine();   // ошибка компиляции
        Machine machine = new WashingMachine();
        machine.start();
    }
}

abstract class Machine {
    abstract void start();
}

class WashingMachine extends Machine {
    @Override
    void start() {
        System.out.println("Стиральная машина: стирка началась");
    }
}
