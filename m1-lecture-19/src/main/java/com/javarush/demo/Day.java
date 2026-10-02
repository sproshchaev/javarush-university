package com.javarush.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum Day {
    MONDAY,    // 0
    TUESDAY,   // 1
    WEDNESDAY, // 2
    THURSDAY,  // 3
    FRIDAY,    // 4
    SATURDAY,  // 5
    SUNDAY;     // 6

    public static List<Day> asList() {
        List<Day> list = new ArrayList<>();
        Collections.addAll(list, values());
        return list;
    }

    public boolean isWeekend() {
        return (this == SATURDAY || this == SUNDAY);
    }

}
