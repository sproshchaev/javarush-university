package com.javarush.example;

import java.util.*;

public class Slide10_CollectionsUtilityMethods {

    public static void main(String[] args) {
        System.out.println("=== Создание и изменение коллекций через Collections ===\n");

        // 1. addAll() — добавление нескольких элементов
        System.out.println("✅ Collections.addAll():");
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "Анна", "Борис", "Виктория", "Григорий");
        System.out.println("Исходный список: " + names);

        // Можно добавить и из другого массива
        String[] moreNames = {"Даша", "Евгений"};
        Collections.addAll(names, moreNames);
        System.out.println("После добавления: " + names);

        // 2. fill() — замена всех элементов на один
        System.out.println("\n✅ Collections.fill():");
        Collections.fill(names, "Неизвестный");
        System.out.println("После fill(): " + names);

        // 3. nCopies() — создание неизменяемого списка копий
        System.out.println("\n✅ Collections.nCopies():");
        List<String> placeholders = Collections.nCopies(5, "Заглушка");
        System.out.println("Список из 5 копий: " + placeholders);

        // Попытка изменения — вызовет исключение!
        try {
            placeholders.set(0, "Новое значение");
        } catch (Exception e) {
            System.out.println("❌ Нельзя изменить nCopies: " + e.getClass().getSimpleName());
        }

        // 4. replaceAll() — замена всех совпадающих элементов
        System.out.println("\n✅ Collections.replaceAll():");
        List<String> fruits = new ArrayList<>(Arrays.asList("Яблоко", "Банан", "Яблоко", "Апельсин"));
        System.out.println("До замены: " + fruits);

        Collections.replaceAll(fruits, "Яблоко", "Персик");
        System.out.println("После замены: " + fruits);

        // 5. copy() — копирование из одного списка в другой
        System.out.println("\n✅ Collections.copy():");
        List<String> source = Arrays.asList("Красный", "Синий", "Зелёный");
        List<String> destination = new ArrayList<>(Arrays.asList("???", "???", "???"));

        System.out.println("Источник: " + source);
        System.out.println("Цель до копирования: " + destination);

        Collections.copy(destination, source);
        System.out.println("Цель после копирования: " + destination);

        // Что будет, если цель меньше источника?
        try {
            List<String> smallDest = new ArrayList<>(Arrays.asList("???", "???"));
            Collections.copy(smallDest, source); // Исключение!
        } catch (Exception e) {
            System.out.println("❌ Целевой список слишком мал: " + e.getClass().getSimpleName());
        }

        // 6. Демонстрация использования nCopies для инициализации
        System.out.println("\n💡 Полезное применение nCopies:");
        List<Integer> defaultScores = Collections.nCopies(10, 0);
        System.out.println("По умолчанию 10 студентов имеют 0 баллов: " + defaultScores);

        // 7. Сравнение с обычным циклом
        System.out.println("\n🔄 Альтернатива fill() через цикл:");
        List<String> listWithLoop = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            listWithLoop.add("Цикл");
        }
        System.out.println("Через цикл: " + listWithLoop);

        // 8. Вывод итогового совета
        System.out.println("\n✅ Итог: Утилитный класс Collections экономит время и код!");
        System.out.println("- Добавляете много? → addAll()");
        System.out.println("- Заливаете всё одним значением? → fill()");
        System.out.println("- Нужны копии? → nCopies() (только для чтения!)");
        System.out.println("- Заменяете значения? → replaceAll()");
        System.out.println("- Копируете? → copy() (следите за размером!)");
    }
}