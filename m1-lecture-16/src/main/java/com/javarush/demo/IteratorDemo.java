package com.javarush.demo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IteratorDemo {

    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer intVal = iterator.next();
            System.out.println(intVal);
        }

    }

}
