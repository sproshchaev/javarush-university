package com.javarush;

/**
 * JavaRush-University
 */
public class Main08_01 {

    // Главный статический метод - точка входа
    public static void main(String[] args) {

        // Модификаторы доступа
        // --------------------
        // public - полный доступ         (!)
        // private - только внутри класса (!)
        // protected - внутри пакета + наследники (и из других пакетов тоже)
        // дефолтный (package-private) - только внутри пакета

        System.out.println("Вызов publicMethod() из текущего класса ");
        publicMethod();
        privateMethod();

    } // main

    // Доступ к этому методу будет ото всюду! Это public!
    public static void publicMethod() {
        System.out.println("I'm publicMethod!");
    }

    // Доступ был только из текущего класса  - то используем private!
    private static void privateMethod() {
        System.out.println("I'm privateMethod!");
    }

}
