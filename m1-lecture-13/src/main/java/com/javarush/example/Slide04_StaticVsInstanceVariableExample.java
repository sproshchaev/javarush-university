package com.javarush.example;

/**
 * Пример демонстрации разницы между статическими и нестатическими переменными.
 */
public class Slide04_StaticVsInstanceVariableExample {

    // Статическая переменная — общая для всех объектов
    static int sharedCounter = 0;

    // Нестатическая переменная — уникальная для каждого объекта
    int individualCounter = 0;

    public void incrementCounters() {
        sharedCounter++;      // Общая — меняется для всех
        individualCounter++;  // Личная — меняется только для этого объекта
    }

    public void showCounters() {
        System.out.println("Объект: " + this + " | Общий счётчик: " + sharedCounter +
                " | Личный счётчик: " + individualCounter);
    }

    public static void main(String[] args) {
        Slide04_StaticVsInstanceVariableExample obj1 = new Slide04_StaticVsInstanceVariableExample();
        Slide04_StaticVsInstanceVariableExample obj2 = new Slide04_StaticVsInstanceVariableExample();

        obj1.incrementCounters(); // Общий: 1, Личный: 1
        obj2.incrementCounters(); // Общий: 2, Личный: 1

        obj1.showCounters(); // Общий: 2, Личный: 1
        obj2.showCounters(); // Общий: 2, Личный: 1

        // Меняем значение статической переменной напрямую — видно всем
        sharedCounter = 99;
        obj1.showCounters(); // Общий: 99, Личный: 1
        obj2.showCounters(); // Общий: 99, Личный: 1

        // Обращение к статической переменной через имя класса
        System.out.println("Текущее значение общего счётчика: " + Slide04_StaticVsInstanceVariableExample.sharedCounter);
    }
}
