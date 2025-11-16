package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 22: Практический выбор сборщика мусора
 */
public class Slide22_GCSelectionGuide {

    public static void main(String[] args) {
        System.out.println("=== ВЫБОР СБОРЩИКА МУСОРА (Слайд 22) ===\n");

        showDefaultApproach();
        demonstrateScenarioBasedSelection();
        showPracticalExamples();
        demonstrateDecisionTree();
    }

    /**
     * Подход по умолчанию - довериться JVM
     */
    private static void showDefaultApproach() {
        System.out.println("1. ПОДХОД ПО УМОЛЧАНИЮ:");

        System.out.println("   • Java 8: Parallel GC (через ergonomics)");
        System.out.println("   • Java 9+: G1 GC (новый дефолт)");
        System.out.println("   • JVM автоматически подбирает параметры");

        System.out.println("\n   Рекомендация:");
        System.out.println("   Начните с настроек по умолчанию!");
        System.out.println("   java -jar your-app.jar");
        System.out.println("   Только при проблемах - настраивайте GC");
    }

    /**
     * Выбор GC по сценариям использования
     */
    private static void demonstrateScenarioBasedSelection() {
        System.out.println("\n2. ВЫБОР ПО СЦЕНАРИЯМ:");

        System.out.println("   СЦЕНАРИЙ: Маленькое CLI приложение");
        System.out.println("   • Heap: < 100MB");
        System.out.println("   • Требования: минимальный overhead");
        System.out.println("   • GC: Serial");
        System.out.println("   • Параметры: -Xmx100m -XX:+UseSerialGC");

        System.out.println("\n   СЦЕНАРИЙ: Batch processing");
        System.out.println("   • Heap: 1-4GB");
        System.out.println("   • Требования: максимальный throughput");
        System.out.println("   • GC: Parallel");
        System.out.println("   • Параметры: -Xmx2g -XX:+UseParallelGC");

        System.out.println("\n   СЦЕНАРИЙ: Web-приложение");
        System.out.println("   • Heap: 4-16GB");
        System.out.println("   • Требования: баланс throughput/latency");
        System.out.println("   • GC: G1");
        System.out.println("   • Параметры: -Xmx8g -XX:+UseG1GC");

        System.out.println("\n   СЦЕНАРИЙ: Real-time система");
        System.out.println("   • Heap: 16GB+");
        System.out.println("   • Требования: паузы < 10ms");
        System.out.println("   • GC: ZGC или Shenandoah");
        System.out.println("   • Параметры: -Xmx32g -XX:+UseZGC");
    }

    /**
     * Практические примеры настройки
     */
    private static void showPracticalExamples() {
        System.out.println("\n3. ПРАКТИЧЕСКИЕ ПРИМЕРЫ:");

        // Демонстрация создания объектов для разных сценариев
        System.out.println("   Демонстрация различных workload:");

        // Сценарий 1: Много временных объектов
        System.out.println("\n   Сценарий: Web-request processing");
        simulateWebWorkload();

        // Сценарий 2: Большие объекты в памяти
        System.out.println("\n   Сценарий: In-memory cache");
        simulateCacheWorkload();

        // Сценарий 3: Постоянные данные
        System.out.println("\n   Сценарий: Batch processing");
        simulateBatchWorkload();
    }

    /**
     * Дерево решений для выбора GC
     */
    private static void demonstrateDecisionTree() {
        System.out.println("\n4. ДЕРЕВО РЕШЕНИЙ ДЛЯ ВЫБОРА GC:");

        System.out.println("   ┌──────────────────────────────────┐");
        System.out.println("   │         Начало: Ваше приложение  │");
        System.out.println("   └──────────────────────────────────┘");
        System.out.println("                    ↓");
        System.out.println("   ┌──────────────────────────────────┐");
        System.out.println("   │ Heap < 100MB?                    │");
        System.out.println("   └──────────────────────────────────┘");
        System.out.println("             ↓ Да              ↓ Нет");
        System.out.println("   UseSerialGC          ┌──────────────────┐");
        System.out.println("                        │ Паузы < 200ms?   │");
        System.out.println("                        └──────────────────┘");
        System.out.println("                ↓ Да                ↓ Нет");
        System.out.println("   ┌──────────────────┐    ┌──────────────────┐");
        System.out.println("   │ Heap < 32GB?     │    │ UseParallelGC    │");
        System.out.println("   └──────────────────┘    └──────────────────┘");
        System.out.println("        ↓ Да        ↓ Нет");
        System.out.println("   UseG1GC     UseZGC/Shenandoah");
    }

    /**
     * Демонстрация workload для web-приложения
     */
    private static void simulateWebWorkload() {
        System.out.println("   • Много короткоживущих объектов");
        System.out.println("   • Быстрое создание/удаление");
        System.out.println("   • Идеально для G1 GC");

        // Симуляция web-запросов
        for (int i = 0; i < 5; i++) {
            byte[] requestData = new byte[1024 * 50]; // 50KB на запрос
            String response = processRequest(requestData);
            System.out.println("   Обработан запрос " + i + ": " + response);
        }
    }

    /**
     * Демонстрация workload для in-memory cache
     */
    private static void simulateCacheWorkload() {
        System.out.println("   • Долгоживущие объекты в Old Gen");
        System.out.println("   • Периодическое обновление");
        System.out.println("   • Подходит для G1 или ZGC");

        List<byte[]> cache = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            byte[] cachedData = new byte[1024 * 1024]; // 1MB кэш-объект
            cache.add(cachedData);
        }
        System.out.println("   Создан кэш: " + cache.size() + " объектов");
    }

    /**
     * Демонстрация workload для batch processing
     */
    private static void simulateBatchWorkload() {
        System.out.println("   • Интенсивная обработка данных");
        System.out.println("   • Throughput важнее latency");
        System.out.println("   • Идеально для Parallel GC");

        long startTime = System.currentTimeMillis();
        int processed = 0;

        // Симуляция batch processing
        for (int i = 0; i < 1000; i++) {
            byte[] batchData = new byte[1024]; // 1KB на элемент
            processed++;
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("   Обработано " + processed + " элементов за " + duration + "ms");
    }

    /**
     * Метод для обработки запроса (заглушка)
     */
    private static String processRequest(byte[] data) {
        // Симуляция обработки
        return "Response-" + data.length;
    }

    /**
     * Чеклист для принятия решения
     */
    private static void showChecklist() {
        System.out.println("\n5. ЧЕКЛИСТ ДЛЯ ВЫБОРА GC:");

        System.out.println("   □ Размер heap? (__ MB/GB)");
        System.out.println("   □ Количество CPU ядер? (__)");
        System.out.println("   □ Требования к latency? (__ ms)");
        System.out.println("   □ Throughput важнее отзывчивости? (Да/Нет)");
        System.out.println("   □ Частое создание временных объектов? (Да/Нет)");
        System.out.println("   □ Много долгоживущих объектов? (Да/Нет)");

        System.out.println("\n   Ответы подскажут оптимальный GC!");
    }
}