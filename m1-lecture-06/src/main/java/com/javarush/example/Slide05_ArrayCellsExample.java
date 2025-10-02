package com.javarush.example;


public class Slide05_ArrayCellsExample {

    public static void main(String[] args) {
        System.out.println("=== Работа с ячейками массива ===\n");

        // Создаём массив из 10 элементов типа int
        int[] a = new int[10];

        // Записываем значения в конкретные ячейки
        a[2] = 4;   // Ячейка с индексом 2 → значение 4
        a[7] = 9;   // Ячейка с индексом 7 → значение 9

        // Используем значения из ячеек для вычисления
        a[9] = a[2] + a[5]; // a[5] по умолчанию 0 → 4 + 0 = 4

        // Выводим весь массив с индексами
        System.out.println("Содержимое массива:");
        for (int i = 0; i < a.length; i++) {
            System.out.printf("a[%d] = %d%n", i, a[i]);
        }

        System.out.println("\n--- Попытка выхода за границы массива ---");
        try {
            System.out.println("Попытка доступа к a[10]: " + a[10]); // Ошибка!
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("⚠️ Ошибка: попытка обратиться к элементу за пределами массива!");
        }

        System.out.println("\n--- Пример: изменение значений в цикле ---");
        int[] b = {1, 2, 3, 4, 5};
        System.out.println("Исходный массив b: ");
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();

        // Увеличиваем каждый элемент на 10
        for (int i = 0; i < b.length; i++) {
            b[i] = b[i] + 10;
        }

        System.out.println("Массив b после изменения: ");
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
    }
}
