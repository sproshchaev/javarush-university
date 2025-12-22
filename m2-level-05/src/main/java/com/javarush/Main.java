package com.javarush;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // До Java 5
        ArrayList list = new ArrayList(); // RAW type
        list.add("Hello!");
        list.add(123);

        // String str = (String) list.get(1); // ClassCastException

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("Hello!");
        // list2.add(123); // java: incompatible types: int cannot be converted to java.lang.String
        String str = list2.get(0);




    }

}
