package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 18: Демонстрация старшего поколения и Major GC
 */
public class Slide18_OldGenerationDemo {

    // Коллекция для хранения долгоживущих объектов
    private static final List<Object> OLD_GENERATION_RESIDENTS = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== СТАРШЕЕ ПОКОЛЕНИЕ И MAJOR GC (Слайд 18) ===\n");

        demonstrateAgingProcess();
        demonstrateOldGenerationBehavior();
        demonstrateHeapSettings();
        demonstrateMemoryPatterns();

        System.out.println("\n=== РЕКОМЕНДАЦИИ ===");
        System.out.println("• Долгоживущие объекты → Old Generation после ~15 Minor GC");
        System.out.println("• Major GC затрагивает всю кучу → долгие паузы");
        System.out.println("• Настройка: -Xms и -Xmx для управления размером кучи");
        System.out.println("• Избегайте утечек памяти в Old Generation");
    }

    /**
     * Демонстрация процесса старения объектов
     */
    private static void demonstrateAgingProcess() {
        System.out.println("1. ПРОЦЕСС СТАРЕНИЯ ОБЪЕКТОВ:");

        System.out.println("   Создаем объекты, которые будут долгожителями...");
        List<BusinessObject> businessObjects = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            BusinessObject obj = new BusinessObject("BizObj-" + (i + 1), 2048);
            businessObjects.add(obj);
            System.out.println("   Создан: " + obj + " [в Eden]");
        }

        // Эмулируем процесс нескольких Minor GC
        System.out.println("\n   Эмуляция циклов Minor GC:");
        for (int gcCycle = 1; gcCycle <= 15; gcCycle++) {
            if (gcCycle == 1 || gcCycle == 8 || gcCycle == 15) {
                String location = gcCycle == 1 ? "Survivor" :
                        gcCycle == 15 ? "Old Generation" : "Survivor";
                System.out.println("   После GC #" + gcCycle + " → " + location);
            }
        }

        // Перемещаем в Old Generation
        OLD_GENERATION_RESIDENTS.addAll(businessObjects);
        System.out.println("\n   Все 5 объектов достигли порога и перемещены в Old Generation");
    }

    /**
     * Демонстрация поведения объектов в Old Generation
     */
    private static void demonstrateOldGenerationBehavior() throws InterruptedException {
        System.out.println("\n2. ПОВЕДЕНИЕ В OLD GENERATION:");

        Runtime runtime = Runtime.getRuntime();

        // Заполняем Old Generation большими объектами
        System.out.println("   Заполняем Old Generation большими объектами...");
        List<byte[]> bigObjects = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            // Создаем объекты, которые сразу попадут в Old Generation
            byte[] bigData = new byte[1024 * 1024]; // 1MB каждый
            bigObjects.add(bigData);
            OLD_GENERATION_RESIDENTS.add(bigData);

            if (i < 3) {
                System.out.println("   Создан большой объект " + (i + 1) + " [1MB]");
            }
        }
        System.out.println("   Создано 20 больших объектов (20MB) в Old Generation");

        // Покажем использование памяти
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("   Используется памяти: " + (usedMemory / 1024 / 1024) + " MB");

        // Демонстрация Major GC
        System.out.println("\n3. MAJOR GC (FULL GC):");
        System.out.println("   Запускаем полную сборку мусора...");

        long memoryBefore = usedMemory;
        System.gc();
        Thread.sleep(1000); // Даем время на Major GC

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("   Память до Major GC: " + (memoryBefore / 1024 / 1024) + " MB");
        System.out.println("   Память после Major GC: " + (memoryAfter / 1024 / 1024) + " MB");
        System.out.println("   Объекты в Old Generation: " + OLD_GENERATION_RESIDENTS.size());

        // Освобождаем часть объектов
        System.out.println("\n   Освобождаем 10 объектов...");
        for (int i = 0; i < 10; i++) {
            OLD_GENERATION_RESIDENTS.remove(bigObjects.get(i));
        }

        System.gc();
        Thread.sleep(500);

        long memoryAfterFree = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("   Память после освобождения: " + (memoryAfterFree / 1024 / 1024) + " MB");
        System.out.println("   Major GC освободил память от недостижимых объектов");
    }

    /**
     * Демонстрация настроек кучи
     */
    private static void demonstrateHeapSettings() {
        System.out.println("\n4. НАСТРОЙКИ КУЧИ (HEAP):");

        Runtime runtime = Runtime.getRuntime();

        System.out.println("   -Xms (Initial Heap Size): " +
                (runtime.totalMemory() / 1024 / 1024) + " MB");
        System.out.println("   -Xmx (Max Heap Size): " +
                (runtime.maxMemory() / 1024 / 1024) + " MB");
        System.out.println("   Available Processors: " +
                runtime.availableProcessors());

        System.out.println("\n   Примеры настроек JVM:");
        System.out.println("   • java -Xms512m -Xmx2g -jar app.jar");
        System.out.println("   • java -Xms1g -Xmx4g -XX:+UseG1GC -jar app.jar");
    }

    /**
     * Демонстрация паттернов использования памяти
     */
    private static void demonstrateMemoryPatterns() {
        System.out.println("\n5. ПАТТЕРНЫ ИСПОЛЬЗОВАНИЯ ПАМЯТИ:");

        System.out.println("   ХОРОШИЕ ПАТТЕРНЫ:");
        System.out.println("   • Временные объекты умирают в Young Generation");
        System.out.println("   • Кэши с SoftReference освобождаются при нехватке памяти");
        System.out.println("   • Пулы объектов для часто используемых данных");

        System.out.println("\n   ПЛОХИЕ ПАТТЕРНЫ:");
        System.out.println("   • Статические коллекции, которые растут бесконечно");
        System.out.println("   • Утечки памяти в слушателях событий");
        System.out.println("   • Большие объекты, создаваемые в циклах");

        // Пример плохого паттерна
        System.out.println("\n   Пример плохого кода:");
        System.out.println("   static List<String> cache = new ArrayList<>();");
        System.out.println("   // Добавляем всё подряд без очистки → утечка в Old Gen");
    }

    /**
     * Класс для демонстрации бизнес-объектов
     */
    static class BusinessObject {
        private final String id;
        private final byte[] data;
        private final long createdAt;

        public BusinessObject(String id, int size) {
            this.id = id;
            this.data = new byte[size];
            this.createdAt = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return "BusinessObject{" + id + ", size=" + data.length + "}";
        }

        // Методы бизнес-логики...
        public void process() {
            // Симуляция обработки
            for (int i = 0; i < Math.min(data.length, 100); i++) {
                data[i] = (byte) (i % 256);
            }
        }
    }
}