package com.javarush;

import java.util.*;

/**
 *  JavaRush-University. Паттерны проектирования - 2
 */
public class IteratorDemo {

    public static void main(String[] args) {

        // Итератор
        List<String> list = Arrays.asList("A", "B", "C", "C");
        Set<String> set = new HashSet<>(list);

        // Получить итератор из коллекции
        Iterator<String> iterator = list.iterator();
        Iterator<String> iterator2 = set.iterator(); //

        System.out.println("List: ");

        // Единообразный обход
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }

        System.out.println("Set: ");

        while (iterator2.hasNext()) {
            String item = iterator2.next();
            System.out.println(item);
        }

    }
}
