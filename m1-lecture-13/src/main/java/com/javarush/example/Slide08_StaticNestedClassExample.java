package com.javarush.example;

/**
 * Пример использования статического вложенного класса.
 */
public class Slide08_StaticNestedClassExample {

    // Статическое поле внешнего класса
    static String outerStaticField = "Я статическое поле внешнего класса";

    // Нестатическое поле внешнего класса
    String outerInstanceField = "Я нестатическое поле внешнего класса";

    // Статический вложенный класс — public, чтобы был доступен извне
    public static class StaticNestedClass {

        // Статическое поле внутри вложенного класса
        public static String nestedStaticField = "Я статическое поле вложенного класса";

        // Метод внутри вложенного класса
        public void display() {
            System.out.println("✅ Внутри статического вложенного класса:");
            System.out.println(outerStaticField); // ✅ Можно обращаться к статическим полям внешнего класса
            // System.out.println(outerInstanceField); // ❌ ОШИБКА: нельзя к нестатическим

            System.out.println(nestedStaticField);
        }

        // Статический метод внутри вложенного класса
        public static void staticMethod() {
            System.out.println("✅ Статический метод вложенного класса вызван!");
        }
    }

    public static void main(String[] args) {
        // ✅ Создание объекта статического вложенного класса — без объекта внешнего класса
        StaticNestedClass nestedObj = new StaticNestedClass();
        nestedObj.display();

        // ✅ Вызов статического метода вложенного класса
        StaticNestedClass.staticMethod();

        // ✅ Обращение к статической переменной вложенного класса
        System.out.println("Статическая переменная вложенного класса: " + StaticNestedClass.nestedStaticField);

        // ✅ Можно создать объект через полное имя класса — если public
        Slide08_StaticNestedClassExample.StaticNestedClass anotherObj = new Slide08_StaticNestedClassExample.StaticNestedClass();
        anotherObj.display();
    }
}