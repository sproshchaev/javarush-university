package com.javarush.demo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IteratorRemoveDemo {

    public static void main(String[] args) {

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println("До удаления: " + set);

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer intVal = iterator.next();
            if (intVal > 1) {
                iterator.remove();
            }
        }

        System.out.println("После удаления: " + set);

    }

}
