package com.javarush.example;

import java.util.*;

public class Slide06_KeySetAndEntrySet {

    public static void main(String[] args) {
        System.out.println("=== Подмножества HashMap: keySet() и entrySet() ===\n");

        // Создаем карту
        HashMap<String, Double> productPrices = new HashMap<>();
        productPrices.put("Яблоко", 55.99);
        productPrices.put("Банан", 32.50);
        productPrices.put("Апельсин", 48.75);

        // 1. Используем keySet() — получаем множество ключей
        System.out.println("✅ Способ 1: Перебор через keySet()");
        Set<String> productNames = productPrices.keySet();
        for (String productName : productNames) {
            double price = productPrices.get(productName);
            System.out.println("- " + productName + ": " + price + " руб.");
        }

        // 2. Используем entrySet() — получаем множество пар (Entry)
        System.out.println("\n✅ Способ 2: Перебор через entrySet()");
        Set<Map.Entry<String, Double>> entries = productPrices.entrySet();
        for (Map.Entry<String, Double> entry : entries) {
            String productName = entry.getKey();
            double price = entry.getValue();
            System.out.println("- " + productName + ": " + price + " руб.");
        }

        // 3. Пример изменения значений через entrySet()
        System.out.println("\n🔧 Изменяем цены на 10% вверх:");
        for (Map.Entry<String, Double> entry : entries) {
            double newPrice = entry.getValue() * 1.1;
            entry.setValue(newPrice);
        }
        System.out.println("Обновленные цены: " + productPrices);

        // 4. Демонстрация типа возвращаемого значения
        System.out.println("\n📌 Тип keySet(): " + productNames.getClass().getSimpleName());
        System.out.println("📌 Тип entrySet(): " + entries.getClass().getSimpleName());

        // 5. Пустая карта — что вернут методы?
        HashMap<String, String> emptyMap = new HashMap<>();
        System.out.println("\n📦 Пустая карта:");
        System.out.println("keySet().size() = " + emptyMap.keySet().size());
        System.out.println("entrySet().size() = " + emptyMap.entrySet().size());
    }
}