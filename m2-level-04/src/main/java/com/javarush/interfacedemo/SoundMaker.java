package com.javarush.interfacedemo;

// Контракт на поведение "Издавать звук"
interface SoundMaker {
    // Абстрактный метод. Нет тела {}, только то, что сделать
    void makeSound();
}
