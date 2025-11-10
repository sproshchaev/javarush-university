package com.javarush.example;

import java.util.Scanner;

/**
 * Slide13_MultiCatch - демонстрация использования множественного перехвата исключений (multi-catch).
 * Пример: работа с пользовательским вводом и обработка нескольких типов ошибок одним блоком.
 */
public class Slide13_MultiCatch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] data = {10, 20, 30, 40, 50};

        System.out.println("=== Демонстрация множественного перехвата исключений ===");

        while (true) {
            try {
                System.out.print("Введите индекс массива (0-4): ");
                int index = Integer.parseInt(scanner.nextLine());

                System.out.print("Введите делитель: ");
                int divisor = Integer.parseInt(scanner.nextLine());

                int value = data[index]; // Может вызвать ArrayIndexOutOfBoundsException
                if (divisor == 0) {
                    throw new ArithmeticException("Деление на ноль запрещено!");
                }

                int result = value / divisor;
                System.out.println("Результат: " + result);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                // Обработка двух разных ошибок одним блоком — DRY!
                System.out.println("⚠️ ОШИБКА: Некорректный ввод или неверный индекс.");
                System.out.println("Подробности: " + e.getMessage());
            } catch (ArithmeticException e) {
                // Отдельная обработка — потому что логика другая
                System.out.println("🚨 ОШИБКА: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("💥 Неизвестная ошибка: " + e.getMessage());
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
        System.out.println("✅ Программа завершена. Код без дублирования!");
    }
}