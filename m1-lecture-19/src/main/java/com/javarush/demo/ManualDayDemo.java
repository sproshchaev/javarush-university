package com.javarush.demo;

public class ManualDayDemo {

    public static void main(String[] args) {

        System.out.println("Ручной класс, FRIDAY.ordinal(): " + ManualDay.FRIDAY.ordinal());
        System.out.println("Ручной класс, values().length: " + ManualDay.values().length);
        System.out.println("Ручной класс, values()[4] == FRIDAY: " + (ManualDay.values()[4] == ManualDay.FRIDAY));

    }

}
