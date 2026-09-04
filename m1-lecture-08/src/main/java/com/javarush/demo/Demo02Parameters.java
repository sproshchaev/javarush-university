package com.javarush.demo;

public class Demo02Parameters {

    public static void main(String[] args) {
        int countInt = 0;
        long countLong = 0;

        printLine("Hello!", Long.getLong("0"));
    }

    // printLine(String, int)
    public static void printLine(String line, int count) {
        System.out.println("Сигнатура: printLine(String, int)");
        for (int i = 0; i < count; i++) {
            System.out.println(line);
        }
    }

    // printLine(String, long)
    public static void printLine(String line, long count) {
        System.out.println("Сигнатура: printLine(String, long)");
        for (int i = 0; i < count; i++) {
            System.out.println(line);
        }
    }

}
