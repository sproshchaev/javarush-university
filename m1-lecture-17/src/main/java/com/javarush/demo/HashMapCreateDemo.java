package com.javarush.demo;

import java.util.*;

public class HashMapCreateDemo {

    public static void main(String[] args) {

        // И                           Р
        Map<String, Integer> map = new HashMap<>();

        System.out.println(map.size()); // 0

        map.put("сто", 100);
        map.put("двести", 200);
        map.put("ноль", 0);

        System.out.println(map.size()); // 3
        System.out.println(map); // {двести=200, сто=100, ноль=0}

        map.put("сто", 1000);

        System.out.println(map); // {двести=200, сто=1000, ноль=0}

        map.put(null, null);

        System.out.println(map); // {null=null, двести=200, сто=1000, ноль=0}

        // O(1) - быстро!
        System.out.println("Есть ли ключ 'двести'? " + map.containsKey("двести")); // true
        System.out.println("Есть ли ключ '_'? " + map.containsKey("_")); // false

        // O(n)
        System.out.println("Есть ли значение 1000? " + map.containsValue(1000)); // true

        // Удаление
        System.out.println("Удалили null " + map.remove(null));
        System.out.println(map); // {двести=200, сто=1000, ноль=0}

        // get
        System.out.println("ноль: " + map.get("ноль")); // ноль: 0
        System.out.println("ноль!: " + map.get("ноль!")); // null

        // getOrDefault
        System.out.println("ноль!: " + map.getOrDefault("ноль!", 0)); // ноль!: 0

        // Получить множество ключей
        Set<String> keys = map.keySet();
        System.out.println("Ключи: " + keys); // Ключи: [двести, сто, ноль]

        // Получить коллекцию значений
        Collection<Integer> values = map.values();
        System.out.println("Значения: " + values); // Значения: [200, 1000, 0]

        // Итерация по ключу O(n) - медленно
        int total = 0;
        for (String key : keys) {
            total = total + map.get(key);
            System.out.println(key + " - " + map.get(key));
        }
        System.out.println("Сумма всех значений в карте: " + total); // Сумма всех значений в карте: 1200

        // Обход по entrySet
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            System.out.println("ключ " + pair.getKey() + " значение " + pair.getValue());
        }

        // Обход по entrySet с изменением
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            pair.setValue(pair.getValue() * 10);
        }
        System.out.println(map); // {двести=2000, сто=10000, ноль=0}

        // Заполнение мапы через цикл
        Map<Integer, Integer> mapInt = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            mapInt.put(i + 1, (i + 1) * 10);
        }
        System.out.println(mapInt); // {1=10, 2=20, 3=30, 4=40, 5=50}

        // Map

        Map<Integer, List<Integer>> mapList = new HashMap<>();

        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        list.add(300);
        mapList.put(1, list);

        System.out.println(mapList); // {1=[100, 200, 300]}

    }

}
