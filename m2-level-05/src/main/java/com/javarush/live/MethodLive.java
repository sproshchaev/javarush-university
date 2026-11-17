package com.javarush.live;

public class MethodLive {

    static <T> T first(T[] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("Передан пустой массив!");
        }
        return values[0];
    }

    public static void main(String[] args) {

        String string = first(new String[]{"Тигр", "Кот"});
        Integer number = first(new Integer[]{10, 20});

        System.out.println(string);
        System.out.println(number);

    }

}
