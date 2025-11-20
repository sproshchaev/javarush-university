package com.javarush.example;

import java.util.concurrent.atomic.*;
import java.util.concurrent.*;

public class Slide07_AtomicExamples {

    // Простой счетчик с AtomicInteger
    static class AtomicCounter {
        private AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet();
        }

        public void decrement() {
            count.decrementAndGet();
        }

        public int getValue() {
            return count.get();
        }

        // Пользовательский метод с CAS
        public boolean compareAndSet(int expected, int newValue) {
            return count.compareAndSet(expected, newValue);
        }
    }

    // Пример с AtomicReference для работы с объектами
    static class User {
        String name;
        int age;

        User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }

    // Демонстрация всех методов AtomicInteger
    public static void demonstrateAtomicIntegerMethods() {
        System.out.println("\n5. ВСЕ МЕТОДЫ ATOMICINTEGER:");
        AtomicInteger atomic = new AtomicInteger(50);

        System.out.println("Начальное значение: " + atomic.get());

        // Get-And- операции
        System.out.println("getAndIncrement(): " + atomic.getAndIncrement()); // 50 → 51
        System.out.println("getAndDecrement(): " + atomic.getAndDecrement()); // 51 → 50
        System.out.println("getAndAdd(10): " + atomic.getAndAdd(10)); // 50 → 60
        System.out.println("getAndSet(100): " + atomic.getAndSet(100)); // 60 → 100

        // And-Get операции
        System.out.println("incrementAndGet(): " + atomic.incrementAndGet()); // 100 → 101
        System.out.println("decrementAndGet(): " + atomic.decrementAndGet()); // 101 → 100
        System.out.println("addAndGet(25): " + atomic.addAndGet(25)); // 100 → 125

        // CAS операции
        System.out.println("compareAndSet(125, 150): " + atomic.compareAndSet(125, 150));
        System.out.println("Текущее значение: " + atomic.get());

        // LazySet - "ленивая" установка (в конечном счете установится)
        atomic.lazySet(200);
        System.out.println("После lazySet(200): " + atomic.get());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ДЕМОНСТРАЦИЯ АТОМАРНЫХ ОПЕРАЦИЙ ===\n");

        // 1. AtomicInteger - потокобезопасный счетчик
        System.out.println("1. ATOMICINTEGER - МНОГОПОТОЧНЫЙ СЧЕТЧИК:");
        AtomicCounter counter = new AtomicCounter();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Запускаем 1000 задач, каждая увеличивает счетчик
        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> counter.increment());
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println("Результат после 1000 инкрементов: " + counter.getValue());
        System.out.println("Всегда должно быть 1000! ✓\n");

        // 2. CAS операции
        System.out.println("2. CAS (COMPARE-AND-SWAP) ОПЕРАЦИИ:");
        AtomicInteger atomic = new AtomicInteger(100);

        System.out.println("Начальное значение: " + atomic.get());

        // Успешная CAS операция
        boolean success1 = atomic.compareAndSet(100, 200);
        System.out.println("CAS(100 → 200): " + success1 + ", результат: " + atomic.get());

        // Неуспешная CAS операция (значение уже изменилось)
        boolean success2 = atomic.compareAndSet(100, 300);
        System.out.println("CAS(100 → 300): " + success2 + ", результат: " + atomic.get());

        // 3. AtomicReference для объектов
        System.out.println("\n3. ATOMICREFERENCE ДЛЯ ОБЪЕКТОВ:");
        AtomicReference<User> userRef = new AtomicReference<>(new User("Иван", 25));

        System.out.println("Начальный пользователь: " + userRef.get());

        // Безопасное обновление объекта
        User newUser = new User("Мария", 30);
        boolean updated = userRef.compareAndSet(userRef.get(), newUser);
        System.out.println("Обновление пользователя: " + updated);
        System.out.println("Текущий пользователь: " + userRef.get());

        // 4. Различные атомарные операции
        System.out.println("\n4. РАЗЛИЧНЫЕ АТОМАРНЫЕ ОПЕРАЦИИ:");
        AtomicInteger num = new AtomicInteger(5);

        System.out.println("getAndIncrement(): " + num.getAndIncrement()); // 5, потом 6
        System.out.println("incrementAndGet(): " + num.incrementAndGet()); // 7
        System.out.println("getAndAdd(10): " + num.getAndAdd(10)); // 7, потом 17
        System.out.println("addAndGet(5): " + num.addAndGet(5)); // 22
        System.out.println("getAndSet(100): " + num.getAndSet(100)); // 22, потом 100
        System.out.println("Финальное значение: " + num.get());

        // 5. Демонстрация всех методов AtomicInteger
        demonstrateAtomicIntegerMethods();

        // 6. AtomicBoolean
        System.out.println("\n6. ATOMICBOOLEAN:");
        AtomicBoolean atomicBool = new AtomicBoolean(true);
        System.out.println("Начальное значение: " + atomicBool.get());
        System.out.println("compareAndSet(true, false): " + atomicBool.compareAndSet(true, false));
        System.out.println("Текущее значение: " + atomicBool.get());
    }
}
