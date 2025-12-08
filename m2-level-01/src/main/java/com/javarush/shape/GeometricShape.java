package com.javarush.shape;

// Абстрактный класс
abstract class GeometricShape {

    // Абстрактные методы - главные характеристки всех фигур
    abstract double calculateArea();
    abstract double calculateAreaPerimeter();

    void printInfo(){
        System.out.println("Area of this shape: " + calculateArea());
        System.out.println("Perimeter of this shape: " + calculateAreaPerimeter());
    }

}
