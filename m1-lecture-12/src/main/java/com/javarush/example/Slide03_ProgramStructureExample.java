package com.javarush.example;

/**
 * Пример, демонстрирующий основные концепции устройства Java-программы:
 * классы, объекты, поля и методы.
 */
public class Slide03_ProgramStructureExample {

    public static void main(String[] args) {
        // Создаем объекты класса Car на основе его "чертежа"
        Car car1 = new Car();
        Car car2 = new Car();

        // Заполняем поля объектов (устанавливаем их состояние)
        car1.brand = "Toyota";
        car1.model = "Corolla";
        car1.year = 2020;
        car1.isRunning = false;

        car2.brand = "Ford";
        car2.model = "Mustang";
        car2.year = 2022;
        car2.isRunning = true;

        // Вызываем методы объектов (заставляем их действовать)
        System.out.println("Информация о машине 1:");
        car1.displayInfo(); // Метод работает с полями объекта car1

        System.out.println("\nИнформация о машине 2:");
        car2.displayInfo(); // Метод работает с полями объекта car2

        // Демонстрация того, что объекты независимы
        car1.startEngine(); // car1 запускает двигатель
        System.out.println("\nПосле запуска двигателя у машины 1:");
        car1.displayInfo(); // isRunning теперь true

        System.out.println("\nСостояние машины 2 не изменилось:");
        car2.displayInfo(); // isRunning всё ещё true (не менялось)
    }
}

/**
 * Класс Car - это "чертёж" для создания объектов автомобилей.
 * Он определяет, какие данные (поля) и методы будут у каждого объекта.
 */
class Car {
    // Поля класса (данные объекта)
    String brand;   // Марка автомобиля
    String model;   // Модель автомобиля
    int year;       // Год выпуска
    boolean isRunning; // Признак, работает ли двигатель

    // Метод класса (функция работы с данными)
    void displayInfo() {
        System.out.println("Марка: " + brand);
        System.out.println("Модель: " + model);
        System.out.println("Год: " + year);
        System.out.println("Двигатель работает: " + isRunning);
    }

    // Метод класса (функция работы с данными)
    void startEngine() {
        isRunning = true;
        System.out.println(brand + " " + model + " завелась!");
    }
}