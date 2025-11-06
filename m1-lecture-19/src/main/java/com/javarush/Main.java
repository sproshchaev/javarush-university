package com.javarush;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {

        Status status = Status.VALID;
        status = Status.VALID;
        // status = Status.valueOf("VALID!"); // No enum constant com.javarush.Status.VALID!

        System.out.println("Статус " + status);

        // Получить все варианты enum
        System.out.println("Число статусов из enum: " + Status.values().length);

        Status[] statusArr = Status.values();
        System.out.println("Массив: " + Arrays.toString(statusArr));

        // Обход enum:
        for (Status s : statusArr) {
            System.out.println(s + " - " + s.ordinal());
        }

        // ! O(1)
        // [][][][][]
        int[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // intArr[0].ordinal(); - не работает с массивом


        // toString()
        String statusStr = status.toString();
        System.out.println("Статус " + statusStr); // VALID

        Status status2 = Status.values()[0];
        System.out.println("status2 " + status2); // VALID

        boolean result = Status.VALID == Status.valueOf("INVALID");

        System.out.println("result: " + result); // false

        System.out.println(Status.VALID == Status.valueOf("VALID")); // true

        // Status status3 = new Status(); // java: enum classes may not be instantiated

        // Использование кастомного метода из enum
        System.out.println(Status.asList()); // [VALID, INVALID, BUSY, ERROR]

        // Singleton
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        // Это один и тот же объект?
        System.out.println(s1 == s2); // true

        s1.sayHello(); // Hello, Singleton!

        // Switch
        int day = 8;
        switch(day) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
                System.out.println("Вторник");
                break;
            case 3:
                System.out.println("Среда");
                break;
            case 4:
                System.out.println("Четверг");
                break;
            case 5:
                System.out.println("Пятница");
                break;
            case 6:
                System.out.println("Суббота");
                break;
            case 7:
                System.out.println("Воскресенье");
                break;
            default:
                System.out.println("Ввыеден неизвестный доселе день!");
        }

        // Что можно использовать в качестве параметра switch
        // int - работает
        int number = 2;
        switch(number) {
            case 1:
                System.out.println("Один");
                break;
            case 2:
                System.out.println("Два");
                break;
        }

        // char - работает!
        char letter = 'A';
        switch(letter) {
            case 'A':
                System.out.println("Буква А");
                break;
            case 'Б':
                System.out.println("Буква Б");
                break;
        }

        // String - работает!
        String color = "red";
        switch(color) {
            case "red":
                System.out.println("Красный");
                break;
            case "green":
                System.out.println("Зеленый");
                break;
        }

        // enum - работает!
        Status status4 = Status.VALID;
        switch(status4) {
            case VALID:
                System.out.println("Действие возможно");
                break;
            case INVALID:
                System.out.println("Действие не возможно");
                break;
        }

        //                       Нода1                     Нода2  Нода3  Нода4
        // ArrayList [][][][].     [1, null, next Нода2]....[2]...[3]....[4, prev, null]
        //                          LinkedList
        // LinkedList - двухсвязанный список [1, prev, next] - [2, prev, next] - ... - [n, prev, next]
        // сотоит из Узлов (Ноды)
        // Нода: значение (payload),

        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println(list.get(2)); // O(n)


    }

    private static class Node<E> {
        E item;
        Node<E> prev; // ссылка на предыдущий
        Node<E> next; // ссылка на следующий узел

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }

    }

}
