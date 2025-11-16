package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 21: Демонстрация современных сборщиков мусора
 */
public class Slide21_ModernCollectorsDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== СОВРЕМЕННЫЕ СБОРЩИКИ МУСОРА (Слайд 21) ===\n");

        demonstrateG1GC();
        demonstrateShenandoah();
        demonstrateZGC();
        showUsageRecommendations();
        comparePerformance();
        demonstrateMigration();
    }

    /**
     * Демонстрация G1 Garbage Collector
     */
    private static void demonstrateG1GC() {
        System.out.println("1. G1 GARBAGE COLLECTOR (-XX:+UseG1GC):");

        System.out.println("   • Разбивает heap на регионы (1-32MB)");
        System.out.println("   • Параллельный + конкурентный");
        System.out.println("   • Предсказуемые паузы < 200ms");
        System.out.println("   • По умолчанию в Java 9+");
        System.out.println("   • Для heap 4GB+");

        // Демонстрация работы с регионами
        System.out.println("\n   Демонстрация региональной организации:");
        createMixedSizeObjects();
    }

    /**
     * Демонстрация Shenandoah GC
     */
    private static void demonstrateShenandoah() {
        System.out.println("\n2. SHENANDOAH GC (-XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC):");

        System.out.println("   • Конкурентная эвакуация объектов");
        System.out.println("   • Паузы < 10ms независимо от heap size");
        System.out.println("   • JDK 12+");
        System.out.println("   • Для low-latency приложений");

        System.out.println("\n   Ключевое преимущество:");
        System.out.println("   Перемещение объектов БЕЗ остановки приложения");
    }

    /**
     * Демонстрация Z Garbage Collector
     */
    private static void demonstrateZGC() {
        System.out.println("\n3. Z GC (-XX:+UnlockExperimentalVMOptions -XX:+UseZGC):");

        System.out.println("   • Паузы < 10ms даже для TB heap");
        System.out.println("   • Полностью конкурентный");
        System.out.println("   • JDK 11+ (production в JDK 15+)");
        System.out.println("   • Масштабируемость до терабайт");

        System.out.println("\n   Особенности:");
        System.out.println("   • Возвращает память ОС автоматически");
        System.out.println("   • Цветные указатели (colored pointers)");
        System.out.println("   • Load barriers");
    }

    /**
     * Рекомендации по использованию
     */
    private static void showUsageRecommendations() {
        System.out.println("\n4. ВЫБОР СОВРЕМЕННОГО GC:");

        System.out.println("   G1 GC (рекомендуемый по умолчанию):");
        System.out.println("   • Большинство enterprise приложений");
        System.out.println("   • Heap 4GB - 32GB");
        System.out.println("   • Баланс throughput и latency");
        System.out.println("   • java -Xmx8g -XX:+UseG1GC -jar app.jar");

        System.out.println("\n   Shenandoah (низкие паузы):");
        System.out.println("   • Real-time системы");
        System.out.println("   • GUI приложения");
        System.out.println("   • Требования к отзывчивости");
        System.out.println("   • java -Xmx16g -XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC -jar app.jar");

        System.out.println("\n   ZGC (огромные heap):");
        System.out.println("   • In-memory базы данных");
        System.out.println("   • Big data processing");
        System.out.println("   • Heap 32GB+");
        System.out.println("   • java -Xmx64g -XX:+UnlockExperimentalVMOptions -XX:+UseZGC -jar app.jar");
    }

    /**
     * Создание объектов разного размера для демонстрации регионов G1
     */
    private static void createMixedSizeObjects() {
        System.out.println("\n   Создание объектов для демонстрации:");

        List<byte[]> smallObjects = new ArrayList<>();
        List<byte[]> mediumObjects = new ArrayList<>();
        List<byte[]> largeObjects = new ArrayList<>();

        // Маленькие объекты (могут быть в одном регионе)
        for (int i = 0; i < 10; i++) {
            smallObjects.add(new byte[1024]); // 1KB
        }

        // Средние объекты (по одному на регион)
        for (int i = 0; i < 3; i++) {
            mediumObjects.add(new byte[1024 * 1024]); // 1MB
        }

        // Большие объекты (humongous - несколько регионов)
        for (int i = 0; i < 2; i++) {
            largeObjects.add(new byte[1024 * 1024 * 10]); // 10MB
        }

        System.out.println("   Создано: " + smallObjects.size() + " маленьких объектов");
        System.out.println("   Создано: " + mediumObjects.size() + " средних объектов");
        System.out.println("   Создано: " + largeObjects.size() + " больших объектов");
        System.out.println("   G1 оптимизирует сборку для каждого типа объектов");
    }

    /**
     * Сравнение производительности современных GC
     */
    private static void comparePerformance() {
        System.out.println("\n5. СРАВНЕНИЕ ПРОИЗВОДИТЕЛЬНОСТИ:");

        System.out.println("   Throughput (обработка в единицу времени):");
        System.out.println("   • G1: ██████████ (высокий)");
        System.out.println("   • Shenandoah: ████████ (средний)");
        System.out.println("   • ZGC: ████████ (средний)");

        System.out.println("\n   Latency (максимальная пауза):");
        System.out.println("   • G1: █████ (200ms)");
        System.out.println("   • Shenandoah: █ (10ms)");
        System.out.println("   • ZGC: █ (10ms)");

        System.out.println("\n   Потребление CPU:");
        System.out.println("   • G1: █████ (умеренное)");
        System.out.println("   • Shenandoah: ███████ (высокое)");
        System.out.println("   • ZGC: ██████ (среднее)");
    }

    /**
     * Демонстрация миграции с legacy GC
     */
    private static void demonstrateMigration() {
        System.out.println("\n6. МИГРАЦИЯ С УСТАРЕВШИХ GC:");

        System.out.println("   С CMS на G1:");
        System.out.println("   -XX:+UseConcMarkSweepGC → -XX:+UseG1GC");

        System.out.println("\n   С Parallel на G1:");
        System.out.println("   -XX:+UseParallelGC → -XX:+UseG1GC");

        System.out.println("\n   Для экстремальных требований:");
        System.out.println("   -XX:+UseG1GC → -XX:+UseZGC");
        System.out.println("   (требует тестирования!)");
    }
}