package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 20: Демонстрация различных сборщиков мусора
 */
public class Slide20_GarbageCollectorsDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ВИДЫ СБОРЩИКОВ МУСОРА (Слайд 20) ===\n");

        demonstrateGCOverview();
        demonstrateMemoryPatterns();
        showGCRecommendations();
    }

    /**
     * Обзор различных сборщиков мусора
     */
    private static void demonstrateGCOverview() {
        System.out.println("1. ОСНОВНЫЕ ТИПЫ GC:");

        System.out.println("\n   Serial GC (-XX:+UseSerialGC):");
        System.out.println("   • Один поток сборки");
        System.out.println("   • Stop-the-World паузы");
        System.out.println("   • Для приложений до 100MB heap");
        System.out.println("   • Клиентские приложения");

        System.out.println("\n   Parallel GC (-XX:+UseParallelGC):");
        System.out.println("   • Многопоточная сборка");
        System.out.println("   • Максимизация throughput");
        System.out.println("   • Для средних/больших heap");
        System.out.println("   • Фоновые задачи, batch processing");

        System.out.println("\n   CMS GC (-XX:+UseConcMarkSweepGC):");
        System.out.println("   • Низкие паузы (low latency)");
        System.out.println("   • Конкурентная работа с приложением");
        System.out.println("   • Устарел в новых версиях Java");
        System.out.println("   • Web-приложения, GUI");
    }

    /**
     * Демонстрация различных паттернов использования памяти
     */
    private static void demonstrateMemoryPatterns() throws InterruptedException {
        System.out.println("\n2. ДЕМОНСТРАЦИЯ РАБОТЫ GC:");

        // Создаем разные типы объектов
        List<byte[]> shortLive = new ArrayList<>();
        List<byte[]> longLive = new ArrayList<>();

        System.out.println("   Создаем короткоживущие объекты...");
        for (int i = 0; i < 1000; i++) {
            shortLive.add(new byte[1024]); // 1KB
        }

        System.out.println("   Создаем долгоживущие объекты...");
        for (int i = 0; i < 10; i++) {
            longLive.add(new byte[1024 * 1024]); // 1MB
        }

        // Освобождаем короткоживущие объекты
        shortLive.clear();

        System.out.println("   Запускаем GC...");
        long startTime = System.currentTimeMillis();
        System.gc();
        long endTime = System.currentTimeMillis();

        System.out.println("   GC выполнен за " + (endTime - startTime) + "ms");
        System.out.println("   Короткоживущие объекты собраны");
        System.out.println("   Долгоживущие остались в памяти: " + longLive.size());
    }

    /**
     * Рекомендации по выбору GC
     */
    private static void showGCRecommendations() {
        System.out.println("\n3. ВЫБОР СБОРЩИКА МУСОРА:");

        System.out.println("   Маленькие приложения (<100MB):");
        System.out.println("   • Serial GC или G1 GC");
        System.out.println("   • java -Xmx100m -XX:+UseSerialGC -jar app.jar");

        System.out.println("\n   Высокая пропускная способность:");
        System.out.println("   • Parallel GC");
        System.out.println("   • java -Xmx2g -XX:+UseParallelGC -jar app.jar");

        System.out.println("\n   Низкие задержки (web-приложения):");
        System.out.println("   • G1 GC (современная замена CMS)");
        System.out.println("   • java -Xmx4g -XX:+UseG1GC -jar app.jar");

        System.out.println("\n   Очень большие heap (>16GB):");
        System.out.println("   • ZGC или Shenandoah");
        System.out.println("   • java -Xmx32g -XX:+UseZGC -jar app.jar");

        System.out.println("\n   По умолчанию в современных версиях:");
        System.out.println("   • G1 GC (Java 9+)");
        System.out.println("   • java -jar app.jar");
    }

    /**
     * Демонстрация влияния GC на производительность
     */
    private static void demonstrateGCImpact() {
        System.out.println("\n4. ВЛИЯНИЕ GC НА ПРОИЗВОДИТЕЛЬНОСТЬ:");

        System.out.println("   Serial GC:");
        System.out.println("   + Простой, малый overhead");
        System.out.println("   - Долгие паузы при больших heap");

        System.out.println("\n   Parallel GC:");
        System.out.println("   + Высокий throughput");
        System.out.println("   - Средние паузы");

        System.out.println("\n   CMS GC:");
        System.out.println("   + Короткие паузы");
        System.out.println("   - Высокий CPU usage");
        System.out.println("   - Фрагментация памяти");

        System.out.println("\n   G1 GC:");
        System.out.println("   + Баланс throughput и latency");
        System.out.println("   + Предсказуемые паузы");
        System.out.println("   - Сложная настройка");
    }
}