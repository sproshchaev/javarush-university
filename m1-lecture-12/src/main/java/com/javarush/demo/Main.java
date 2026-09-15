package com.javarush.demo;

public class Main {

    public static void main(String[] args) {
     // int a    = 1;
        Car car1 = new Car(); // это создание объекта/экз класса

        car1.setModel("Lada"); // вызов сеттеров
        car1.setMaxSpeed(180); //

        car1.printInfo(); // вызов метода

        // Создание объекта с иниц ч/з констр
        Car car2 = new Car("Tesla", 250);
        car2.printInfo();

        // 3-ий конструктор
        Car car3 = new Car("Bmw");
        car3.setMaxSpeed(250); //

        car3.printInfo();

        //
        Car car4 = new Car("Lada", 180);

        System.out.println(car1 == car4); // false
        System.out.println(car1.equals(car4)); // true

        System.out.println("car1 hash: " + car1.hashCode()); // 73177571
        System.out.println("car4 hash: " + car4.hashCode()); // 73177571

    }

}
