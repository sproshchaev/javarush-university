package com.javarush.demo;

public class AbstractShapeDemo {
    public static void main(String[] args) {
        Figure[] figures = {new RoundFigure(2), new BoxFigure(3, 4)};
        for (Figure figure : figures) {
            System.out.println(figure.describe());
        }
    }
}

// Абстрактный класс: метод area() объявлен, но не реализован
abstract class Figure {
    abstract double area();

    String describe() {
        return getClass().getSimpleName() + ", площадь " + Math.round(area() * 100) / 100.0;
    }
}

class RoundFigure extends Figure {
    private double radius;

    RoundFigure(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class BoxFigure extends Figure {
    private double width;
    private double height;

    BoxFigure(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}
