package com.javarush.drawable;

import java.util.SortedMap;

// Абстрактный класс - общая сущность
abstract class Shape {

    protected String color; // поле с состоянием

    // Конструктор 1
    public Shape() {
    }

    // Конструктор 2
    public Shape(String color) {
        this.color = color;
    }

    // Абстрактный метод
    public abstract double getArea();

    // Метод с реализацией
    public void setColor(String color) {
        this.color = color;
        System.out.println("Цвет изменился на " + color);
    }

}
