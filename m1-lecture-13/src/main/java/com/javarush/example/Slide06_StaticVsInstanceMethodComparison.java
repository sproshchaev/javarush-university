package com.javarush.example;

/**
 * Пример, демонстрирующий различия между статическими и обычными методами,
 * согласно таблице на слайде 6.
 */
public class Slide06_StaticVsInstanceMethodComparison {

    // Статическая переменная
    static int staticValue = 10;

    // Нестатическая переменная
    int instanceValue = 20;

    // Обычный метод
    public void instanceMethod() {
        System.out.println("✅ Обычный метод: вызван у объекта");
        System.out.println("✅ Обращается к нестатической переменной: " + instanceValue);
        System.out.println("✅ Обращается к статической переменной: " + staticValue);

        // ✅ Может вызывать и статические, и нестатические методы
        staticMethod();
        anotherInstanceMethod();
    }

    // Статический метод
    public static void staticMethod() {
        System.out.println("✅ Статический метод: вызван у класса");
        System.out.println("✅ Обращается к статической переменной: " + staticValue);
        // ❌ Нельзя обратиться к нестатической переменной
        // System.out.println(instanceValue); // ОШИБКА компиляции!

        // ✅ Может вызывать только статические методы
        anotherStaticMethod();
        // anotherInstanceMethod(); // ❌ ОШИБКА компиляции!
    }

    // Другой обычный метод
    public void anotherInstanceMethod() {
        System.out.println("✅ Ещё один обычный метод");
    }

    // Другой статический метод
    public static void anotherStaticMethod() {
        System.out.println("✅ Ещё один статический метод");
    }

    public static void main(String[] args) {
        // Создаем объект
        Slide06_StaticVsInstanceMethodComparison obj = new Slide06_StaticVsInstanceMethodComparison();

        // ✅ Обычный метод — вызывается только у объекта
        obj.instanceMethod();

        // ✅ Статический метод — вызывается у класса
        Slide06_StaticVsInstanceMethodComparison.staticMethod();

        // ✅ Можно вызвать статический метод через объект (но не рекомендуется!)
        obj.staticMethod(); // Работает, но выглядит странно

        // ❌ Нельзя вызвать обычный метод у класса
        // Slide06_StaticVsInstanceMethodComparison.anotherInstanceMethod(); // ОШИБКА!
    }
}