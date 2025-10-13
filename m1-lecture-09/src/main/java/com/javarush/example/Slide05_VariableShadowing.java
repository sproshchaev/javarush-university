package com.javarush.example;

/**
 * Пример затенения переменных (Variable Shadowing)
 */
public class Slide05_VariableShadowing {

    // Поля класса
    public int count = 0;
    public int sum = 0;

    public static void main(String[] args) {
        Slide05_VariableShadowing solution = new Slide05_VariableShadowing();

        System.out.println("Начальные значения:");
        System.out.println("count = " + solution.count);
        System.out.println("sum   = " + solution.sum);

        solution.add(10); // Вызов метода

        System.out.println("\nПосле вызова add(10):");
        System.out.println("count = " + solution.count);
        System.out.println("sum   = " + solution.sum);
    }

    public void add(int data) {
        // Локальная переменная 'sum' затеняет поле класса 'sum'
        int sum = data * 2; // ⚠️ Это НОВАЯ локальная переменная!

        // Доступ к полю класса через 'this'
        this.sum = this.sum + data; // ✅ Обновляем поле класса

        count++; // Поле класса — доступно напрямую

        // Выводим значения — показываем разницу
        System.out.println("Внутри метода add():");
        System.out.println("  Поле sum (класса): " + this.sum); // Через this
        System.out.println("  Локальная sum: " + sum);         // Локальная переменная
        System.out.println("  Параметр data: " + data);
        System.out.println("  Поле count: " + count);
    }
}