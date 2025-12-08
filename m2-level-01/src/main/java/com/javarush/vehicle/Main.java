package com.javarush.vehicle;

public class Main {
    public static void main(String[] args) {
        // Создаем объект родителя
        Vehicle vehicle = new Vehicle("Грузовик", 120);
        vehicle.StartEngine();
        vehicle.pringInfo();

        // Создаем объект класса-наследника
        Car car = new Car("Тойота", 200, 4);
        car.StartEngine(); // метод унаследован у Vehicle
        car.honk(); // свой метод наследника
        car.pringInfo(); // переопределенный метод
    }
}
