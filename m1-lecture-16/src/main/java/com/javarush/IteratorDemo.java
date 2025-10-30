package com.javarush;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Итератор.
 */
public class IteratorDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList();
        list.add("Apple");
        list.add("Banana");
        list.add("Chery");

        // Получить итератор из коллекции [Apple] [Banana] [Chery] [] [] ...
        Iterator<String> iterator = list.iterator();

        // hasNext() - возвращает true если есть след элемент для обхода
        // next() - элемент [Apple] -> [Banana], вернет Banana
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }

        // (Одноразовость)Если мы дошли до конца коллекции - то должны взять новый итератор
        Iterator<String> iterator2 =  list.iterator();
        while (iterator2.hasNext()) {
            String item = iterator2.next();
            if (item.equals("Chery")){
                iterator2.remove(); // безопасное удаление
            }
        }

        System.out.println("remove: " + list); // remove: [Apple, Banana]

    }

}
