package com.javarush.example;

import java.util.*;

public class Slide05_HashMapMethods {

    public static void main(String[] args) {
        System.out.println("=== Основные методы HashMap ===\n");

        // Создаем карту
        HashMap<String, Integer> studentScores = new HashMap<>();

        // 1. put(K key, V value) — добавление пары
        studentScores.put("Анна", 95);
        studentScores.put("Борис", 87);
        studentScores.put("Виктория", 92);
        System.out.println("После put(): " + studentScores); // После put(): {Борис=87, Анна=95, Виктория=92}

        // 2. get(K key) — получение значения по ключу
        Integer annaScore = studentScores.get("Анна");
        System.out.println("Оценка Анны: " + annaScore); // Оценка Анны: 95

        // 3. containsKey(K key) — проверка наличия ключа
        boolean hasBoris = studentScores.containsKey("Борис"); // Есть ли Борис? true
        System.out.println("Есть ли Борис? " + hasBoris);

        // 4. containsValue(V value) — проверка наличия значения
        boolean has95 = studentScores.containsValue(95);
        System.out.println("Есть ли оценка 95? " + has95); // Есть ли оценка 95? true

        // 5. remove(K key) — удаление пары по ключу
        Integer removed = studentScores.remove("Борис");
        System.out.println("Удален Борис, его оценка: " + removed); // Удален Борис, его оценка: 87
        System.out.println("После remove(): " + studentScores);

        // 6. clear() — очистка всей карты
        studentScores.clear();
        System.out.println("После clear(): " + studentScores);

        // 7. size() — размер карты
        System.out.println("Размер после очистки: " + studentScores.size()); // Размер после очистки: 0

        // Возвращаем данные для демонстрации остальных методов
        studentScores.put("Анна", 95);
        studentScores.put("Виктория", 92);

        // 8. keySet() — множество ключей
        Set<String> names = studentScores.keySet();
        System.out.println("\nИмена студентов (keySet): " + names); // Имена студентов (keySet): [Анна, Виктория]

        // 9. values() — множество значений
        Collection<Integer> scores = studentScores.values(); // Оценки (values): [95, 92]
        System.out.println("Оценки (values): " + scores);

        // 10. entrySet() — множество пар (Map.Entry)
        Set<Map.Entry<String, Integer>> entries = studentScores.entrySet();
        System.out.println("\nВсе пары (entrySet):");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        // 11. Пример использования entrySet для изменения значений
        System.out.println("\nДобавляем +5 баллов всем:");
        for (Map.Entry<String, Integer> entry : entries) {
            entry.setValue(entry.getValue() + 5);
        }
        System.out.println("Новые оценки: " + studentScores);
    }
}