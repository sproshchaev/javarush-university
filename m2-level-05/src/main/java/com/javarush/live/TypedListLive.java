package com.javarush.live;

import java.util.ArrayList;

public class TypedListLive {

    public static void main(String[] args) {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("abc");

        // strings.add(1000); // 'add(java.lang.String)' in 'java.util.ArrayList' cannot be applied to '(int)'

        String value = strings.get(0);
        System.out.println(value);

    }

}
