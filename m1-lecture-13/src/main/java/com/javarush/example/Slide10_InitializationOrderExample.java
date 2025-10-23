package com.javarush.example;

/**
 * Пример демонстрации порядка инициализации внешнего и статического вложенного классов.
 */
public class Slide10_InitializationOrderExample {

    // --- ВНЕШНИЙ КЛАСС ---

    // 1. Статическое поле внешнего класса
    static String staticFieldOuter = initializeStaticFieldOuter();

    // 2. Статический блок внешнего класса
    static {
        System.out.println("✅ [ВНЕШНИЙ] Статический блок внешнего класса");
    }

    // 3. Нестатическое поле внешнего класса
    String instanceFieldOuter = initializeInstanceFieldOuter();

    // 4. Блок инициализации внешнего класса
    {
        System.out.println("✅ [ВНЕШНИЙ] Блок инициализации внешнего класса");
    }

    // 5. Конструктор внешнего класса
    public Slide10_InitializationOrderExample() {
        System.out.println("✅ [ВНЕШНИЙ] Конструктор внешнего класса");
    }

    // --- СТАТИЧЕСКИЙ ВЛОЖЕННЫЙ КЛАСС ---

    static class StaticNestedClass {

        // 1. Статическое поле внутреннего класса
        static String staticFieldInner = initializeStaticFieldInner();

        // 2. Статический блок внутреннего класса
        static {
            System.out.println("✅ [ВНУТРЕННИЙ] Статический блок внутреннего класса");
        }

        // 3. Нестатическое поле внутреннего класса
        String instanceFieldInner = initializeInstanceFieldInner();

        // 4. Блок инициализации внутреннего класса
        {
            System.out.println("✅ [ВНУТРЕННИЙ] Блок инициализации внутреннего класса");
        }

        // 5. Конструктор внутреннего класса
        public StaticNestedClass() {
            System.out.println("✅ [ВНУТРЕННИЙ] Конструктор внутреннего класса");
        }

        // Методы инициализации — теперь внутри StaticNestedClass

        private static String initializeStaticFieldInner() {
            System.out.println("✅ [ВНУТРЕННИЙ] Инициализация статического поля внутреннего класса");
            return "Статическое поле внутреннего класса";
        }

        private String initializeInstanceFieldInner() {
            System.out.println("✅ [ВНУТРЕННИЙ] Инициализация нестатического поля внутреннего класса");
            return "Нестатическое поле внутреннего класса";
        }
    }

    // --- МЕТОДЫ ИНИЦИАЛИЗАЦИИ ВНЕШНЕГО КЛАССА ---

    private static String initializeStaticFieldOuter() {
        System.out.println("✅ [ВНЕШНИЙ] Инициализация статического поля внешнего класса");
        return "Статическое поле внешнего класса";
    }

    private String initializeInstanceFieldOuter() {
        System.out.println("✅ [ВНЕШНИЙ] Инициализация нестатического поля внешнего класса");
        return "Нестатическое поле внешнего класса";
    }

    // --- MAIN ---

    public static void main(String[] args) {
        System.out.println("\n--- СОЗДАНИЕ ОБЪЕКТА ВНЕШНЕГО КЛАССА ---");
        Slide10_InitializationOrderExample outer = new Slide10_InitializationOrderExample();

        System.out.println("\n--- СОЗДАНИЕ ОБЪЕКТА СТАТИЧЕСКОГО ВЛОЖЕННОГО КЛАССА ---");
        StaticNestedClass inner = new StaticNestedClass();
    }
}