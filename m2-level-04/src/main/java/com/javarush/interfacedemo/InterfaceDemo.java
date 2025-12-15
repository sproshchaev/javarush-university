package com.javarush.interfacedemo;

public class InterfaceDemo {

    public static void main(String[] args) {

        // Слева интерфейс, справа - реализация
        SoundMaker myPet = new Cat();
        SoundMaker myItem = new Bell();

        // Один и тот же метод - разное поведение
        myPet.makeSound();  // мяу!
        myItem.makeSound(); // Дзинь-дзинь!

        // Мы можем написать метод, который работает
        // с любым объектом, поддерживающим интерфейс SoundMaker
        playSound(myPet); // <- передаем реализацию интерфейса makeSound()
        playSound(myItem);
    }

    // Этот метод не знает КТО издает звук!
    // Он знает только что этот что-то умеет делать makeSound()
    static void playSound(SoundMaker something) {
        System.out.println("Сейчас прозвучит:");
        something.makeSound();
    }


}
