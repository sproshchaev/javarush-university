package com.javarush.example;

/**
 * Пример использования статических методов.
 */
public class Slide05_StaticMethodExample {

    // Статическая переменная
    static int staticCounter = 0;

    // Нестатическая переменная
    int instanceCounter = 0;

    // Статический метод — работает только со статическими полями
    public static void incrementStaticCounter() {
        staticCounter++;
        // instanceCounter++; // ❌ ОШИБКА: нельзя обращаться к нестатической переменной
        // this.instanceCounter++; // ❌ ОШИБКА: нельзя использовать 'this'
    }

    // Обычный метод — работает с любыми полями
    public void incrementInstanceCounter() {
        instanceCounter++;
        staticCounter++; // ✅ Можно обращаться к статическим полям
    }

    // Статический метод — можно вызывать без объекта
    public static void printStaticCounter() {
        System.out.println("Статический счётчик: " + staticCounter);
    }

    // Обычный метод — требует объекта
    public void printInstanceCounter() {
        System.out.println("Экземплярный счётчик: " + instanceCounter);
    }

    public static void main(String[] args) {
        // Вызов статического метода — без объекта
        Slide05_StaticMethodExample.incrementStaticCounter();
        Slide05_StaticMethodExample.printStaticCounter(); // Статический счётчик: 1

        // Создаем объект для вызова обычного метода
        Slide05_StaticMethodExample obj = new Slide05_StaticMethodExample();
        obj.incrementInstanceCounter();
        obj.printInstanceCounter(); // Экземплярный счётчик: 1

        // Попробуем вызвать обычный метод без объекта — это невозможно!
        // printInstanceCounter(); // ❌ ОШИБКА компиляции!

        // Но можно вызвать статический метод через объект (не рекомендуется!)
        obj.printStaticCounter(); // ✅ Работает, но лучше через имя класса
    }
}
