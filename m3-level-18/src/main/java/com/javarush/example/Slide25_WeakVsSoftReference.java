package com.javarush.example;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Слайд 25: Сравнение WeakReference и SoftReference
 */
public class Slide25_WeakVsSoftReference {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== WEAKREFERENCE vs SOFTREFERENCE (Слайд 25) ===\n");

        demonstrateBehaviorDifference();
        demonstrateWeakReferenceUseCase();
        demonstrateSoftReferenceUseCase();
        showMemoryPressureScenario();
        compareCharacteristics();
        showBestPractices();
    }

    /**
     * Демонстрация разницы в поведении при сборке мусора
     */
    private static void demonstrateBehaviorDifference() throws InterruptedException {
        System.out.println("1. РАЗНИЦА В ПОВЕДЕНИИ ПРИ GC:");

        // Создаем объекты для weak и soft ссылок
        Object weakTarget = new LargeObject("Weak Target", 1024); // 1KB
        Object softTarget = new LargeObject("Soft Target", 1024); // 1KB

        WeakReference<Object> weakRef = new WeakReference<>(weakTarget);
        SoftReference<Object> softRef = new SoftReference<>(softTarget);

        System.out.println("   Созданы WeakReference и SoftReference");
        System.out.println("   WeakReference: " + weakRef.get());
        System.out.println("   SoftReference: " + softRef.get());

        // Освобождаем strong ссылки
        weakTarget = null;
        softTarget = null;

        System.out.println("\n   Освободили strong ссылки...");
        System.out.println("   Запускаем GC...");

        // Многократный GC для демонстрации
        for (int i = 1; i <= 3; i++) {
            System.gc();
            Thread.sleep(100);

            System.out.println("\n   После GC #" + i + ":");
            System.out.println("   WeakReference: " +
                    (weakRef.get() != null ? weakRef.get().toString() : "NULL"));
            System.out.println("   SoftReference: " +
                    (softRef.get() != null ? softRef.get().toString() : "NULL"));
        }

        System.out.println("\n   Вывод: WeakReference собирается быстрее!");
    }

    /**
     * Демонстрация использования WeakReference
     */
    private static void demonstrateWeakReferenceUseCase() {
        System.out.println("\n2. WEAKREFERENCE - ДЛЯ МЕТАДАННЫХ:");

        System.out.println("   Идеально для:");
        System.out.println("   • ClassLoader references");
        System.out.println("   • Event listeners");
        System.out.println("   • Metadata cache");
        System.out.println("   • Temporary associations");

        // Пример: WeakReference для метаданных
        System.out.println("\n   Пример - ClassLoader tracking:");
        System.out.println("   WeakReference<ClassLoader> loaderRef = ...");
        System.out.println("   // Когда ClassLoader становится недостижим,");
        System.out.println("   // weak reference автоматически очищается");

        // Пример: listeners без утечек памяти
        System.out.println("\n   Пример - Event listeners:");
        System.out.println("   List<WeakReference<EventListener>> listeners;");
        System.out.println("   // Listeners автоматически удаляются GC");
        System.out.println("   // когда становятся недостижимыми");
    }

    /**
     * Демонстрация использования SoftReference
     */
    private static void demonstrateSoftReferenceUseCase() {
        System.out.println("\n3. SOFTREFERENCE - ДЛЯ КЭШЕЙ:");

        System.out.println("   Идеально для:");
        System.out.println("   • Image cache");
        System.out.println("   • Large object cache");
        System.out.println("   • Calculation results");
        System.out.println("   • Any cache that can be recomputed");

        // Пример: кэш изображений
        System.out.println("\n   Пример - Image cache:");
        System.out.println("   class ImageCache {");
        System.out.println("       private Map<String, SoftReference<Image>> cache = new HashMap<>();");
        System.out.println("       // Изображения автоматически удаляются");
        System.out.println("       // когда JVM нужна память");
        System.out.println("   }");

        // Демонстрация простого кэша
        demonstrateSimpleCache();
    }

    /**
     * Демонстрация поведения при нехватке памяти
     */
    private static void showMemoryPressureScenario() {
        System.out.println("\n4. ПОВЕДЕНИЕ ПРИ НЕХВАТКЕ ПАМЯТИ:");

        System.out.println("   SoftReference собирается когда:");
        System.out.println("   • Свободной памяти становится мало");
        System.out.println("   • JVM близка к OutOfMemoryError");
        System.out.println("   • Нужно освободить память для новых объектов");

        System.out.println("\n   WeakReference собирается когда:");
        System.out.println("   • Нет strong ссылок на объект");
        System.out.println("   • Произошла любая сборка мусора");
        System.out.println("   • Независимо от состояния памяти");

        System.out.println("\n   Практический пример:");
        System.out.println("   // Много SoftReference объектов в памяти");
        System.out.println("   // JVM начинает испытывать нехватку памяти");
        System.out.println("   // → SoftReference объекты собираются GC");
        System.out.println("   // → Память освобождается, OOM предотвращен");
    }

    /**
     * Сравнительная таблица характеристик
     */
    private static void compareCharacteristics() {
        System.out.println("\n5. СРАВНИТЕЛЬНАЯ ТАБЛИЦА:");

        System.out.println("   ┌─────────────────┬────────────────────┬────────────────────┐");
        System.out.println("   │ Характеристика  │   WeakReference    │   SoftReference    │");
        System.out.println("   ├─────────────────┼────────────────────┼────────────────────┤");
        System.out.println("   │ Время сбора     │ Следующий GC       │ При memory pressure│");
        System.out.println("   │ Гарантии        │ Быстрый сбор       │ Сохранение в памяти│");
        System.out.println("   │ Использование   │ Метаданные         │ Кэши               │");
        System.out.println("   │ Память          │ Минимальный overhead│ Memory-sensitive   │");
        System.out.println("   │ Надежность      │ Ненадежный кэш     │ Надежный при памяти│");
        System.out.println("   └─────────────────┴────────────────────┴────────────────────┘");
    }

    /**
     * Лучшие практики использования
     */
    private static void showBestPractices() {
        System.out.println("\n6. ЛУЧШИЕ ПРАКТИКИ:");

        System.out.println("   WeakReference используйте когда:");
        System.out.println("   • Данные можно легко пересоздать");
        System.out.println("   • Хотите избежать memory leaks");
        System.out.println("   • Работаете с метаданными");
        System.out.println("   • Нужны временные ассоциации");

        System.out.println("\n   SoftReference используйте когда:");
        System.out.println("   • Данные дорого пересоздавать");
        System.out.println("   • Хотите кэш, но боитесь OOM");
        System.out.println("   • Работаете с большими объектами");
        System.out.println("   • Нужен intelligent cache");

        System.out.println("\n   Всегда проверяйте:");
        System.out.println("   if (softRef.get() != null) { ... }");
        System.out.println("   // Объект мог быть собран GC!");
    }

    /**
     * Демонстрация простого кэша на SoftReference
     */
    private static void demonstrateSimpleCache() {
        System.out.println("\n7. ПРИМЕР SOFTREFERENCE КЭША:");

        System.out.println("   class SimpleCache {");
        System.out.println("       private Map<String, SoftReference<byte[]>> cache = new HashMap<>();");
        System.out.println("       ");
        System.out.println("       public byte[] get(String key) {");
        System.out.println("           SoftReference<byte[]> ref = cache.get(key);");
        System.out.println("           return ref != null ? ref.get() : null;");
        System.out.println("       }");
        System.out.println("       ");
        System.out.println("       public void put(String key, byte[] data) {");
        System.out.println("           cache.put(key, new SoftReference<>(data));");
        System.out.println("       }");
        System.out.println("   }");
        System.out.println("   // Кэш автоматически освобождает память при нехватке");
    }

    /**
     * Вспомогательный класс для демонстрации
     */
    static class LargeObject {
        private final String name;
        private final byte[] data;

        public LargeObject(String name, int size) {
            this.name = name;
            this.data = new byte[size];
        }

        @Override
        public String toString() {
            return name + " [size: " + data.length + " bytes]";
        }
    }

    /**
     * Демонстрация проблемы memory leak без weak references
     */
    private static void demonstrateMemoryLeakPrevention() {
        System.out.println("\n8. ПРЕДОТВРАЩЕНИЕ УТЕЧЕК ПАМЯТИ:");

        System.out.println("   ❌ Проблема - static Map утечка:");
        System.out.println("   static Map<User, Profile> userCache = new HashMap<>();");
        System.out.println("   // User объекты никогда не удаляются!");

        System.out.println("\n   ✅ Решение - WeakHashMap:");
        System.out.println("   static Map<User, Profile> userCache = new WeakHashMap<>();");
        System.out.println("   // User объекты удаляются когда становятся недостижимы");

        System.out.println("\n   ✅ Или WeakReference:");
        System.out.println("   static Map<WeakReference<User>, Profile> userCache = ...");
    }
}