package com.javarush.example;

/**
 * Пример демонстрации особенностей статических вложенных классов.
 */
public class Slide09_StaticNestedClassFeaturesExample {

    // Статическое поле внешнего класса — доступно вложеному
    private static String secretNote = "Это секретная заметка внешнего класса";

    // Нестатическое поле — недоступно статическому вложенному классу
    String publicNote = "Это публичная заметка экземпляра";

    // Статический вложенный класс — ведёт себя как обычный класс
    public static class StaticNestedClass {

        // Статическое поле
        public static String nestedStaticField = "Я статическое поле вложенного класса";

        // Нестатическое поле
        private String instanceField = "Я поле экземпляра вложенного класса";

        // Конструктор
        public StaticNestedClass(String value) {
            this.instanceField = value;
        }

        // Обычный метод
        public void display() {
            System.out.println("✅ Я метод статического вложенного класса:");
            System.out.println(nestedStaticField);
            System.out.println(instanceField);

            // ✅ Доступ к статическому полю внешнего класса (даже private!)
            System.out.println(secretNote); // Работает!

            // ❌ Нельзя обратиться к нестатическому полю внешнего класса
            // System.out.println(publicNote); // ОШИБКА компиляции!
        }

        // Статический метод
        public static void staticMethod() {
            System.out.println("✅ Статический метод вложенного класса");
        }
    }

    public static void main(String[] args) {
        // Создаём объект статического вложенного класса
        StaticNestedClass obj = new StaticNestedClass("Значение из конструктора");
        obj.display();

        // Вызываем статический метод
        StaticNestedClass.staticMethod();

        // Попробуем создать объект через внешний класс — работает
        Slide09_StaticNestedClassFeaturesExample.StaticNestedClass anotherObj =
                new Slide09_StaticNestedClassFeaturesExample.StaticNestedClass("Другой объект");

        anotherObj.display();
    }
}