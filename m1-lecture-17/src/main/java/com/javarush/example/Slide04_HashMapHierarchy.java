package com.javarush.example;

import java.util.*;

public class Slide04_HashMapHierarchy {

    public static void main(String[] args) {
        System.out.println("=== Иерархия коллекций: HashMap ===\n");

        // 1. Создание HashMap с типами Integer (ключ) и String (значение)
        HashMap<Integer, String> userMap = new HashMap<>();

        // 2. Добавление элементов
        userMap.put(101, "Анна");
        userMap.put(102, "Борис");
        userMap.put(103, "Виктория");

        System.out.println("Добавлены пользователи:");
        for (Map.Entry<Integer, String> entry : userMap.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Имя: " + entry.getValue());
        }

        // 3. Получение значения по ключу
        String name = userMap.get(102);
        System.out.println("\nИмя пользователя с ID 102: " + name);

        // 4. Проверка наличия ключа
        boolean hasUser = userMap.containsKey(104);
        System.out.println("Есть ли пользователь с ID 104? " + hasUser);

        // 5. Удаление элемента
        userMap.remove(101);
        System.out.println("\nПосле удаления пользователя ID 101: " + userMap);

        // 6. Размер карты
        System.out.println("Текущее количество пользователей: " + userMap.size());

        // 7. Очистка карты
        userMap.clear();
        System.out.println("После очистки: " + userMap);

        // 8. Пустая ли карта?
        System.out.println("Карта пуста? " + userMap.isEmpty());

        // 9. Пример с другим типом ключа — строкой
        HashMap<String, Double> prices = new HashMap<>();
        prices.put("Яблоко", 55.99);
        prices.put("Банан", 32.50);

        System.out.println("\nЦены на фрукты:");
        for (String fruit : prices.keySet()) {
            System.out.println(fruit + " — " + prices.get(fruit) + " руб.");
        }

        // 10. Вывод всех значений
        System.out.println("\nВсе цены: " + prices.values());
    }
}