package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 17: Демонстрация молодого поколения и Minor GC
 */
public class Slide17_YoungGenerationDemo {

    // Статическое поле - объекты здесь будут долгоживущими
    private static final List<Object> LONG_LIVED_OBJECTS = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== МОЛОДОЕ ПОКОЛЕНИЕ И MINOR GC (Слайд 17) ===\n");

        demonstrateEdenAllocation();
        demonstrateSurvivorPromotion();
        demonstrateGCBehavior();

        System.out.println("\n=== РЕКОМЕНДАЦИИ ===");
        System.out.println("• Временные объекты умирают в Eden → быстрый Minor GC");
        System.out.println("• Долгоживущие объекты → Survivor → Old Generation");
        System.out.println("• Избегайте утечек в Young Generation");
        System.out.println("• Настройка: -Xmn для размера молодого поколения");
    }

    /**
     * Демонстрация выделения памяти в Eden Space
     */
    private static void demonstrateEdenAllocation() {
        System.out.println("1. ВЫДЕЛЕНИЕ ПАМЯТИ В EDEN SPACE:");

        // Много короткоживущих объектов - идеально для Eden
        System.out.println("   Создаем временные объекты в Eden...");
        for (int i = 0; i < 1000; i++) {
            // Эти объекты скорее всего умрут сразу после создания
            String tempObject = "temporary-" + i;
            byte[] data = new byte[1024]; // 1KB временных данных

            if (i < 3) { // Покажем первые несколько
                System.out.println("   Создан: " + tempObject + " [умрет в Minor GC]");
            }
        }
        System.out.println("   Создано 1000 временных объектов в Eden");
        System.out.println("   После выхода из метода они станут недостижимыми");
    }

    /**
     * Демонстрация перехода объектов в Survivor Space
     */
    private static void demonstrateSurvivorPromotion() {
        System.out.println("\n2. ПЕРЕХОД В SURVIVOR SPACE:");

        // Создаем объекты, которые переживут несколько GC
        List<Object> survivors = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            // Используем final переменную для использования в анонимном классе
            final int objectId = i;
            Object survivor = new Object() {
                final String name = "Survivor-" + objectId;
                final long[] data = new long[100]; // Немного данных

                @Override
                public String toString() {
                    return name + " [hash: " + System.identityHashCode(this) + "]";
                }
            };

            survivors.add(survivor);
            System.out.println("   " + survivor);
        }

        // Сохраняем ссылки, чтобы объекты не были собраны
        LONG_LIVED_OBJECTS.addAll(survivors);
        System.out.println("   5 объектов сохранили ссылки → выживут в Minor GC");
        System.out.println("   Они будут перемещены в Survivor Space");
    }

    /**
     * Демонстрация поведения при сборке мусора
     */
    private static void demonstrateGCBehavior() throws InterruptedException {
        System.out.println("\n3. ПОВЕДЕНИЕ ПРИ MINOR GC:");

        Runtime runtime = Runtime.getRuntime();

        // Покажем использование памяти до и после
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("   Память до создания мусора: " + (memoryBefore / 1024) + " KB");

        // Создаем много мусора
        System.out.println("   Создаем 10,000 временных объектов...");
        for (int i = 0; i < 10000; i++) {
            byte[] garbage = new byte[512]; // 0.5KB мусора
            String tempString = "garbage-" + i;
        }

        long memoryAfterCreation = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("   Память после создания мусора: " + (memoryAfterCreation / 1024) + " KB");

        // Запрашиваем GC (в реальности JVM решает сама)
        System.out.println("   Запускаем System.gc()...");
        System.gc();

        Thread.sleep(500); // Даем время на выполнение GC

        long memoryAfterGC = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("   Память после GC: " + (memoryAfterGC / 1024) + " KB");
        System.out.println("   Освобождено: " + ((memoryAfterCreation - memoryAfterGC) / 1024) + " KB");

        System.out.println("   Выжившие объекты: " + LONG_LIVED_OBJECTS.size());

        // Демонстрация создания LongLivedObject
        System.out.println("\n4. СОЗДАНИЕ ДОЛГОЖИВУЩИХ ОБЪЕКТОВ:");
        LongLivedObject longLived = new LongLivedObject("ImportantData", 5000);
        LONG_LIVED_OBJECTS.add(longLived);
        System.out.println("   Создан: " + longLived);
        System.out.println("   Этот объект будет перемещен в Old Generation после нескольких Minor GC");
    }

    /**
     * Внутренний класс для демонстрации долгоживущих объектов
     */
    static class LongLivedObject {
        private final String id;
        private final byte[] data;

        public LongLivedObject(String id, int size) {
            this.id = id;
            this.data = new byte[size];
        }

        @Override
        public String toString() {
            return "LongLivedObject{" + id + ", size=" + data.length + "}";
        }
    }
}