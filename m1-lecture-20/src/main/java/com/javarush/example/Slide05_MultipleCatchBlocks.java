package com.javarush.example;

import java.util.Scanner;

/**
 * Slide05_MultipleCatchBlocks - демонстрация использования нескольких catch-блоков.
 * Пример: программа, которая работает с пользовательским вводом, массивом и делением.
 */
public class Slide05_MultipleCatchBlocks {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = {10, 20, 30, 40, 50}; // Массив из 5 элементов

        System.out.println("=== Работа с несколькими типами исключений ===");

        while (true) {
            try {
                System.out.print("Введите индекс (0-4): ");
                int index = Integer.parseInt(scanner.nextLine());

                System.out.print("Введите делитель: ");
                int divisor = Integer.parseInt(scanner.nextLine());

                // Доступ к массиву
                int value = numbers[index];
                System.out.println("Значение по индексу " + index + ": " + value);

                // Деление
                if (divisor == 0) {
                    throw new ArithmeticException("Деление на ноль!");
                }
                System.out.println("Результат деления: " + (value / divisor));

            } catch (NumberFormatException e) {
                System.out.println("❌ ОШИБКА: Введите целое число!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("⚠️ ОШИБКА: Индекс выходит за границы массива (доступны 0-4).");
            } catch (ArithmeticException e) {
                System.out.println("🚨 ОШИБКА: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("💥 НЕИЗВЕСТНАЯ ОШИБКА: " + e.getMessage());
            } finally {
                System.out.println("---");
            }

            System.out.print("Продолжить? (да/нет): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("да") && !answer.equalsIgnoreCase("yes")) {
                break;
            }
        }

        scanner.close();
        System.out.println("✅ Программа завершена. До встречи!");
    }
}