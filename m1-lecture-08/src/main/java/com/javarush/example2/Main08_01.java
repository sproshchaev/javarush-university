package com.javarush.example2;

import com.javarush.Auto;

public class Main08_01 {

    // static и не static

    public static void main(String[] args) {

        // Вызов статического метода: Имя_класса.имя_метода
        Main08_01.staticMethod();
        // могу не указывать имя класса - если вызываю внутри этого класса
        staticMethod();

        // Класс Автомобиль -> автомобиль_bmw("bmw", "черный")
        Auto bmv = new Auto("bmw", "черный"); // экземпляр класса Auto
        Auto kia = new Auto("kia", "белый");  // экземпляр класса Auto

        // Для вызова метода класса мне нужно создать экземпляр класса
        Main08_01 main0801 = new Main08_01();
        main0801.someMethod();
    }

    // Это статический метод - он принадлежит классу
    public static void staticMethod() {
        System.out.println("Статический метод класса Main08_01");
    }

    // Это не статический метод - он принадлежит экземпляру класса (или объекту)
    public void someMethod() {
        System.out.println("Это метод someMethod() вызван на экземпляре класса (или объекта)");
    }




}

