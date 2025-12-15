package com.javarush.drawable;

public class DrawDemo {

    public static void main(String[] args) {

        Circle circle = new Circle("Красный", 5.0);

        // Используем как Shape (абстрактный класс)
        System.out.println("Площадь: " + circle.getArea()); // Площадь: 78.53981633974483
        circle.setColor("Синий"); // Цвет изменился на Синий

        // Нельзя создать экземпляр от абстрактного класса
        // Shape shape = new Shape("Зеленый"); // Shape is abstract; cannot be instantiated

        // Нельзя создать экзкмпляр от интерфейса
        // Drawable drawable = new Drawable(); // Drawable is abstract; cannot be instantiated

        Shape shapeRef = circle; // тк extends Shape ...
        Drawable drawableRef = circle; // тк implements Drawable ...

        shapeRef.setColor("Желтый"); // Цвет изменился на Желтый
        drawableRef.draw(); // Рисуем круг, цвет Желтый, радиус=5.0

    }

}
