package com.javarush;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Удаление элементов при Итерировании
 */
public class Iterator2Demo {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        System.out.println("set: " + set);

        // Удаление во время итерации делать нельзя!
        for (Integer i : set) {
            if (i % 2 == 0) {
                // set.remove(i); // ConcurrentModificationException
            }
        }

        // Использовать Итератор
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            if (num % 2 == 0) {
                iterator.remove();
            }
        }

        System.out.println("remove: " + set); // remove: [1, 3]

    }
}
