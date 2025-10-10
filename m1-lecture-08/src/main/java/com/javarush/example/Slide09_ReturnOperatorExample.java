package com.javarush.example;

import java.util.Arrays;

/**
 * Пример из слайда 9: "Оператор return"
 *
 * Оператор return мгновенно завершает работу метода, в котором он был вызван.
 * Если вызвать return в main() — завершается вся программа.
 */
public class Slide09_ReturnOperatorExample {

    public static void main(String[] args) {
        System.out.println("=== Оператор return ===");

        int[] array = new int[10];

        for (int i = 0; i < 10; i++) {
            array[i] = i;
            System.out.println("Заполнено значение: " + array[i]);

            if (i == 3) {
                System.out.println("\n⚠️ Достигнуто условие i == 3 — вызов return!");
                return; // 👇 Программа завершается здесь!
            }
        }

        // ❗ Эта строка НЕ ВЫПОЛНИТСЯ, потому что return уже сработал
        System.out.println("\nТекущее состояние массива:");
        System.out.println(Arrays.toString(array));
    }
}
