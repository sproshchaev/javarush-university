package com.javarush.util;

public class IdGenerator {
    public static String nextId() {
        return "ID-" + System.nanoTime();
    }
}
