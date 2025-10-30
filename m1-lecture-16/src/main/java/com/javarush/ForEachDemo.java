package com.javarush;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * For-each
 */
public class ForEachDemo {

    public static void main(String[] args) {

        // [Apple] [Banana] [Chery]

        // Синтаксис
        // for (Тип имя : коллекция) {
        //   имя = Apple
        //         Banana
        //         Chery
        // }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer i : list) {
            System.out.println("i= " + i);
        }

        System.out.println("Set: ");
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        for (Integer i : set) {
            System.out.println("i= " + i);
        }


    }

}
