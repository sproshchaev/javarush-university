package com.javarush.wildcards;

import java.util.ArrayList;
import java.util.List;

public class WildcardsDemo {
    static class Animal {}
    static class Cat extends Animal {}

    public static void main(String[] args) {
        List<Cat> catList = new ArrayList<>();
        catList.add(new Cat());

        // PECS = (P)roducer (E)xtends, (C)onsumer (S)uper

        // ? extends - можно читать = Producer - создание, генерация
        List<? extends Animal> animalList = catList;
        Animal animal = animalList.get(0); // чтение возможно!
        // animalList.add(new Cat()); // не могу добавлять

        // ? super - можем добавлять = Consumer - получение, потребление
        List<? super Cat> catList2 = new ArrayList<Animal>();
        catList2.add(new Cat()); // добавление возможно
        // Cat cat = catList2.get(0);  // чтение как не Object не работает
        Object o = catList2.get(0);  // чтение как Object!
    }

}
