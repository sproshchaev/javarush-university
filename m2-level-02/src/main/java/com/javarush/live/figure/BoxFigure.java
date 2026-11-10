package com.javarush.live.figure;

public class BoxFigure extends Figure {

    private double width;
    private double height;
    private String name;

    public BoxFigure(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public BoxFigure(double width, double height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public BoxFigure(String name, double width, double height) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    @Override
    protected double area() {
        return width * height;
    }
}
