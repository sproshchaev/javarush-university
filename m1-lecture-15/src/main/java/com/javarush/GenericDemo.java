package com.javarush;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenericDemo {

    public static void main(String[] args) {

        // Без дженериков - Object нужно приведение // учебный пример!
        ArrayList rawList = new ArrayList();
        rawList.add("Hello");
        String str = (String) rawList.get(0);

        // C дженериком
        ArrayList<String> genericList = new ArrayList<>();
        genericList.add("World");
        String safeStr = genericList.get(0); // Без приведения

        // Параметризация коллекций
        ArrayList<Integer> intList = new ArrayList<>();
        ArrayList<Double> doubleList = new ArrayList<>();

        // Интерфейс Лист -> реализация Интерфейса
        List<Double> doubleList2 = new ArrayList<>();
        List<Double> doubleList3 = new LinkedList<>();

    }

    public interface Drawable {
        void draw();
    }

    List<Drawable> drawableList = new ArrayList<>();

}
