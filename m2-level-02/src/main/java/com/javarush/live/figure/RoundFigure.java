package com.javarush.live.figure;

public class RoundFigure extends Figure {

    private double radius;

    public RoundFigure(double radius) {
        this.radius = radius;
    }

    @Override
    protected double area() {
        return Math.PI * radius * radius;
    }
}
