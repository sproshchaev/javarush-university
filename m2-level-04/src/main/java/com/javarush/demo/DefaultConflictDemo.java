package com.javarush.demo;

public class DefaultConflictDemo {
    public static void main(String[] args) {
        new Smartphone().greet();
    }
}

interface Phone {
    default void greet() {
        System.out.println("Телефон: алло");
    }
}

interface Camera {
    default void greet() {
        System.out.println("Камера: улыбочку");
    }
}

// Два одинаковых метода по умолчанию: класс обязан решить сам
class Smartphone implements Phone, Camera {
    @Override
    public void greet() {
        Phone.super.greet();
        Camera.super.greet();
        System.out.println("Смартфон: умею и то и другое");
    }
}

// class CheapPhone implements Phone, Camera {
// }
