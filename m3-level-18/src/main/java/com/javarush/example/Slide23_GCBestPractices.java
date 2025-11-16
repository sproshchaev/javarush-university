package com.javarush.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 23: Рекомендации по работе со сборкой мусора
 */
public class Slide23_GCBestPractices {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== РЕКОМЕНДАЦИИ ПО СБОРКЕ МУСОРА (Слайд 23) ===\n");

        demonstrateManualGCAvoidance();
        demonstrateMonitoringTools();
        demonstrateDefaultSettings();
        demonstrateJVMFlags();
        demonstrateGCSelection();
        showChecklist();
    }

    /**
     * 1. Избегайте ручных триггеров GC
     */
    private static void demonstrateManualGCAvoidance() {
        System.out.println("1. ИЗБЕГАЙТЕ РУЧНЫХ ТРИГГЕРОВ GC:");

        System.out.println("   ❌ НЕПРАВИЛЬНО:");
        System.out.println("   System.gc(); // Плохая практика!");
        System.out.println("   Runtime.getRuntime().gc(); // Тоже плохо!");

        System.out.println("\n   ✅ ПРАВИЛЬНО:");
        System.out.println("   // Доверьтесь JVM - она знает лучше!");
        System.out.println("   // Создавайте и освобождайте объекты нормально");

        // Демонстрация: создание и автоматическое освобождение объектов
        System.out.println("\n   Демонстрация нормальной работы:");
        createAndReleaseObjects();
    }

    /**
     * 2. Используйте инструменты для анализа
     */
    private static void demonstrateMonitoringTools() {
        System.out.println("\n2. ИНСТРУМЕНТЫ ДЛЯ АНАЛИЗА GC:");

        System.out.println("   • VisualVM (бесплатный, входит в JDK)");
        System.out.println("   • JConsole (мониторинг в реальном времени)");
        System.out.println("   • Java Mission Control (профилирование)");
        System.out.println("   • GC logs (подробная информация)");

        System.out.println("\n   Пример включения GC логов:");
        System.out.println("   -Xlog:gc* - для детальных логов (Java 9+)");
        System.out.println("   -XX:+PrintGCDetails - для старых версий");

        // Демонстрация получения информации о памяти
        Runtime runtime = Runtime.getRuntime();
        System.out.println("\n   Текущее использование памяти:");
        System.out.println("   Используется: " + (runtime.totalMemory() - runtime.freeMemory()) / 1024 + " KB");
        System.out.println("   Всего: " + runtime.totalMemory() / 1024 + " KB");
    }

    /**
     * 3. Настройки по умолчанию
     */
    private static void demonstrateDefaultSettings() {
        System.out.println("\n3. НАСТРОЙКИ ПО УМОЛЧАНИЮ:");

        System.out.println("   ✅ Java 8: Parallel GC (через ergonomics)");
        System.out.println("   ✅ Java 9+: G1 GC (новый дефолт)");
        System.out.println("   ✅ Размер heap: автоматическая настройка");

        System.out.println("\n   Начните с простого:");
        System.out.println("   java -jar your-application.jar");

        System.out.println("\n   Только при проблемах добавляйте настройки:");
        System.out.println("   java -Xmx2g -jar your-application.jar");
    }

    /**
     * 4. Флаги JVM для настройки
     */
    private static void demonstrateJVMFlags() {
        System.out.println("\n4. ПОЛЕЗНЫЕ ФЛАГИ JVM:");

        System.out.println("   Базовые настройки памяти:");
        System.out.println("   -Xms512m - начальный размер heap");
        System.out.println("   -Xmx2g   - максимальный размер heap");
        System.out.println("   -Xmn256m - размер молодого поколения");

        System.out.println("\n   Настройки GC:");
        System.out.println("   -XX:+UseG1GC           - использовать G1 GC");
        System.out.println("   -XX:MaxGCPauseMillis=200 - целевая пауза GC");
        System.out.println("   -XX:ParallelGCThreads=4 - потоки для Parallel GC");

        System.out.println("\n   Логирование:");
        System.out.println("   -Xlog:gc* - детальные логи GC");
        System.out.println("   -XX:+PrintGC - базовые логи GC");
    }

    /**
     * 5. Выбор сборщика мусора
     */
    private static void demonstrateGCSelection() {
        System.out.println("\n5. ВЫБОР СБОРЩИКА МУСОРА:");

        System.out.println("   Маленькие приложения (<100MB):");
        System.out.println("   • Serial GC: -XX:+UseSerialGC");

        System.out.println("\n   Высокий throughput:");
        System.out.println("   • Parallel GC: -XX:+UseParallelGC");

        System.out.println("\n   Баланс производительности:");
        System.out.println("   • G1 GC: -XX:+UseG1GC (рекомендуется)");

        System.out.println("\n   Низкие задержки:");
        System.out.println("   • ZGC: -XX:+UseZGC");
        System.out.println("   • Shenandoah: -XX:+UseShenandoahGC");
    }

    /**
     * Создание и освобождение объектов (демонстрация хорошей практики)
     */
    private static void createAndReleaseObjects() {
        System.out.println("   Создаем временные объекты...");

        // Объекты создаются и автоматически становятся недостижимыми
        for (int i = 0; i < 5; i++) {
            String temporaryObject = "temp-" + i;
            byte[] data = new byte[1024]; // 1KB

            // Работа с объектом
            processObject(temporaryObject);

            // После выхода из цикла объекты становятся недостижимыми
        }

        System.out.println("   Объекты автоматически станут кандидатами на GC");
        System.out.println("   JVM сама решит когда их собрать");
    }

    /**
     * Обработка объекта (заглушка)
     */
    private static void processObject(String obj) {
        // Симуляция работы с объектом
        if (obj.length() > 0) {
            // Объект используется
        }
    }

    /**
     * Чеклист лучших практик
     */
    private static void showChecklist() {
        System.out.println("\n6. ЧЕКЛИСТ ЛУЧШИХ ПРАКТИК:");

        System.out.println("   ✅ Не вызывайте System.gc() вручную");
        System.out.println("   ✅ Используйте инструменты мониторинга");
        System.out.println("   ✅ Начните с настроек по умолчанию");
        System.out.println("   ✅ Тестируйте разные GC при проблемах");
        System.out.println("   ✅ Включайте GC логи в продакшене");
        System.out.println("   ✅ Настраивайте размер heap под нагрузку");

        System.out.println("\n   ❌ Не угадывайте настройки");
        System.out.println("   ❌ Не копируйте настройки без тестирования");
        System.out.println("   ❌ Не игнорируйте GC паузы");
        System.out.println("   ❌ Не используйте устаревшие GC без причины");
    }

    /**
     * Демонстрация плохих практик (что НЕ делать)
     */
    private static void demonstrateBadPractices() {
        System.out.println("\n7. ЧЕГО ИЗБЕГАТЬ:");

        System.out.println("   ❌ Частый вызов System.gc():");
        System.out.println("   while (true) {");
        System.out.println("       processData();");
        System.out.println("       System.gc(); // НЕТ!");
        System.out.println("   }");

        System.out.println("\n   ❌ Слишком маленький heap:");
        System.out.println("   java -Xmx64m -jar large-app.jar");
        System.out.println("   // → Частый GC → низкая производительность");

        System.out.println("\n   ❌ Слишком большой heap:");
        System.out.println("   java -Xmx32g -jar small-app.jar");
        System.out.println("   // → Долгие паузы GC");

        System.out.println("\n   ❌ Устаревшие настройки:");
        System.out.println("   -XX:+UseConcMarkSweepGC // Устарел в Java 14");
        System.out.println("   // Используйте G1 или ZGC вместо CMS");
    }
}