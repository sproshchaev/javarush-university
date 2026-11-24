package com.javarush.live;

public class ReferenceCastingDemo {

    public static void main(String[] args) {

        Integer number = 42; // наследник
        Number value = number; // родитель

        System.out.println(value == number); // true

        Integer restored = (Integer) value;
        System.out.println("После сужения: " + restored); // После сужения: 42

        Number fraction = 3.5;

        try {
            Integer wrong = (Integer) fraction;
            System.out.println(wrong);
        } catch (ClassCastException e) {
            System.out.println(e.getClass().getSimpleName());
        }

        if (fraction instanceof Integer) {
            System.out.println((Integer) fraction);
        } else {
            System.out.println("Проверка пропустила приведение к Integer");
        }

    }

}
