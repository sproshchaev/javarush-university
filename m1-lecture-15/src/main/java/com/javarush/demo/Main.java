package com.javarush.demo;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int a = 10; // примитив

        Integer intObj = new Integer(); // класс-обертка над примитивом
        intObj.setValue(a);

        System.out.println(intObj); // Integer{value=10}

        int[] array = new int[5]; // массив работает с примитивами
        Integer[] arrayObj = new Integer[5]; // массив работает с объектами

        // У массива есть свои недостатки, поэтому в Java были придуманы коллекции (ArrayList)

        // ArrayList<int> - в списке я не могу исп примитив
        ArrayList<Integer> listIntObj = new ArrayList<>(); // я исп примитив упакованный в объект

        ArrayList<Car> carArrayList = new ArrayList<>();

    }

}
