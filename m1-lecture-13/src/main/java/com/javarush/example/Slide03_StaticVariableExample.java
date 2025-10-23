package com.javarush.example;

/**
 * Пример использования статических переменных.
 */
public class Slide03_StaticVariableExample {

    // Статическая переменная — общая для всех объектов
    static int instanceCounter = 0;

    // Поле экземпляра — уникальное для каждого объекта
    String name;

    public Slide03_StaticVariableExample(String name) {
        this.name = name;
        instanceCounter++; // Увеличиваем счётчик при создании объекта
    }

    public void showInfo() {
        System.out.println("Объект: " + name + ", номер: " + instanceCounter);
    }

    public static void main(String[] args) {
        // Создаем несколько объектов
        Slide03_StaticVariableExample obj1 = new Slide03_StaticVariableExample("Первый");
        Slide03_StaticVariableExample obj2 = new Slide03_StaticVariableExample("Второй");
        Slide03_StaticVariableExample obj3 = new Slide03_StaticVariableExample("Третий");

        // Вызываем методы — видим общий счётчик
        obj1.showInfo(); // Объект: Первый, номер: 3
        obj2.showInfo(); // Объект: Второй, номер: 3
        obj3.showInfo(); // Объект: Третий, номер: 3

        // Обращение к статической переменной через имя класса
        System.out.println("Всего создано объектов: " + Slide03_StaticVariableExample.instanceCounter);
    }
}