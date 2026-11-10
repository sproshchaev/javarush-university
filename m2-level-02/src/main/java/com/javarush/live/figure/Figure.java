package com.javarush.live.figure;

abstract class Figure {

    protected abstract double area();

    protected String describe() {
        return getClass().getSimpleName() + ", площадь "
                + Math.round(area() * 100)/ 100.0;
    }

    @Override
    public String toString() {
        return "Фигура " + getClass().getSimpleName();
    }
}
