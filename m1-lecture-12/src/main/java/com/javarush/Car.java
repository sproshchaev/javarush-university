package com.javarush;

import java.util.Objects;

public class Car {

    // Состояния (поля) - инициализация полей происходит до выполнения конструктора
    private long id;         // id - инициализруется #1
    private Color color;     // цвет - инициализруется #2
    private Brand brand;     // марка автомобиля - инициализруется #3
    private int countEngine; // счетчик моточасов = 0 - инициализруется #4
    private int distance;    // пробег автомобиля - инициализруется #5

    // Конструктор - имя совпадает с именем класса и пишется с большой буквы!
    //             - ничего не возвращает, даже void
    //             - модификатор "public"
    // Best practice - не писать логику в конструкторе!
    public Car(long id, Color color, Brand brand) {
        this.id = id;
        this.color = color;
        this.brand = brand;
    }

    // Второй конструктор
    public Car(long id) {
        this.id = id;
    }

    // Третий конструктор
    public Car(long id, int countEngine) {
        this.id = id;
        this.countEngine = countEngine;
    }

    // Методы - геттеры (получаем значение состояния объекта)
    // и сеттеры (устанавливаем значение состояния объекта)
    public long getId() {
        return id;
    }


    // Чтобы не изменять свойство id
    //    public void setId(long id) {
    //        this.id = id;
    //    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Brand getBrand() {
        return brand;
    }

//    public void setBrand(String brand) {
//        this.brand = brand;
//    }

    // Метод запускающий автомобиль
    // Основная логика класса - в методах!
    public void runEngine() {
        countEngine = countEngine + 1;
        System.out.println("Двигатель запущен! Счетчик числа запусков: " + countEngine);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id
                && countEngine == car.countEngine
                && distance == car.distance
                && Objects.equals(color, car.color)
                && Objects.equals(brand, car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, brand, countEngine, distance);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", color=" + color +
                ", brand='" + brand + '\'' +
                '}';
    }
}
