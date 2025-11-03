package com.javarush;

import org.atpfivt.ljv.LJV;

import java.awt.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;
import java.util.List;

/**
 * JavaRush-University
 */
public class Main {

    public static void main(String[] args) {

        // Страна (ключ) -> Столица (значение)
        // (!) поиск по ключу O(1)
        // Константное время поиска: не зависимо от кол-ва элементов время у нас одно и тоже
        // [100]       [][][][]...[](1 000 000) => O(1) - не зависит от размера коллекции
        // [100]       [][][][]...[](1 000 000) => O(n) - зависит от размера коллекции
        // Map<Ключ, Значение> имя = new HashMap<>();
        // HashMap<String, String> capitals = new HashMap<>();
        // HashMap не гарантирует порядок элементов!
        Map<String, String> capitals = new HashMap<>(); // <- правильно так

        capitals.put("США", "Вашингтон");
        capitals.put("Германия", "Берлин");

        System.out.println("Столица США " + capitals.get("США"));

        for (Map.Entry<String, String> entry : capitals.entrySet()) {
            entry.setValue(entry.getValue() + "!");
        }

        System.out.println(capitals);

        // Map<int, int> mapInt = new HashMap<>();
        Map<Integer, Integer> mapInt = new HashMap<>();
        mapInt.put(1, 100);
        mapInt.put(2, 200);

        // Карта персон
        Map<Person, Integer> personIntegerMap = new HashMap<>();
        Person person1 = new Person(1, "Иван");
        Person person2 = new Person(2, "Семен");

        personIntegerMap.put(person1, 100);
        personIntegerMap.put(person2, 200);

        System.out.println(person1.equals(person2)); // false
        System.out.println("hashCode для Person id=1: " + person1.hashCode()); // hashCode=132288391
        System.out.println("hashCode для Person id=2: " + person2.hashCode()); // hashCode=21009323823

        System.out.println(personIntegerMap); // до 8 (?) элементов попадающих в 1 бакет - связанный список, далее будет дерево O(log n)



        //browse(new LJV(), personIntegerMap);

        // * Доп задание для тех кто идет вперед
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        System.out.println(list); // [100, 200, 300, 400]
        // Collections.reverse(list);
        System.out.println(MyReverse(list)); // [400, 300, 200, 100]
        System.out.println(list); // [400, 300, 200, 100]

    }

    /**
     * Метод переворачивает динамический массив без использования Collections.reverse();
     * @param intList
     * @return
     */
    public static List<Integer> MyReverse(List<Integer> intList) {
        return null;
    }

    public static void browse(LJV ljv, Object obj) {
        try {
            var dot = URLEncoder.encode(ljv.drawGraph(obj), "UTF8")
                    .replaceAll("\\+", "%20");
            Desktop.getDesktop().browse(
                    new URI("https://dreampuf.github.io/GraphvizOnline/#"
                            + dot));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


}
