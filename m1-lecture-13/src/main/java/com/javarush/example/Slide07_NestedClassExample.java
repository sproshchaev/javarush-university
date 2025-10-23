package com.javarush.example;

/**
 * Пример демонстрации статических и нестатических внутренних классов.
 */
public class Slide07_NestedClassExample {

    // Статическое поле внешнего класса
    static String staticMessage = "Я статическое поле внешнего класса";

    // Нестатическое поле внешнего класса
    String instanceMessage = "Я нестатическое поле внешнего класса";

    // Статический внутренний класс (nested class)
    static class StaticNestedClass {
        void display() {
            System.out.println("✅ Статический внутренний класс:");
            System.out.println(staticMessage); // ✅ Можно обращаться к статическим полям
            // System.out.println(instanceMessage); // ❌ ОШИБКА: нельзя к нестатическим
        }
    }

    // Нестатический внутренний класс (inner class)
    class InnerClass {
        void display() {
            System.out.println("✅ Нестатический внутренний класс:");
            System.out.println(staticMessage);     // ✅ Можно обращаться к статическим
            System.out.println(instanceMessage);   // ✅ Можно обращаться к нестатическим
        }
    }

    public static void main(String[] args) {
        // Создание статического внутреннего класса — без объекта внешнего класса
        StaticNestedClass staticNested = new StaticNestedClass();
        staticNested.display();

        // Создание нестатического внутреннего класса — требует объекта внешнего класса
        Slide07_NestedClassExample outer = new Slide07_NestedClassExample();
        InnerClass inner = outer.new InnerClass();
        inner.display();

        // Можно вызвать статический внутренний класс через имя внешнего класса
        Slide07_NestedClassExample.StaticNestedClass anotherStatic = new Slide07_NestedClassExample.StaticNestedClass();
        anotherStatic.display();
    }
}
