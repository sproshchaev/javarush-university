package com.javarush.example;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 24: Демонстрация специальных ссылок в Java
 */
public class Slide24_SpecialReferences {

    private static final ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== СПЕЦИАЛЬНЫЕ ССЫЛКИ (Слайд 24) ===\n");

        demonstrateStrongReference();
        demonstrateWeakReference();
        demonstrateSoftReference();
        demonstratePhantomReference();
        showComparisonTable();
        demonstrateUseCases();
    }

    /**
     * Демонстрация Strong Reference (обычная ссылка)
     */
    private static void demonstrateStrongReference() {
        System.out.println("1. STRONG REFERENCE:");

        // Обычная strong reference - защищает объект от GC
        Object strongObj = new Object() {
            @Override
            public String toString() {
                return "Strong Object";
            }
        };

        System.out.println("   Создан: " + strongObj);
        System.out.println("   Strong reference предотвращает сборку мусора");
        System.out.println("   Объект будет жить пока есть strong ссылки");

        // Освобождаем strong ссылку
        strongObj = null;
        System.out.println("   После strongObj = null объект может быть собран");
    }

    /**
     * Демонстрация Weak Reference
     */
    private static void demonstrateWeakReference() throws InterruptedException {
        System.out.println("\n2. WEAK REFERENCE:");

        // Создаем объект и weak ссылку на него
        Object weakTarget = new Object() {
            @Override
            public String toString() {
                return "Weak Target Object";
            }
        };

        WeakReference<Object> weakRef = new WeakReference<>(weakTarget);

        System.out.println("   Создан WeakReference: " + weakRef.get());

        // Убираем strong ссылку
        weakTarget = null;

        System.out.println("   Убрали strong ссылку...");
        System.out.println("   WeakReference до GC: " + weakRef.get());

        // Запускаем GC
        System.gc();
        Thread.sleep(100);

        System.out.println("   WeakReference после GC: " + weakRef.get());
        System.out.println("   WeakReference собран при первой же сборке!");
    }

    /**
     * Демонстрация Soft Reference
     */
    private static void demonstrateSoftReference() {
        System.out.println("\n3. SOFT REFERENCE:");

        // Создаем soft reference
        Object softTarget = new Object() {
            @Override
            public String toString() {
                return "Soft Target Object";
            }
        };

        SoftReference<Object> softRef = new SoftReference<>(softTarget);

        System.out.println("   Создан SoftReference: " + softRef.get());
        System.out.println("   SoftReference будет жить пока есть память");
        System.out.println("   Собирается только когда JVM нужна память");

        // Освобождаем strong ссылку
        softTarget = null;

        System.out.println("   SoftReference после освобождения strong: " + softRef.get());
        System.out.println("   Может пережить несколько GC циклов");
    }

    /**
     * Демонстрация Phantom Reference
     */
    private static void demonstratePhantomReference() throws InterruptedException {
        System.out.println("\n4. PHANTOM REFERENCE:");

        // Создаем phantom reference с ReferenceQueue
        Object phantomTarget = new Object() {
            @Override
            public String toString() {
                return "Phantom Target Object";
            }

            @Override
            protected void finalize() throws Throwable {
                System.out.println("   finalize() вызван для Phantom Target");
                super.finalize();
            }
        };

        PhantomReference<Object> phantomRef =
                new PhantomReference<>(phantomTarget, referenceQueue);

        System.out.println("   Создан PhantomReference");
        System.out.println("   phantomRef.get() всегда возвращает: " + phantomRef.get());

        // Освобождаем strong ссылку
        phantomTarget = null;

        System.out.println("   Запускаем GC...");
        System.gc();
        Thread.sleep(100);

        // Проверяем очередь
        Reference<?> polledRef = referenceQueue.poll();
        if (polledRef != null) {
            System.out.println("   Объект попал в ReferenceQueue после финализации");
        }

        System.out.println("   PhantomReference используется для cleanup операций");
    }

    /**
     * Сравнительная таблица типов ссылок
     */
    private static void showComparisonTable() {
        System.out.println("\n5. СРАВНЕНИЕ ТИПОВ ССЫЛОК:");

        System.out.println("   ┌─────────────┬────────────────────┬────────────────────┐");
        System.out.println("   │    Тип      │     Когда собирается │      Использование   │");
        System.out.println("   ├─────────────┼────────────────────┼────────────────────┤");
        System.out.println("   │ Strong      │ Никогда            │ Обычные объекты    │");
        System.out.println("   │ Weak        │ Следующий GC       │ Кэши, listeners    │");
        System.out.println("   │ Soft        │ При нехватке памяти│ Memory-sensitive   │");
        System.out.println("   │ Phantom     │ После finalize()   │ Cleanup операции   │");
        System.out.println("   └─────────────┴────────────────────┴────────────────────┘");
    }

    /**
     * Практические примеры использования
     */
    private static void demonstrateUseCases() {
        System.out.println("\n6. ПРАКТИЧЕСКИЕ ПРИМЕРЫ:");

        System.out.println("   WeakReference - для кэшей:");
        System.out.println("   WeakHashMap, listeners, metadata cache");

        System.out.println("\n   SoftReference - для memory-sensitive кэшей:");
        System.out.println("   Image cache, large object cache");
        System.out.println("   Автоматически очищается при memory pressure");

        System.out.println("\n   PhantomReference - для cleanup:");
        System.out.println("   Освобождение native resources");
        System.out.println("   Логирование удаления объектов");

        // Демонстрация WeakHashMap
        demonstrateWeakHashMapExample();
    }

    /**
     * Демонстрация WeakHashMap
     */
    private static void demonstrateWeakHashMapExample() {
        System.out.println("\n7. WEAKHASHMAP ПРИМЕР:");

        System.out.println("   WeakHashMap автоматически удаляет entries");
        System.out.println("   когда ключи становятся недостижимыми");

        // Пример кода (для демонстрации)
        System.out.println("   Map<Key, Value> cache = new WeakHashMap<>();");
        System.out.println("   // Когда Key собирается GC, entry удаляется");
    }

    /**
     * Демонстрация утечки памяти без weak references
     */
    private static void demonstrateMemoryLeak() {
        System.out.println("\n8. ПРОБЛЕМА БЕЗ WEAK REFERENCES:");

        System.out.println("   ❌ Проблема - listeners утечка:");
        System.out.println("   class EventManager {");
        System.out.println("       private List<Listener> listeners = new ArrayList<>();");
        System.out.println("       // Listeners никогда не удаляются → memory leak!");
        System.out.println("   }");

        System.out.println("\n   ✅ Решение - weak references:");
        System.out.println("   class EventManager {");
        System.out.println("       private List<WeakReference<Listener>> listeners = new ArrayList<>();");
        System.out.println("       // Listeners автоматически удаляются GC");
        System.out.println("   }");
    }
}