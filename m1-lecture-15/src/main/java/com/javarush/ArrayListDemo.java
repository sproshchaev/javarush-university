package com.javarush;

import java.util.ArrayList;

/**
 * ArrayList
 */
public class ArrayListDemo {

    public static void main(String[] args) {

        // Создание
        ArrayList<String> list = new ArrayList<>(); // DEFAULT_CAPACITY = 10; [] [] [] [] ... []

        System.out.println("Размер массива: " + list.size()); // Размер массива: 0
        System.out.println("Список пуст: " + list.isEmpty()); // true

        list.add("Apple");  // 0 - индекс
        list.add("Banana"); // 1 - индекс
        list.add("Orange"); // 2 - индекс
        list.add(0,"Orange 1");

        System.out.println("Размер массива: " + list.size()); // Размер массива: 3
        System.out.println("Список пуст: " + list.isEmpty()); // false
        System.out.println(list.toString()); // [Orange 1, Apple, Banana, Orange]

        System.out.println("Первый элемент: " + list.get(0)); // Первый элемент: Orange 1

        list.remove("Orange"); // Удаление по значению
        System.out.println(list); // [Orange 1, Apple, Banana]

        list.remove(0); // Удаление по индексу
        System.out.println(list); // [Apple, Banana]

        // Очистить весь список
        list.clear();
        System.out.println(list); // []

        // Заполнение списка через цикл
        for (int i = 0; i < 20; i++) {
            list.add("Элемент " + i);
        }

        System.out.print(list);

        // Вывод содержимого через for-each
        for (String str : list) {
            System.out.println(str);
        }


    }

}
