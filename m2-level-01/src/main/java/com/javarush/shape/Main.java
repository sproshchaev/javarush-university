package com.javarush.shape;

public class Main {
    public static void main(String[] args) {

        GeometricShape circle = new Circle(5.0);
        GeometricShape rectangle = new Rectangle(3.0, 6.0);

        circle.printInfo();
        rectangle.printInfo();
    }
}
