package com.javarush.example;

import java.util.*;

public class Slide08_AnonymousComparator {

    public static void main(String[] args) {
        System.out.println("=== Пример анонимного компаратора в TreeMap ===\n");

        // Определяем класс Student — НЕ реализует Comparable
        class Student {
            String name;
            int math, english, design;

            public Student(String name, int math, int english, int design) {
                this.name = name;
                this.math = math;
                this.english = english;
                this.design = design;
            }

            public int getTotalScore() {
                return math + english + design;
            }

            @Override
            public String toString() {
                return name + " [math=" + math + ", eng=" + english + ", des=" + design + "]";
            }
        }

        // 1. Создаем TreeMap с анонимным компаратором — сортируем по сумме баллов
        TreeMap<Student, String> studentRanking = new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int total1 = s1.getTotalScore();
                int total2 = s2.getTotalScore();
                return Integer.compare(total1, total2); // по возрастанию
            }
        });

        // Добавляем студентов в случайном порядке
        studentRanking.put(new Student("Анна", 90, 85, 95), "Отличница");
        studentRanking.put(new Student("Борис", 80, 90, 88), "Хорошист");
        studentRanking.put(new Student("Виктория", 95, 92, 98), "Лучшая");
        studentRanking.put(new Student("Григорий", 75, 80, 78), "Усердный");

        System.out.println("✅ Студенты отсортированы по сумме баллов (по возрастанию):");
        for (Student student : studentRanking.keySet()) {
            System.out.println(student + " → " + studentRanking.get(student));
        }

        // 2. Пример с null-ключом — нужно явно указать поведение
        TreeMap<Student, String> studentMapWithNull = new TreeMap<>(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (s1 == null && s2 == null) return 0;
                if (s1 == null) return -1;  // null считаем меньше любого объекта
                if (s2 == null) return 1;
                return Integer.compare(s1.getTotalScore(), s2.getTotalScore());
            }
        });

        studentMapWithNull.put(null, "Неизвестный студент");
        studentMapWithNull.put(new Student("Петр", 85, 88, 86), "Средний");

        System.out.println("\n✅ TreeMap с null-ключом (обработка в компараторе):");
        for (Student student : studentMapWithNull.keySet()) {
            if (student == null) {
                System.out.println("NULL → " + studentMapWithNull.get(student));
            } else {
                System.out.println(student + " → " + studentMapWithNull.get(student));
            }
        }

        // 3. Что будет, если не обработать null?
        try {
            TreeMap<Student, String> brokenMap = new TreeMap<>(new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    return Integer.compare(s1.getTotalScore(), s2.getTotalScore()); // NPE при s1 или s2 == null
                }
            });
            brokenMap.put(null, "Ошибка!");
        } catch (Exception e) {
            System.out.println("\n❌ Без обработки null — получаем:");
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        // 4. Современный способ — лямбда вместо анонимного класса
        TreeMap<Student, String> modernMap = new TreeMap<>((s1, s2) ->
                Integer.compare(s1.getTotalScore(), s2.getTotalScore())
        );

        modernMap.put(new Student("Даша", 92, 94, 96), "Топ");
        System.out.println("\n✅ Современный способ: лямбда-выражение:");
        for (Student s : modernMap.keySet()) {
            System.out.println(s + " → " + modernMap.get(s));
        }
    }
}