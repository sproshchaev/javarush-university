package com.javarush.drawable;

// Класс, который наследует и реализует
public class Circle extends Shape implements Drawable {

    private double radius;

    public Circle(String color, double radius) {
        super(color); // вызов конструктора родителя (абстрактный класс Shape)
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Рисуем круг, цвет " + color + ", радиус=" + radius);

    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
