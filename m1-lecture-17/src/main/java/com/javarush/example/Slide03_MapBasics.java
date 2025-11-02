package com.javarush.example;

import java.util.*;

public class Slide03_MapBasics {

    public static void main(String[] args) {
        System.out.println("=== Пример работы с Map ===\n");

        // 1. Создание Map
        Map<String, Integer> studentGrades = new HashMap<>();

        // 2. Добавление пар ключ-значение
        studentGrades.put("Анна", 95);
        studentGrades.put("Борис", 87);
        studentGrades.put("Виктория", 92);

        // 3. Получение значения по ключу
        System.out.println("Оценка Анны: " + studentGrades.get("Анна"));

        // 4. Проверка наличия ключа
        if (studentGrades.containsKey("Петр")) {
            System.out.println("Петр есть в списке.");
        } else {
            System.out.println("Петра нет в списке.");
        }

        // 5. Перебор всех ключей
        System.out.println("\nВсе студенты:");
        for (String name : studentGrades.keySet()) {
            System.out.println("- " + name);
        }

        // 6. Перебор всех значений
        System.out.println("\nВсе оценки:");
        for (Integer grade : studentGrades.values()) {
            System.out.println("- " + grade);
        }

        // 7. Перебор всех пар (ключ-значение)
        System.out.println("\nСтуденты и их оценки:");
        for (Map.Entry<String, Integer> entry : studentGrades.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // 8. Удаление элемента
        studentGrades.remove("Борис");
        System.out.println("\nПосле удаления Бориса: " + studentGrades);

        // 9. Размер Map
        System.out.println("Количество студентов: " + studentGrades.size());

        // 10. Проверка на пустоту
        System.out.println("Map пустая? " + studentGrades.isEmpty());
    }
}