package com.javarush;

/**
 * JavaRush-University
 */
public class Main03 {

    // String Pool
    public static void main(String[] args) {

        // Содержимое сравниваем объектов сравниваем через equals, ссылки через ==

        // (1) Создание через строковый литерал - наиболее часто используется при работе со строками!
        String a = "Привет!"; // Создание строки через литерал -> String Pool
        String b = "Привет!"; // Тот же литерал - ссылается на ту же строку в String Pool

        // == - по ссылке
        System.out.println(a == b); // true

        // (2) Создание через new
        String c = new String("Привет!"); // Через new - в Heap (не попадает в String Pool)
        System.out.println(a == c); // false

        // (3) Метод intern() - добавляет в String Pool если ее там нет и возвр ссылку из пула
        String d = new String("Привет!").intern(); // true
        System.out.println(a == d);


    }

}
