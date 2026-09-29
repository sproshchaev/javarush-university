package com.javarush.demo;

import java.util.HashMap;
import java.util.Map;

public class WordCountDemo {

    public static void main(String[] args) {

        // word
        String text = "мама мыла рамы мама мыла окно";

        Map<String, Integer> counts = new HashMap<>();

        for (String word : text.split(" ")) {
            int previous = counts.getOrDefault(word, 0);
            counts.put(word, previous + 1);
        }

        System.out.println("Карта подсчета: " + counts);
        for (Map.Entry<String, Integer> pair : counts.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

    }

}
