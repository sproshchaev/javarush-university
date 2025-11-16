package com.javarush.example;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Слайд 19: Демонстрация Metaspace и управления метаданными
 */
public class Slide19_MetaspaceDemo {

    // Статическая коллекция для демонстрации хранения метаданных
    private static final List<Class<?>> LOADED_CLASSES = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("=== METASPACE И УПРАВЛЕНИЕ МЕТАДАННЫМИ (Слайд 19) ===\n");

        demonstrateMetaspaceInfo();
        demonstrateClassLoading();
        demonstrateMemoryManagement();
        comparePermGenVsMetaspace();

        System.out.println("\n=== РЕКОМЕНДАЦИИ ===");
        System.out.println("• Java 8+: Metaspace автоматически управляет памятью метаданных");
        System.out.println("• -XX:MaxMetaspaceSize для установки верхнего лимита");
        System.out.println("• Динамическая загрузка классов безопаснее в Metaspace");
        System.out.println("• Мониторьте использование Metaspace в продакшене");
    }

    /**
     * Демонстрация информации о Metaspace
     */
    private static void demonstrateMetaspaceInfo() {
        System.out.println("1. ИНФОРМАЦИЯ О METASPACE:");

        // Получаем информацию о пулах памяти
        List<MemoryPoolMXBean> memoryPools = ManagementFactory.getMemoryPoolMXBeans();

        for (MemoryPoolMXBean pool : memoryPools) {
            String name = pool.getName();
            if (name.toLowerCase().contains("metaspace") || name.toLowerCase().contains("class")) {
                System.out.println("   Пул памяти: " + name);
                System.out.println("   Использование: " + pool.getUsage());
                System.out.println("   Пиковое использование: " + pool.getPeakUsage());
            }
        }

        Runtime runtime = Runtime.getRuntime();
        System.out.println("\n   Общая память JVM:");
        System.out.println("   Total Heap: " + (runtime.totalMemory() / 1024 / 1024) + " MB");
        System.out.println("   Max Heap: " + (runtime.maxMemory() / 1024 / 1024) + " MB");
        System.out.println("   Metaspace находится ВНЕ кучи (native memory)");
    }

    /**
     * Демонстрация загрузки классов и использования Metaspace
     */
    private static void demonstrateClassLoading() throws Exception {
        System.out.println("\n2. ЗАГРУЗКА КЛАССОВ И METASPACE:");

        System.out.println("   Загружаем системные классы...");

        // Загружаем различные классы для демонстрации
        Class<?>[] systemClasses = {
                String.class,
                ArrayList.class,
                Thread.class,
                System.class,
                RuntimeException.class
        };

        for (Class<?> clazz : systemClasses) {
            LOADED_CLASSES.add(clazz);
            System.out.println("   Загружен: " + clazz.getName());
        }

        // Демонстрация загрузки пользовательских классов
        System.out.println("\n   Создаем пользовательские классы...");
        createDynamicClasses(5);

        System.out.println("   Всего загружено классов: " + LOADED_CLASSES.size());
        System.out.println("   Все метаданные хранятся в Metaspace");
    }

    /**
     * Демонстрация управления памятью Metaspace
     */
    private static void demonstrateMemoryManagement() {
        System.out.println("\n3. УПРАВЛЕНИЕ ПАМЯТЬЮ METASPACE:");

        System.out.println("   Преимущества Metaspace (Java 8+):");
        System.out.println("   • Автоматическое расширение при необходимости");
        System.out.println("   • Хранится в native memory (вне кучи)");
        System.out.println("   • Неиспользуемые классы могут быть выгружены");
        System.out.println("   • Нет фиксированного ограничения как в PermGen");

        System.out.println("\n   Когда классы выгружаются из Metaspace:");
        System.out.println("   1. ClassLoader собирается сборщиком мусора");
        System.out.println("   2. Все классы этого ClassLoader'а становятся недостижимы");
        System.out.println("   3. Metaspace освобождает память этих классов");

        // Демонстрация "утечки" метаданных
        System.out.println("\n   Потенциальные проблемы:");
        System.out.println("   • Бесконечная перезагрузка классов (Redeployment)");
        System.out.println("   • Динамическая генерация классов без очистки");
        System.out.println("   • Server containers (Tomcat, JBoss) при частом redeploy");
    }

    /**
     * Сравнение PermGen и Metaspace
     */
    private static void comparePermGenVsMetaspace() {
        System.out.println("\n4. СРАВНЕНИЕ: PERMGEN VS METASPACE");

        System.out.println("   PERMGEN (Java 7 и ранее):");
        System.out.println("   ┌─────────────────────────────────────────────┐");
        System.out.println("   │ • Фиксированный размер                      │");
        System.out.println("   │ • -XX:PermSize, -XX:MaxPermSize            │");
        System.out.println("   │ • OutOfMemoryError: PermGen space          │");
        System.out.println("   │ • Проблемы с hot deployment               │");
        System.out.println("   │ • Жесткие лимиты                          │");
        System.out.println("   └─────────────────────────────────────────────┘");

        System.out.println("\n   METASPACE (Java 8+):");
        System.out.println("   ┌─────────────────────────────────────────────┐");
        System.out.println("   │ • Автоматическое расширение                │");
        System.out.println("   │ • -XX:MaxMetaspaceSize (опционально)       │");
        System.out.println("   │ • Хранится в native memory                 │");
        System.out.println("   │ • Классы выгружаются при нехватке памяти   │");
        System.out.println("   │ • Лучшая стабильность при redeploy         │");
        System.out.println("   └─────────────────────────────────────────────┘");

        System.out.println("\n   Примеры настройки JVM:");
        System.out.println("   Java 7: java -Xmx2g -XX:MaxPermSize=256m -jar app.jar");
        System.out.println("   Java 8+: java -Xmx2g -XX:MaxMetaspaceSize=512m -jar app.jar");
        System.out.println("   Java 11+: java -Xmx2g -jar app.jar (Metaspace по умолчанию)");
    }

    /**
     * Создание динамических классов для демонстрации
     */
    private static void createDynamicClasses(int count) {
        for (int i = 0; i < count; i++) {
            try {
                // Создаем простой динамический класс
                Class<?> dynamicClass = createSimpleClass(i);
                LOADED_CLASSES.add(dynamicClass);

                if (i < 3) { // Покажем только первые несколько
                    System.out.println("   Создан динамический класс: " + dynamicClass.getSimpleName());
                }
            } catch (Exception e) {
                System.out.println("   Ошибка создания класса: " + e.getMessage());
            }
        }
        if (count > 3) {
            System.out.println("   ... и еще " + (count - 3) + " классов");
        }
    }

    /**
     * Создание простого класса во время выполнения
     */
    private static Class<?> createSimpleClass(int index) {
        // В реальном приложении здесь была бы компиляция и загрузка байткода
        // Для демонстрации вернем существующий класс
        return DynamicExampleClass.class;
    }

    /**
     * Пример класса для демонстрации динамической загрузки
     */
    public static class DynamicExampleClass {
        private final int id;
        private String data;

        public DynamicExampleClass(int id) {
            this.id = id;
            this.data = "DynamicData-" + id;
        }

        public void process() {
            System.out.println("Обработка " + data);
        }

        public String getInfo() {
            return "DynamicExampleClass{id=" + id + ", data='" + data + "'}";
        }
    }

    /**
     * Демонстрация "утечки" ClassLoader'ов (опасный пример)
     */
    private static void demonstrateClassLoaderLeak() {
        System.out.println("\n5. ВАЖНО: ИЗБЕГАЙТЕ УТЕЧЕК CLASSLOADER:");

        System.out.println("   Опасный паттерн:");
        System.out.println("   CustomClassLoader loader = new CustomClassLoader();");
        System.out.println("   Class<?> clazz = loader.loadClass(...);");
        System.out.println("   // Забыли очистить ссылки → ClassLoader не собирается GC");
        System.out.println("   // → Все его классы остаются в Metaspace");

        System.out.println("\n   Правильный подход:");
        System.out.println("   • Используйте существующие ClassLoader'ы когда возможно");
        System.out.println("   • Очищайте ссылки на кастомные ClassLoader'ы");
        System.out.println("   • В server containers используйте правильные deployment стратегии");
    }
}