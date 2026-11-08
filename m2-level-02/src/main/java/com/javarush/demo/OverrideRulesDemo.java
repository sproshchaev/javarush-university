package com.javarush.demo;

public class OverrideRulesDemo {
    public static void main(String[] args) {
        Bird bird = new Parrot();
        bird.speak();            // вызовется переопределенный метод
        bird.greet();            // вызовется метод родителя

        Parrot parrot = new Parrot();
        parrot.greet("Кеша");    // это перегрузка, отдельный метод
    }
}

class Bird {
    void speak() {
        System.out.println("Птица: чирик");
    }

    void greet() {
        System.out.println("Птица: привет");
    }
}

class Parrot extends Bird {
    // Те же имя, параметры и тип результата: переопределение
    @Override
    void speak() {
        System.out.println("Попугай: Кеша хороший");
    }

    // Другие параметры: это новый метод, родительский greet() остался
    void greet(String name) {
        System.out.println("Попугай: привет, " + name);
    }

    // @Override
    // void speak(String word) {
    //     System.out.println(word);
    // }
}
