package com.javarush;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Интерфейс Set
 */
public class SetDemo {

    public static void main(String[] args) {

        // HashSet -> неупорядоченная коллекция, быстрый
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Pineapple");
        hashSet.add("Pineapple"); // не содержит дубликаты

        System.out.println(hashSet); // [Apple, Pineapple, Banana]

        // LinkedHashSet - сохраняет порядок вставки
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Apple");
        linkedHashSet.add("Banana");
        linkedHashSet.add("Pineapple");
        linkedHashSet.add("Pineapple"); // не содержит дубликаты
        System.out.println(linkedHashSet); // [Apple, Banana, Pineapple]

        // TreeSet
        Set<Person> personSet = new TreeSet<>();
        personSet.add(new Person("FirstName_A", "", 25));
        personSet.add(new Person("FirstName_B", "", 15));
        personSet.add(new Person("FirstName_A", "", 15));
        personSet.add(new Person("FirstName_C", "", 45));

        for (Person person : personSet) {
            System.out.println(person);
        }

    }

}
