package com.javarush.example;

import java.util.*;

public class Slide11_CollectionsSearchMethods {

    public static void main(String[] args) {
        System.out.println("=== Поиск элементов в коллекциях через Collections ===\n");

        // 1. min() и max() — находим минимальный и максимальный элемент
        System.out.println("✅ Collections.min() / max():");
        List<Integer> scores = Arrays.asList(85, 92, 78, 96, 88);
        System.out.println("Оценки: " + scores);
        System.out.println("Минимальная оценка: " + Collections.min(scores));
        System.out.println("Максимальная оценка: " + Collections.max(scores));

        // Можно использовать компаратор для не-Comparable объектов
        class Student {
            String name;
            int score;
            Student(String name, int score) { this.name = name; this.score = score; }
            @Override public String toString() { return name + "(" + score + ")"; }
        }

        List<Student> students = Arrays.asList(
                new Student("Анна", 95),
                new Student("Борис", 87),
                new Student("Виктория", 92)
        );

        Student topStudent = Collections.max(students, Comparator.comparingInt(s -> s.score));
        System.out.println("Лучший студент: " + topStudent);

        // 2. frequency() — подсчёт вхождений элемента
        System.out.println("\n✅ Collections.frequency():");
        List<String> fruits = Arrays.asList("Яблоко", "Банан", "Яблоко", "Апельсин", "Банан", "Яблоко");
        System.out.println("Фрукты: " + fruits);

        int appleCount = Collections.frequency(fruits, "Яблоко");
        int bananaCount = Collections.frequency(fruits, "Банан");
        int grapeCount = Collections.frequency(fruits, "Виноград");

        System.out.println("Яблок: " + appleCount);
        System.out.println("Бананов: " + bananaCount);
        System.out.println("Винограда: " + grapeCount); // 0

        // 3. binarySearch() — бинарный поиск (только в отсортированном списке!)
        System.out.println("\n✅ Collections.binarySearch():");
        List<Integer> sortedNumbers = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11));
        System.out.println("Отсортированный список: " + sortedNumbers);

        int index = Collections.binarySearch(sortedNumbers, 7);
        System.out.println("Индекс числа 7: " + index); // 3

        int notFound = Collections.binarySearch(sortedNumbers, 4);
        System.out.println("Поиск числа 4 (не найдено): " + notFound); // -3 (вставка на позицию 2)

        // Что будет, если список не отсортирован?
        List<Integer> unsorted = Arrays.asList(5, 2, 8, 1, 9);
        int wrongIndex = Collections.binarySearch(unsorted, 8);
        System.out.println("Бинарный поиск в НЕотсортированном списке: " + wrongIndex); // может быть любой!

        // 4. disjoint() — проверка на отсутствие пересечений
        System.out.println("\n✅ Collections.disjoint():");
        Set<String> setA = Set.of("Красный", "Синий", "Зелёный");
        Set<String> setB = Set.of("Жёлтый", "Оранжевый", "Фиолетовый");
        Set<String> setC = Set.of("Синий", "Белый");

        System.out.println("setA и setB не пересекаются? " + Collections.disjoint(setA, setB)); // true
        System.out.println("setA и setC не пересекаются? " + Collections.disjoint(setA, setC)); // false

        // 5. Полезные комбинации
        System.out.println("\n💡 Пример: найти самое частое слово в списке");
        List<String> words = Arrays.asList("привет", "мир", "привет", "java", "мир", "привет");
        String mostFrequent = null;
        int maxFreq = 0;

        for (String word : new HashSet<>(words)) { // уникальные слова
            int freq = Collections.frequency(words, word);
            if (freq > maxFreq) {
                maxFreq = freq;
                mostFrequent = word;
            }
        }

        System.out.println("Самое частое слово: \"" + mostFrequent + "\" (встречается " + maxFreq + " раз)");

        // 6. Итог
        System.out.println("\n✅ Итог: Методы поиска в Collections экономят время и код!");
        System.out.println("- Минимум/максимум? → min()/max()");
        System.out.println("- Сколько раз встречается? → frequency()");
        System.out.println("- Быстрый поиск в отсортированном списке? → binarySearch()");
        System.out.println("- Нет общих элементов? → disjoint()");
    }
}