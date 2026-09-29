package com.javarush.demo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapImplementationsDemo {

    public static void main(String[] args) {


        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();

        fill(hashMap);
        fill(linkedHashMap);
        fill(treeMap);

        System.out.println("hashMap: " + hashMap);
        System.out.println("linkedHashMap: " + linkedHashMap);
        System.out.println("treeMap: " + treeMap);

    }

    // Метод заполняет любую карту
    private static void fill(Map<String, Integer> map) {
        map.put("молоко", 12);
        map.put("хлеб", 5);
        map.put("сахар", 3);
    }

}
