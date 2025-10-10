package com.javarush;

public class Main08_02 {
    public static void main(String[] args) {

        System.out.println("Вызываю Main08_01.publicMethod() из другого класса! ");
        Main08_01.publicMethod();

        // Попытка вызова метода private из другого класса
        // Main08_01.privateMethod(); // java: privateMethod() has private access in com.javarush.Main08_01

    }

}
