package com.javarush.demo;

public class EnumDeclareDemo {

    public static void main(String[] args) {

        Day day = Day.MONDAY;
        System.out.println("Сегодня: " + day);

        day = Day.FRIDAY;
        System.out.println(day);

        Day weekend = Day.SUNDAY;
        System.out.println("Выходной: " + weekend);

        Day[] days = Day.values();
        System.out.println("Всего значений: " + days.length);

        for (Day dayInFor : days) {
            System.out.println(dayInFor);
        }

        System.out.println("days[2] = " + days[2]);

        System.out.println(Day.MONDAY.ordinal()); // 0
        System.out.println(Day.FRIDAY.ordinal());

        String str = Day.MONDAY.toString();
        System.out.println("В строку: " + str + ", длина " + str.length());

        Day fromString = Day.valueOf("MONDAY");
        System.out.println("Из строки: " + fromString);

        int index = Day.MONDAY.ordinal();
        System.out.println("В число: " + index);

        Day fromNumber = Day.values()[2];
        System.out.println("Из числа 2: " + fromNumber);

        Day fist = Day.FRIDAY;
        Day second = Day.valueOf("FRIDAY");
        Day third = Day.values()[4];

        System.out.println(fist == second);     // true
        System.out.println(fist == third);      // true
        System.out.println(fist == Day.MONDAY); // false

        String a = "FRIDAY";
        String b = new String("FRIDAY");
        System.out.println("Строки через == " + (a == b)); // false
        System.out.println("Строки через equals " + (a.equals(b))); // true

        System.out.println("---");

        System.out.println(Day.valueOf("FRIDAY"));
        System.out.println(Day.valueOf("friday".toUpperCase())); // No enum constant com.javarush.demo.Day.friday


        for (Day dayInFor : Day.values()) {
            if (dayInFor.isWeekend()) {
                System.out.println(day + ": выходной"); // dayInFor!!!
            }
        }

    }

}
