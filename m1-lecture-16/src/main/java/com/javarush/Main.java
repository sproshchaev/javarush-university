package com.javarush;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {

        // Множество Set
        // 1) Уникальные элементы
        // 2) Отсутствие порядка
        // 3) Добавление, Удаление, Проверка

        Set<String> set = new HashSet();

        set.add("Zebra");
        set.add("Apple");
        set.add("Banana");
        set.add("Banana"); // дубликат не будет добавлен

        System.out.println("Set: " + set); // Set: [Apple, Zebra, Banana]

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);

        System.out.println("List: " + list); // List: [1, 2, 3, 1]

        Set<Integer> unicSet = new HashSet<>();

        for (Integer i : list) {
            System.out.println("Результат добавления в Set для " + i + " " + unicSet.add(i));
        }

        System.out.println("UnicSet: " + unicSet); // UnicSet: [1, 2, 3] - произвольный порядок, нет гарантии

        // Проверка наличия
        // Set: [Apple, Zebra, Banana]
        System.out.println("contains Apple: " + set.contains("Apple")); // true
        System.out.println("contains !Apple: " + set.contains("!Apple")); // false

        // Удаление элемента
        set.remove("Apple");
        System.out.println("Set: " + set); // Set: [Zebra, Banana]






    }

}
