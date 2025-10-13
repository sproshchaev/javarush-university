package com.javarush.example;

/**
 * Пример области видимости переменных
 */
public class Slide04_ScopeOfVariables {

    // Переменные класса (поля) — доступны везде внутри класса
    public int count = 0;
    public int sum = 0;

    public static void main(String[] args) {
        Slide04_ScopeOfVariables solution = new Slide04_ScopeOfVariables();

        System.out.println("Начальные значения:");
        System.out.println("count = " + solution.count);
        System.out.println("sum   = " + solution.sum);

        solution.add(10); // Вызов метода

        System.out.println("\nПосле вызова add(10):");
        System.out.println("count = " + solution.count);
        System.out.println("sum   = " + solution.sum);
    }

    public void add(int data) {
        // Локальная переменная 'data' — доступна только в этом методе
        sum = sum + data; // Используем поле класса 'sum'

        // Локальная переменная 'sum' — скрывает поле класса!
        int sum = data * 2; // ⚠️ Это НОВАЯ локальная переменная!

        count++; // Используем поле класса 'count'

        // Выводим значения — показываем разницу между полем и локальной переменной
        System.out.println("Внутри метода add():");
        System.out.println("  Поле sum (класса): " + this.sum); // Доступ через this
        System.out.println("  Локальная sum: " + sum);         // Локальная переменная
        System.out.println("  Параметр data: " + data);
        System.out.println("  Поле count: " + count);
    }
}