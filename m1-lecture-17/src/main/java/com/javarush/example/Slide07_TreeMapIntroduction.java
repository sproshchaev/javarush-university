package com.javarush.example;

import java.util.*;

public class Slide07_TreeMapIntroduction {

    public static void main(String[] args) {
        System.out.println("=== Знакомство с коллекцией TreeMap ===\n");

        // 1. Создание TreeMap с ключами String (реализуют Comparable)
        TreeMap<String, Double> productPrices = new TreeMap<>();

        // Добавляем элементы в "случайном" порядке
        productPrices.put("Банан", 32.50);
        productPrices.put("Яблоко", 55.99);
        productPrices.put("Апельсин", 48.75);

        System.out.println("✅ TreeMap с ключами String — сортировка по алфавиту:");
        for (String productName : productPrices.keySet()) {
            System.out.println("- " + productName + ": " + productPrices.get(productName) + " руб.");
        }

        // 2. TreeMap с ключами Integer — сортировка по возрастанию
        TreeMap<Integer, String> idToName = new TreeMap<>();
        idToName.put(103, "Виктория");
        idToName.put(101, "Анна");
        idToName.put(102, "Борис");

        System.out.println("\n✅ TreeMap с ключами Integer — сортировка по числу:");
        for (Integer id : idToName.keySet()) {
            System.out.println("- ID " + id + ": " + idToName.get(id));
        }

        // 3. Пример с пользовательским классом, который НЕ реализует Comparable
        // Создаем класс без Comparable
        class Product {
            String name;
            double price;

            public Product(String name, double price) {
                this.name = name;
                this.price = price;
            }

            @Override
            public String toString() {
                return name + " (" + price + ")";
            }
        }

        // Создаем TreeMap с компаратором — сортируем по имени
        TreeMap<Product, String> productMap = new TreeMap<>(
                Comparator.comparing(p -> p.name)
        );

        Product p1 = new Product("Чай", 120.0);
        Product p2 = new Product("Кофе", 250.0);
        Product p3 = new Product("Сахар", 45.0);

        productMap.put(p1, "Напиток");
        productMap.put(p2, "Напиток");
        productMap.put(p3, "Ингредиент");

        System.out.println("\n✅ TreeMap с пользовательским ключом и компаратором:");
        for (Product product : productMap.keySet()) {
            System.out.println("- " + product + " → " + productMap.get(product));
        }

        // 4. Что будет, если не передать компаратор для не-Comparable ключа?
        try {
            TreeMap<Product, String> brokenMap = new TreeMap<>();
            brokenMap.put(p1, "Тест");
        } catch (Exception e) {
            System.out.println("\n❌ Ошибка при создании TreeMap без компаратора для не-Comparable ключа:");
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // 5. Получение первого и последнего элемента (специфично для TreeMap)
        System.out.println("\n📌 Первый ключ: " + productMap.firstKey());
        System.out.println("📌 Последний ключ: " + productMap.lastKey());

        // 6. Сортировка по значению? Нет — только по ключу!
        System.out.println("\n💡 Важно: TreeMap всегда сортирует по ключу, а не по значению!");
    }
}