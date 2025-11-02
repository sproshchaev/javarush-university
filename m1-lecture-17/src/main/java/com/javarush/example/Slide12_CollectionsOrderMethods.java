package com.javarush.example;

import java.util.*;

public class Slide12_CollectionsOrderMethods {

    public static void main(String[] args) {
        System.out.println("=== Изменение порядка элементов через Collections ===\n");

        // 1. reverse() — разворот списка
        System.out.println("✅ Collections.reverse():");
        List<String> names = new ArrayList<>(Arrays.asList("Анна", "Борис", "Виктория", "Григорий"));
        System.out.println("Исходный список: " + names);

        Collections.reverse(names);
        System.out.println("После reverse(): " + names);

        // 2. sort() — сортировка по естественному порядку
        System.out.println("\n✅ Collections.sort():");
        List<Integer> scores = new ArrayList<>(Arrays.asList(85, 92, 78, 96, 88));
        System.out.println("Оценки до сортировки: " + scores);

        Collections.sort(scores);
        System.out.println("Оценки после сортировки: " + scores);

        // Сортировка строк — по алфавиту
        List<String> fruits = new ArrayList<>(Arrays.asList("Банан", "Яблоко", "Апельсин"));
        Collections.sort(fruits);
        System.out.println("Фрукты по алфавиту: " + fruits);

        // Сортировка с компаратором — по длине строки
        Collections.sort(fruits, Comparator.comparingInt(String::length));
        System.out.println("Фрукты по длине: " + fruits);

        // 3. rotate() — циклический сдвиг
        System.out.println("\n✅ Collections.rotate():");
        List<String> days = new ArrayList<>(Arrays.asList("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"));
        System.out.println("Неделя: " + days);

        Collections.rotate(days, 2); // сдвигаем на 2 вперёд
        System.out.println("После rotate(2): " + days); // [Сб, Вс, Пн, Вт, Ср, Чт, Пт]

        Collections.rotate(days, -3); // сдвигаем на 3 назад
        System.out.println("После rotate(-3): " + days); // [Ср, Чт, Пт, Сб, Вс, Пн, Вт]

        // 4. shuffle() — случайное перемешивание
        System.out.println("\n✅ Collections.shuffle():");
        List<String> cards = new ArrayList<>(Arrays.asList("Туз", "Король", "Дама", "Валет", "10"));
        System.out.println("Колода до перемешивания: " + cards);

        Collections.shuffle(cards);
        System.out.println("Колода после перемешивания: " + cards);

        // Показываем, что каждый вызов даёт разный результат
        System.out.println("Еще два перемешивания:");
        for (int i = 0; i < 2; i++) {
            Collections.shuffle(cards);
            System.out.println("Перемешивание #" + (i+1) + ": " + cards);
        }

        // 5. Пример: создание "случайного" расписания
        System.out.println("\n💡 Пример: случайное расписание уроков");
        List<String> subjects = new ArrayList<>(Arrays.asList(
                "Математика", "Физика", "Химия", "История", "Литература"
        ));

        System.out.println("Исходные предметы: " + subjects);
        Collections.shuffle(subjects);
        System.out.println("Расписание на день: " + subjects);

        // 6. Важное предупреждение
        System.out.println("\n⚠️ Внимание: все методы изменяют исходный список!");
        List<String> original = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> copy = new ArrayList<>(original);

        Collections.reverse(copy);
        System.out.println("Оригинал: " + original); // не изменился
        System.out.println("Копия после reverse: " + copy); // изменилась

        // 7. Итог
        System.out.println("\n✅ Итог: Методы управления порядком — мощный инструмент!");
        System.out.println("- Нужно перевернуть? → reverse()");
        System.out.println("- Нужно отсортировать? → sort()");
        System.out.println("- Нужно сдвинуть циклически? → rotate()");
        System.out.println("- Нужна случайность? → shuffle()");
    }
}
