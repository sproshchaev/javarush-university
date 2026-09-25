package com.javarush.demo.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class HashSetCreateDemo {

    public static void main(String[] args) {

        Set<Integer> numbers = new HashSet<>();
        Set<Integer> numbersLnk = new LinkedHashSet<>();

        // [] [] []
        numbers.add(10);
        numbers.add(10);
        numbers.add(20);
        numbers.add(1);
        System.out.println(numbers); // [1, 20, 10]
        System.out.println(numbers.size());
        System.out.println(numbers.contains(10)); // true
        System.out.println(numbers.contains(100)); // false
        numbers.add(100);
        System.out.println(numbers); // [1, 20, 100, 10]
        numbers.remove(100);
        System.out.println(numbers); // [1, 20, 10]
        numbers.clear();
        System.out.println(numbers); // []


        // [] -> [] -> []
        numbersLnk.add(10);
        numbersLnk.add(10);
        numbersLnk.add(20);
        numbersLnk.add(1);
        System.out.println(numbersLnk); // [10, 20, 1]

        // TreeSet
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("10");
        treeSet.add("10");
        treeSet.add("20");
        treeSet.add("1");
        System.out.println(treeSet); // [1, 10, 20]

    }

}
