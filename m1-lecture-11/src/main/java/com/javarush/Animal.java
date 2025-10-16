package com.javarush;

/**
 * Базовый (абстрактный) класс - мы не знаем какое это животное
 * Нельзя создать экземпляр от абстрактного класса!
 */
abstract class Animal {

    void makeSound() {
        System.out.println("Какое-то животное издает звук");
    }

}
