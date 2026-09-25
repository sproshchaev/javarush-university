package com.javarush.demo;

import java.util.HashSet;
import java.util.Set;

public class ForEachRemoveErrorDemo {

    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        for (Integer intVal : set) {
            if (intVal > 1) {
                set.remove(intVal); // меняю коллекцию при проходе и получаю ConcurrentModificationException
            }
        }

    }

}
