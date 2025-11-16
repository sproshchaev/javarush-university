package com.javarush.example;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 26: Демонстрация PhantomReference
 */
public class Slide26_PhantomReferenceDemo {

    private static final ReferenceQueue<Object> PHANTOM_QUEUE = new ReferenceQueue<>();
    private static final List<PhantomReference<Object>> PHANTOM_REFS = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== PHANTOMREFERENCE (Слайд 26) ===\n");

        demonstrateBasicBehavior();
        demonstrateWithFinalize();
        demonstrateCleanupUseCase();
        compareWithOtherReferences();
        showBestPractices();
        demonstrateResourceCleanup();
    }

    /**
     * Демонстрация базового поведения PhantomReference
     */
    private static void demonstrateBasicBehavior() throws InterruptedException {
        System.out.println("1. ОСНОВНОЕ ПОВЕДЕНИЕ:");

        // Создаем объект и phantom reference
        Object target = new Object() {
            @Override
            public String toString() {
                return "Phantom Target Object";
            }
        };

        PhantomReference<Object> phantomRef =
                new PhantomReference<>(target, PHANTOM_QUEUE);
        PHANTOM_REFS.add(phantomRef);

        System.out.println("   Создан PhantomReference");
        System.out.println("   phantomRef.get() = " + phantomRef.get());
        System.out.println("   Всегда возвращает null!");

        // Освобождаем strong ссылку
        target = null;

        System.out.println("\n   Освободили strong ссылку...");
        System.out.println("   Запускаем GC...");

        System.gc();
        Thread.sleep(100);

        // Проверяем очередь
        Reference<?> polledRef = PHANTOM_QUEUE.poll();
        if (polledRef != null) {
            System.out.println("   PhantomReference попала в очередь после GC");
            System.out.println("   Это значит объект финализирован и готов к cleanup");
        } else {
            System.out.println("   Объект еще не финализирован");
        }
    }

    /**
     * Демонстрация работы с finalize()
     */
    private static void demonstrateWithFinalize() throws InterruptedException {
        System.out.println("\n2. PHANTOMREFERENCE И FINALIZE():");

        Object finalizableObject = new Object() {
            private final String name = "Finalizable Object";

            @Override
            protected void finalize() throws Throwable {
                System.out.println("   🔥 finalize() вызван для: " + name);
                super.finalize();
            }

            @Override
            public String toString() {
                return name;
            }
        };

        PhantomReference<Object> phantomRef =
                new PhantomReference<>(finalizableObject, PHANTOM_QUEUE);
        PHANTOM_REFS.add(phantomRef);

        System.out.println("   Создан объект с finalize() методом");

        // Освобождаем strong ссылку
        finalizableObject = null;

        System.out.println("   Запускаем GC...");
        System.gc();
        Thread.sleep(200);

        // Мониторим очередь
        monitorPhantomQueue();
    }

    /**
     * Демонстрация использования для cleanup операций
     */
    private static void demonstrateCleanupUseCase() {
        System.out.println("\n3. CLEANUP ОПЕРАЦИИ:");

        System.out.println("   PhantomReference идеально подходит для:");
        System.out.println("   • Освобождение native resources");
        System.out.println("   • Закрытие file descriptors");
        System.out.println("   • Cleanup операций после финализации");
        System.out.println("   • Мониторинг времени жизни объектов");

        System.out.println("\n   Пример - Native resource cleanup:");
        System.out.println("   class NativeResource {");
        System.out.println("       private long nativeHandle;");
        System.out.println("       ");
        System.out.println("       // Вместо finalize() используем PhantomReference");
        System.out.println("       // для гарантированного освобождения ресурсов");
        System.out.println("   }");
    }

    /**
     * Сравнение с другими типами ссылок
     */
    private static void compareWithOtherReferences() {
        System.out.println("\n4. СРАВНЕНИЕ С ДРУГИМИ ССЫЛКАМИ:");

        System.out.println("   ┌─────────────────┬────────────┬────────────┬────────────┐");
        System.out.println("   │ Характеристика  │   Weak     │   Soft     │  Phantom   │");
        System.out.println("   ├─────────────────┼────────────┼────────────┼────────────┤");
        System.out.println("   │ get() доступен  │    Да      │    Да      │    Нет     │");
        System.out.println("   │ Время сбора     │   Быстро   │ При OOM    │ После final│");
        System.out.println("   │ ReferenceQueue  │   Опционно │ Опционно   │ Обязательно│");
        System.out.println("   │ Использование   │  Кэши      │  Кэши      │  Cleanup   │");
        System.out.println("   └─────────────────┴────────────┴────────────┴────────────┘");

        System.out.println("\n   Ключевые отличия PhantomReference:");
        System.out.println("   • Не позволяет получить объект (get() == null)");
        System.out.println("   • Требует ReferenceQueue");
        System.out.println("   • Собирается ПОСЛЕ вызова finalize()");
        System.out.println("   • Для пост-финализационного cleanup");
    }

    /**
     * Лучшие практики использования PhantomReference
     */
    private static void showBestPractices() {
        System.out.println("\n5. ЛУЧШИЕ ПРАКТИКИ:");

        System.out.println("   Когда использовать PhantomReference:");
        System.out.println("   • Нужен гарантированный cleanup");
        System.out.println("   • Работа с native resources");
        System.out.println("   • Замена ненадежного finalize()");
        System.out.println("   • Мониторинг времени жизни объектов");

        System.out.println("\n   Паттерн использования:");
        System.out.println("   1. Создать PhantomReference с ReferenceQueue");
        System.out.println("   2. Запустить отдельный thread для мониторинга очереди");
        System.out.println("   3. При получении reference из очереди - выполнить cleanup");
        System.out.println("   4. Явно очистить phantom reference");

        System.out.println("\n   Важно:");
        System.out.println("   • Всегда используйте ReferenceQueue");
        System.out.println("   • Очищайте phantom references после использования");
        System.out.println("   • Не используйте для обычных кэшей");
    }

    /**
     * Демонстрация cleanup ресурсов
     */
    private static void demonstrateResourceCleanup() throws InterruptedException {
        System.out.println("\n6. ПРАКТИЧЕСКИЙ ПРИМЕР - RESOURCE CLEANUP:");

        // Создаем объект, представляющий некий ресурс
        Object resource = new Object() {
            private final String resourceName = "Database Connection";
            private boolean closed = false;

            @Override
            protected void finalize() throws Throwable {
                if (!closed) {
                    System.out.println("   ⚠️  WARNING: Ресурс не закрыт в finalize(): " + resourceName);
                }
                super.finalize();
            }

            @Override
            public String toString() {
                return "ResourceHolder{" + resourceName + "}";
            }
        };

        // Создаем phantom reference для отслеживания cleanup
        PhantomReference<Object> phantomRef =
                new PhantomReference<>(resource, PHANTOM_QUEUE);
        PHANTOM_REFS.add(phantomRef);

        System.out.println("   Создан Resource: " + resource);

        // Освобождаем ресурс (симуляция)
        System.out.println("   Освобождаем ресурс...");
        resource = null;

        // Запускаем GC
        System.gc();
        Thread.sleep(200);

        // Проверяем очередь и выполняем cleanup
        performCleanupFromQueue();
    }

    /**
     * Мониторинг phantom queue
     */
    private static void monitorPhantomQueue() throws InterruptedException {
        System.out.println("\n   Мониторинг Phantom Queue:");

        // Даем время для финализации
        Thread.sleep(100);

        Reference<?> ref;
        while ((ref = PHANTOM_QUEUE.poll()) != null) {
            System.out.println("   📨 Получена PhantomReference из очереди");
            System.out.println("   Объект финализирован, можно выполнять cleanup");

            // Очищаем reference
            ref.clear();
            PHANTOM_REFS.remove(ref);
        }
    }

    /**
     * Выполнение cleanup из очереди
     */
    private static void performCleanupFromQueue() {
        System.out.println("\n   Выполнение cleanup из очереди:");

        Reference<?> ref;
        while ((ref = PHANTOM_QUEUE.poll()) != null) {
            System.out.println("   🧹 Выполняем cleanup для финализированного объекта");

            // В реальном приложении здесь было бы освобождение ресурсов
            System.out.println("   • Закрытие file handles");
            System.out.println("   • Освобождение native memory");
            System.out.println("   • Закрытие network connections");

            // Очищаем reference
            ref.clear();
            PHANTOM_REFS.remove(ref);
        }
    }

    /**
     * Демонстрация почему PhantomReference лучше finalize()
     */
    private static void demonstrateFinalizeProblems() {
        System.out.println("\n7. ПРОБЛЕМЫ FINALIZE() И РЕШЕНИЕ:");

        System.out.println("   ❌ Проблемы finalize():");
        System.out.println("   • Не гарантируется вызов");
        System.out.println("   • Может быть вызван поздно");
        System.out.println("   • Может выбросить исключения");
        System.out.println("   • Замедляет сборку мусора");

        System.out.println("\n   ✅ Решение - PhantomReference:");
        System.out.println("   • Гарантированный cleanup через очередь");
        System.out.println("   • Контролируемое время выполнения");
        System.out.println("   • Изоляция исключений");
        System.out.println("   • Более эффективно чем finalize()");

        System.out.println("\n   Oracle рекомендует:");
        System.out.println("   \"Избегайте finalize(), используйте PhantomReference\"");
    }
}