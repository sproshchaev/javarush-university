package com.javarush.vehicle;

// Класс-наследник
public class Car extends Vehicle {

    private int doorsCount;

    public Car(String brand, int maxSpeed, int doorsCount) {
        super(brand, maxSpeed); // вызов конструктора родителя
        this.doorsCount = doorsCount;
    }

    // Специфический метод для наследника
    public void honk() {
        System.out.println(brand + ": Beep!");
    }

    @Override
    public void pringInfo() {
        super.pringInfo(); // Метод родителя
        System.out.println("doorsCount: " + doorsCount); // количество дверей наследника
    }
}
