package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Пример, демонстрирующий внутреннее устройство ArrayList.
 * Показывает, как работает динамическое расширение массива.
 * Рефлексия удалена — ёмкость выводится на основе известного поведения ArrayList.
 */
public class Slide12_ArrayListInternalsExample {

    public static void main(String[] args) {
        demonstrateBasicStructure();
        demonstrateDynamicGrowth();
        demonstrateCapacityCalculation();
        demonstratePerformanceImpact();
        demonstrateOptimizationWithInitialCapacity();
        demonstratePracticalImplications();
    }

    private static void demonstrateBasicStructure() {
        System.out.println("=== Базовая структура ArrayList ===");

        // Создаём пустой список
        ArrayList<String> list = new ArrayList<>();

        // Внутри: массив elementData и переменная size
        System.out.println("Начальный размер (size): " + list.size());
        System.out.println("Начальная ёмкость (по умолчанию): 10");

        // Добавляем элемент
        list.add("First");
        System.out.println("После добавления одного элемента:");
        System.out.println("  Размер: " + list.size());
        System.out.println("  Ёмкость: 10 (известно из спецификации ArrayList)");
    }

    private static void demonstrateDynamicGrowth() {
        System.out.println("\n=== Динамическое расширение ===");

        ArrayList<String> list = new ArrayList<>();

        // Заполняем до 10 элементов
        for (int i = 0; i < 10; i++) {
            list.add("Item" + i);
        }
        System.out.println("После 10 элементов: size=" + list.size() + ", capacity=10");

        // Добавляем 11-й элемент — должен произойти рост
        list.add("Item10");
        System.out.println("После 11-го элемента: size=" + list.size() + ", capacity=15");
        System.out.println("✅ Ёмкость увеличилась с 10 до 15!");

        // Добавляем ещё 5 элементов → всего 16
        for (int i = 11; i < 16; i++) {
            list.add("Item" + i);
        }
        System.out.println("После 16 элементов: size=" + list.size() + ", capacity=15");

        // Добавляем 17-й — снова рост
        list.add("Item16");
        System.out.println("После 17-го элемента: size=" + list.size() + ", capacity=22");
        System.out.println("✅ Ёмкость увеличилась с 16 до 22!");
    }

    private static void demonstrateCapacityCalculation() {
        System.out.println("\n=== Как рассчитывается новая ёмкость ===");

        int oldCapacity = 10;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // oldCapacity + oldCapacity / 2
        System.out.println("Старая ёмкость: " + oldCapacity);
        System.out.println("Новая ёмкость: " + newCapacity); // 10 + 5 = 15

        oldCapacity = 15;
        newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println("Старая ёмкость: " + oldCapacity);
        System.out.println("Новая ёмкость: " + newCapacity); // 15 + 7 = 22

        oldCapacity = 22;
        newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println("Старая ёмкость: " + oldCapacity);
        System.out.println("Новая ёмкость: " + newCapacity); // 22 + 11 = 33
    }

    private static void demonstratePerformanceImpact() {
        System.out.println("\n=== Влияние на производительность ===");

        long start, end;

        // Без предварительной ёмкости — частые пересоздания массива
        ArrayList<String> list1 = new ArrayList<>();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            list1.add("Item" + i);
        }
        end = System.currentTimeMillis();
        System.out.println("Добавление 100K элементов без initialCapacity: " + (end - start) + " мс");

        // С предварительной ёмкостью — меньше пересозданий
        ArrayList<String> list2 = new ArrayList<>(100_000);
        start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            list2.add("Item" + i);
        }
        end = System.currentTimeMillis();
        System.out.println("Добавление 100K элементов с initialCapacity: " + (end - start) + " мс");

        System.out.println("⚠️ Без initialCapacity — потенциально медленнее из-за копирования массива!");
    }

    private static void demonstrateOptimizationWithInitialCapacity() {
        System.out.println("\n=== Оптимизация с помощью initialCapacity ===");

        // Если мы знаем, что будет 1000 элементов
        ArrayList<String> list = new ArrayList<>(1000);

        // Добавляем 1000 элементов — не будет ни одного пересоздания массива!
        for (int i = 0; i < 1000; i++) {
            list.add("Item" + i);
        }

        System.out.println("Добавлено 1000 элементов.");
        System.out.println("Размер: " + list.size());
        System.out.println("Ёмкость: ≥1000 (точное значение зависит от реализации, но перераспределения не было)");

        // Сравнение без initialCapacity
        ArrayList<String> listWithoutCapacity = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            listWithoutCapacity.add("Item" + i);
        }
        System.out.println("Без initialCapacity: несколько перераспределений (до ёмкости ≥1000)");
    }

    private static void demonstratePracticalImplications() {
        System.out.println("\n=== Практические выводы ===");

        System.out.println("1. Для больших списков всегда указывайте initialCapacity.");
        System.out.println("2. Вставка/удаление в середину — медленно, т.к. сдвигает элементы.");
        System.out.println("3. Для фиксированных данных — лучше массив или unmodifiableList.");
        System.out.println("4. ArrayList не потокобезопасен. Используйте synchronizedList или CopyOnWriteArrayList при необходимости.");

        // Пример оптимального использования
        List<String> optimizedList = new ArrayList<>(100); // заранее знаем размер
        for (int i = 0; i < 100; i++) {
            optimizedList.add("Item" + i);
        }
        System.out.println("Оптимизированный список создан.");
    }
}