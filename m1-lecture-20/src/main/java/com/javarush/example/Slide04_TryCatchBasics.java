package com.javarush.example;

import java.util.Scanner;

/**
 * Slide04_TryCatchBasics - демонстрация работы с блоком try-catch.
 * Пример: безопасная работа с массивом и пользовательским вводом.
 */
public class Slide04_TryCatchBasics {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] data = {10, 20, 30, 40, 50}; // Массив из 5 элементов

        System.out.println("=== Работа с массивом и обработка исключений ===");

        while (true) {
            try {
                System.out.print("Введите индекс элемента (0-4) или -1 для выхода: ");
                int index = Integer.parseInt(scanner.nextLine());

                if (index == -1) {
                    break;
                }

                // Попытка доступа к элементу массива
                int value = data[index];
                System.out.println("Значение по индексу " + index + ": " + value);

            } catch (NumberFormatException e) {
                System.out.println("❌ ОШИБКА: Введите целое число!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("⚠️ ОШИБКА: Индекс выходит за границы массива (доступны индексы 0-4).");
            } catch (Exception e) {
                System.out.println("🚨 НЕИЗВЕСТНАЯ ОШИБКА: " + e.getMessage());
            } finally {
                System.out.println("---");
            }
        }

        scanner.close();
        System.out.println("✅ Программа завершена. Спасибо за использование!");
    }
}
