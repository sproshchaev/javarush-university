package com.javarush;

import java.util.Arrays;
import java.util.List;

public enum Status {

    VALID,   // 0
    INVALID, // 1
    BUSY,    // 2
    ERROR;   // 3

    // Статический метод возвращающий все статусы
    public static List<Status> asList() {
        return Arrays.asList(values());
    }

}
