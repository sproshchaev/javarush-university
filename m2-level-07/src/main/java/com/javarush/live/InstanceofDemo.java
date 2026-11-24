package com.javarush.live;

public class InstanceofDemo {

    public static void main(String[] args) {

        Object o = 42;
        System.out.println(o instanceof  Integer); // true
        System.out.println(o instanceof  Number); // true

        o = "Java";

        System.out.println(o instanceof  Integer); // false
        System.out.println(o instanceof  CharSequence); // true

        o = null;

        System.out.println(o instanceof String); // false

    }

}
