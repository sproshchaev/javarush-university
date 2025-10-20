package com.javarush;

import java.util.Objects;

public class Color {

    private String color;

    public Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Color color1 = (Color) o;
        return Objects.equals(color, color1.color);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(color);
    }

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                '}';
    }
}
